package com.onpassive.onet.serviceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.onpassive.onet.config.FileStorageProperties;
import com.onpassive.onet.entity.Events;
import com.onpassive.onet.entity.User;
import com.onpassive.onet.exception.FileStorageException;
import com.onpassive.onet.model.EventModel;
import com.onpassive.onet.model.UpdateEventModel;
import com.onpassive.onet.repository.EventsRepository;
import com.onpassive.onet.service.EventService;
import com.onpassive.onet.util.TimeUtills;

@Service
public class EventServiceImpl implements EventService {
	private final Path eventImagePath;

	@Autowired
	EventsRepository eventRepository;

	@Autowired
	public EventServiceImpl(FileStorageProperties fileStorageProperties) {
		this.eventImagePath = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.eventImagePath);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	@Override
	public List<Events> getAllEvents() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		List<Events> list = new ArrayList<Events>();

		list = eventRepository.findAll();
		String differneceDate = null;
		int i = 0;

		for (Events event : list) {

			differneceDate = TimeUtills.convertToSpecificFormat(event.getEventCreatedDate());
			System.out.println("-----difference--------->" + differneceDate);
			list.get(i).setEventCreatedDate(differneceDate);

			i++;
		}

		Collections.reverse(list);

		return list;
	}

	@Override
	public User findUserByEmpId(long empId) {
		return eventRepository.findUserByEmpId(empId);
	}

	public String convertToStringDateTime(LocalDateTime localDateTime) {
		System.out.println("Before Formatting: " + localDateTime);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatDateTime = localDateTime.format(format);
		System.out.println("After Formatting: " + formatDateTime);
		return formatDateTime;
	}

	public String convertEventDatesToStringDateTime(String eventDates) {
		System.out.println("Before Formatting: " + eventDates);

		String replacedEventDate = eventDates.replace("T", " ");

		System.out.println("After EventDate : " + replacedEventDate);
		return replacedEventDate;
	}

	@Override
	public long saveEvent(long empId, MultipartFile file, EventModel model) {
		String eventImage = StringUtils.cleanPath(file.getOriginalFilename());
		User user = eventRepository.findUserByEmpId(empId);

		try {
			if (eventImage.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + eventImage);
			}
			Path targetLocation = this.eventImagePath.resolve(eventImage);
			System.out.println("check::" + model.getEventStartDate());
			Events event = new Events();

			LocalDateTime currentDate = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

			System.out.println("neededeventDates::-->" + convertEventDatesToStringDateTime(model.getEventStartDate()));

			LocalDateTime startdate = LocalDateTime.parse(convertEventDatesToStringDateTime(model.getEventStartDate()),
					formatter);
			LocalDateTime enddate = LocalDateTime.parse(convertEventDatesToStringDateTime(model.getEventEndDate()),
					formatter);

			event.setEventCreatedDate(convertToStringDateTime(currentDate));
			event.setEventStartDate(startdate);
			event.setEventEndDate(enddate);

			event.setTitle(model.getTitle());
			event.setInformation(model.getInfo());
			event.setImagePath(this.eventImagePath.toString());
			event.setEventImage(eventImage);
			event.setStatus("unread");
			event.setEmail(user.getEmail());
			event.setFirstName(user.getFirstName());
			event.setPfPath(user.getPfPath());
			event.setUserPicName(user.getFileName());
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			Events eventId = eventRepository.save(event);

			return eventId.getEventId();
		} catch (Exception e) {
			throw new FileStorageException("Could not store file " + eventImage + ". Please try again!", e);
		}
	}

	public Date StringToDate(String eventDates) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		Date date = formatter.parse(eventDates);

		System.out.println("Date object value: " + date);

		return date;
	}

	@Override
	public long updateEvent(long empId, MultipartFile file, long eventId, UpdateEventModel updateEventModel) {
		String eventImage = StringUtils.cleanPath(file.getOriginalFilename());
		User user = eventRepository.findUserByEmpId(empId);

		try {
			if (eventImage.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + eventImage);
			}
			Path targetLocation = this.eventImagePath.resolve(eventImage);
			System.out.println("check::" + updateEventModel.getEventStartDate());

			Events event = eventRepository.findByeventId(eventId);

			LocalDateTime currentDate = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			System.out.println("startDate Format::--->" + updateEventModel.getEventStartDate());

			Date returnedStartDate = StringToDate(updateEventModel.getEventStartDate());

			Date returnedEndDate = StringToDate(updateEventModel.getEventEndDate());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String neededStartDate = sdf.format(returnedStartDate);

			String neededEndtDate = sdf.format(returnedEndDate);

			System.out.println("NeededDate::" + neededStartDate);

			LocalDateTime startdate = LocalDateTime.parse(neededStartDate, formatter);
			LocalDateTime enddate = LocalDateTime.parse(neededEndtDate, formatter);

			event.setEventCreatedDate(convertToStringDateTime(currentDate));
			event.setEventStartDate(startdate);
			event.setEventEndDate(enddate);

			event.setTitle(updateEventModel.getTitle());
			event.setInformation(updateEventModel.getInfo());
			event.setImagePath(this.eventImagePath.toString());
			event.setEventImage(eventImage);
			event.setStatus("unread");
			event.setEmail(user.getEmail());
			event.setFirstName(user.getFirstName());
			event.setPfPath(user.getPfPath());
			event.setUserPicName(user.getFileName());
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			Events UpdateEventId = eventRepository.save(event);

			return UpdateEventId.getEventId();

//			return eventId.getEventId();
		} catch (Exception e) {
			throw new FileStorageException("Could not store file " + eventImage + ". Please try again!", e);
		}
	}

	@Override
	public long countByStatus(String status) {
		return eventRepository.countByStatus(status);
	}

	@Override
	public int updateAsUnread(String status, long noticationId) {
		return eventRepository.updateAsUnread(status, noticationId);
	}

	@Override
	public long getTotalCount() {
		return eventRepository.count();
	}

	@Override
	public int updateEvents(String status, long id) {
		return eventRepository.updateAsUnread(status, id);
	}

	@Override
	public Events eventUpdate(Long eventId, EventModel model) {
		Events event = eventRepository.findByeventId(eventId);

		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

		LocalDateTime startdate = LocalDateTime.parse(model.getEventStartDate(), formatter);
		LocalDateTime enddate = LocalDateTime.parse(model.getEventEndDate(), formatter);

		event.setEventStartDate(startdate);
		event.setEventEndDate(enddate);
		event.setEventCreatedDate(convertToStringDateTime(currentDate));
//		event.setEventImage(model.getEventImage());
		event.setInformation(model.getInfo());
		event.setTitle(model.getTitle());
		final Events updatedEvent = eventRepository.save(event);
		return updatedEvent;
	}

	@Override
	public List<EventModel> findLiveEvents() {
		List<Events> liveEvents = new ArrayList<Events>();
		List<EventModel> listModuls = new ArrayList<>();
		liveEvents = eventRepository.getLiveEvents();
//		List<EventModel> listModuls1 = new ArrayList<>();

		for (Events list : liveEvents) {
			EventModel m = new EventModel();
			m.setEventId(list.getEventId());
			m.setEventEndDate(convertToStringDateTime(list.getEventEndDate()));
			m.setEventStartDate(convertToStringDateTime(list.getEventStartDate()));
			m.setEventImage(list.getEventImage());
			m.setImagePath(list.getImagePath());
			m.setInfo(list.getInformation());
			m.setTitle(list.getTitle());
			listModuls.add(m);
		}
//		listModuls1.addAll(listModuls);
		return listModuls;

	}

	@Override
	public List<EventModel> getArchiveEvent() {
		List<Events> archiveEvents = new ArrayList<Events>();
		List<EventModel> listModuls = new ArrayList<>();
		archiveEvents = eventRepository.getArchiveEvent();
		// List<EventModel> listModuls1 = new ArrayList<>();

		for (Events list : archiveEvents) {
			EventModel m = new EventModel();
			m.setEventId(list.getEventId());
			m.setEventEndDate(convertToStringDateTime(list.getEventEndDate()));
			m.setEventStartDate(convertToStringDateTime(list.getEventStartDate()));
			m.setEventImage(list.getEventImage());
			m.setImagePath(list.getImagePath());
			m.setInfo(list.getInformation());
			m.setTitle(list.getTitle());
			listModuls.add(m);
		}
		// listModuls1.addAll(listModuls);
		return listModuls;

	}

	@Override
	public List<EventModel> getFutureEvents() {
		List<Events> futureEvents = new ArrayList<Events>();
		List<EventModel> listModuls = new ArrayList<>();
		futureEvents = eventRepository.getFutureEvents();
		// List<EventModel> listModuls1 = new ArrayList<>();

		for (Events list : futureEvents) {
			EventModel m = new EventModel();

			m.setEventId(list.getEventId());
			m.setTitle(list.getTitle());
			m.setInfo(list.getInformation());
			m.setEventEndDate(convertToStringDateTime(list.getEventEndDate()));
			m.setEventStartDate(convertToStringDateTime(list.getEventStartDate()));
			m.setEventImage(list.getEventImage());
			m.setImagePath(list.getImagePath());

			listModuls.add(m);
		}
		Collections.reverse(listModuls);
		return listModuls;
	}

	@Override
	public List<EventModel> displayTenEvents() {
		List<Events> displayEvents = new ArrayList<Events>();
		List<EventModel> listModules = new ArrayList<>();
		displayEvents = eventRepository.displayTenEvents();
		int i = 0;
		String differneceDate = null;
		for (Events list : displayEvents) {
			EventModel m = new EventModel();

			m.setEventId(list.getEventId());
			m.setTitle(list.getTitle());
			m.setInfo(list.getInformation());

			differneceDate = TimeUtills.convertToSpecificFormat(list.getEventCreatedDate());
			System.out.println("-----difference--------->" + differneceDate);

			m.setEventCreatedDate(differneceDate);

			listModules.add(m);
			i++;
		}

		return listModules;

	}

}
