package com.onpassive.onet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtills {
	public static String convertToSpecificFormat(String dateStart) {
		String specificFormat = null;
		try {

			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			Date d1 = format.parse(dateStart);
			Date d2 = (Date) format.parse(dtf.format(now).toString());
			
			long diff = d2.getTime() - d1.getTime();
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
			long week = (diff / (24 * 60 * 60 * 1000)) / 7;
			long year = (diff / (24 * 60 * 60 * 1000)) / 365;

			if (year != 0) {
				specificFormat = year + "y";
			} else if (week != 0) {
				specificFormat = week + "W";
			} else if (diffDays != 0) {
				specificFormat = diffDays + "d";
			} else if (diffHours != 0) {
				specificFormat = diffHours + "h";
			}else if (diffMinutes != 0) {
				specificFormat = diffMinutes + "m";
			} else if (diffSeconds != 0) {
				specificFormat = diffSeconds + "s";
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return specificFormat;
	}
	
	public static String convertToSpecificFormatLocalDateTime(LocalDateTime localDateTime) {
		String specificFormat = null;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
			String formatDateTime =localDateTime.format(formatter);
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			Date d1 = format.parse(formatDateTime);
			Date d2 = (Date) format.parse(dtf.format(now).toString());
			
			long diff = d2.getTime() - d1.getTime();
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
			long week = (diff / (24 * 60 * 60 * 1000)) / 7;
			long year = (diff / (24 * 60 * 60 * 1000)) / 365;

			if (year != 0) {
				specificFormat = year + "y";
			} else if (week != 0) {
				specificFormat = week + "W";
			} else if (diffDays != 0) {
				specificFormat = diffDays + "d";
			} else if (diffHours != 0) {
				specificFormat = diffHours + "h";
			}else if (diffMinutes != 0) {
				specificFormat = diffMinutes + "m";
			} else if (diffSeconds != 0) {
				specificFormat = diffSeconds + "s";
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return specificFormat;
	}
}
