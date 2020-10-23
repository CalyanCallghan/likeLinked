package com.onpassive.onet.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

//import org.joda.time.Months;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.onet.model.Notification;
import com.onpassive.onet.repository.NotificationRepository;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	@Autowired
	private NotificationRepository notificationRepository;

	@GetMapping("/getNotifications")
	public List<Notification> getNotifications() {

		System.out.println("working");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Date d1 = null;
		Date d2 = null;
		Notification notification = new Notification();
		String dateStart = null;
		List<Notification> list = new ArrayList<Notification>();
		list = notificationRepository.findAll();

		System.out.println("*******" + list);

		int i = 0;
		for (Notification notify : list) {
			dateStart = notify.getSendDate();
			System.err.println("dateStart==>" + dateStart);
			try {

				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				d1 = format.parse(dateStart);
				d2 = (Date) format.parse(dtf.format(now).toString());
				System.err.println(d1 + "<-->" + d2);
				// in milliseconds
				long diff = d2.getTime() - d1.getTime();

				System.out.println("difference:: " + diff);

				long diffag = 0;

				long diffSeconds = diff / 1000 % 60;
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);
				long week = (diff / (24 * 60 * 60 * 1000)) / 7;
				long year = (diff / (24 * 60 * 60 * 1000)) / 365;

				long month = ((diff / (24 * 60 * 60 * 1000)) / 365) / 12;

				System.out.println(diffSeconds + "<---->" + diffMinutes + "<---->" + diffHours + "<---->" + diffDays
						+ "<---->" + week);

				/*
				 * 32<---->10<---->4<---->0<---->0 187832000 32<---->10<---->4<---->2<---->0
				 * 101432000 32<---->10<---->4<---->1<---->0
				 */

				// long month = d2.getMonth()-d1.getMonth();

				if (year != 0) {
					diffag = year;
					notification.setSendDate(diffag + "y");
				}

				else if (week != 0) {
					diffag = week;
					notification.setSendDate(diffag + "w");
				} else if (diffDays != 0) {
					diffag = diffDays;
					// System.out.println(diffag + " h");
					notification.setSendDate(diffag + "d");
				} else if (diffHours != 0) {
					diffag = diffHours;
					notification.setSendDate(diffag + "h");
				}

				else if (diffMinutes != 0) {
					diffag = diffMinutes;
					// System.out.print(diffag + " m");
					notification.setSendDate(diffag + "m");
				} else if (diffSeconds != 0) {
					diffag = diffSeconds;
					// System.out.print(diffag + " s");
					notification.setSendDate(diffag + "s");
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.get(i).setSendDate(notification.getSendDate());
			i++;
		}
		System.out.println("list of notification:::" + list.get(0).getMessage());
		Collections.reverse(list);
		return list;
	}

	// Getting the all List of count
	@GetMapping("/totalCount")
	public long getTotalCount() {
		long totalCount = 0;
		totalCount = notificationRepository.findAll().size();
		return totalCount;
	}

	// Getting the count of unread message
	@GetMapping("/unreadCount")
	public long getUnreadCount() {
		long unreadCount = 0;
		unreadCount = notificationRepository.countByStatus("unread");
		return unreadCount;
	}

	// update unreadCount
	@PutMapping("notificationUnread/{notificationId}")
	public int notificationReadByUser(@PathVariable("notificationId") long id, @RequestParam("status") String status) {
		int count = notificationRepository.updateAsUnread(status, id);
		return count;
	}

}
