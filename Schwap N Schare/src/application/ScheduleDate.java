package application;

import java.util.Scanner;

public class ScheduleDate {
//variables for schedule page contents declared 
	private int day,hour,minute,second;
	public static String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	public ScheduleDate(int day, int hour, int minute, int second) {
		setScheduleDate(day,hour,minute,second);
	}
	public ScheduleDate() {}
	
	public void setScheduleDate(int day, int hour, int minute, int second) {
		this.day=day;
		this.hour=hour;
		this.minute=minute;
		this.second=second;
	}
	public void setScheduleDate(int day,int seconds) {
		this.day=day;
		second = seconds % 60;
		minute = (int)((double)(seconds % 3600)/60.0);
		hour = (int)(((double)seconds)/3600.0);
	}
	public int getRealTime() {
		return hour*3600 + minute*60+second;
	}
	public int getDay() {
		return day;
	}
	public int getHour() {
		return hour;
	}
	public int getMinute() {
		return minute;
	}
	public int getSecond() {
		return second;
	}
	public void parseString(String s) {
		Scanner sc = new Scanner(s);
		String day_t = sc.next();
		String time = sc.next();
		sc.close();
		parseString(day_t,time);
	}
	public void parseString(String day_t, String s2) throws NumberFormatException, NullPointerException{
		String[] time = s2.split(":");
		this.hour=Integer.parseInt(time[0]);
		this.minute = (time.length >= 2) ? Integer.parseInt(time[1]) : 0;
		this.second = (time.length >= 3) ? Integer.parseInt(time[2]) : 0;
		day_t = day_t.toLowerCase();
		char c1 = day_t.charAt(0);
		
		switch(c1) {
		case 't':
			if(day_t.length() >= 2)
				day = (day_t.charAt(1) == 'h' || day_t.charAt(1) == 'r') ? 4 : 2;
			else {
				throw new NullPointerException();
			}
			break;
		case 's':
			if(day_t.length() >= 2)
				day = (day_t.charAt(1) == 'u') ? 0 : 6;
			else {
				System.err.println("Failed to read day");
				return;
			}
			break;
		case 'm':
			day = 1;
			break;
		case 'w':
			day = 3;
			break;
		case 'f':
			day = 5;
			break;
		case 'r':
			day = 4;
			break;
		default:
			System.err.println("Failed to read day");
		}
	}
	public String toString() {	
		return days[day]+" "+String.format("%02d", hour)+":"+String.format("%02d", minute)+":"+String.format("%02d", second);
	}
	public String getTime() {	
		return String.format("%02d", hour)+":"+String.format("%02d", minute)+":"+String.format("%02d", second);
	}
	public int compareTo(ScheduleDate date) {
		return Integer.compare(this.getRealTime(),date.getRealTime());
	}
	public static boolean testEquality(ScheduleDate a, ScheduleDate b) {
		return a.day == b.day && a.hour == b.hour && a.minute == b.minute && a.second == b.second;
	}
	
}
