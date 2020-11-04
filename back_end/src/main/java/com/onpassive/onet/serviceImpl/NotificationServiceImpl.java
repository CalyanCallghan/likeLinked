package com.onpassive.onet.serviceImpl;

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

import com.onpassive.onet.entity.Notification;
import com.onpassive.onet.repository.NotificationRepository;
import com.onpassive.onet.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationRepository notificationRepository;

	@Override
	public List<Notification> getNotifications() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Date d1 = null;
		Date d2 = null;
		Notification notification = new Notification();
		String dateStart = null;
		List<Notification> list = new ArrayList<Notification>();
		list = notificationRepository.findAll();

		int i = 0;
		for (Notification notify : list) {
			dateStart = notify.getSendDate();
			try {

				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				d1 = format.parse(dateStart);
				d2 = (Date) format.parse(dtf.format(now).toString());
				long diff = d2.getTime() - d1.getTime();

				long diffag = 0;

				long diffSeconds = diff / 1000 % 60;
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);
				long week = (diff / (24 * 60 * 60 * 1000)) / 7;
				long year = (diff / (24 * 60 * 60 * 1000)) / 365;

				if (year != 0) {
					diffag = year;
					notification.setSendDate(diffag + "y");
				}

				else if (week != 0) {
					diffag = week;
					notification.setSendDate(diffag + "w");
				} else if (diffDays != 0) {
					diffag = diffDays;
					notification.setSendDate(diffag + "d");
				} else if (diffHours != 0) {
					diffag = diffHours;
					notification.setSendDate(diffag + "h");
				}

				else if (diffMinutes != 0) {
					diffag = diffMinutes;
					notification.setSendDate(diffag + "m");
				} else if (diffSeconds != 0) {
					diffag = diffSeconds;
					notification.setSendDate(diffag + "s");
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
			list.get(i).setSendDate(notification.getSendDate());
			i++;
		}
		Collections.reverse(list);
		return list;
	}

}
