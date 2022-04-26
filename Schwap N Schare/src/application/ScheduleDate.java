package application;

import java.util.Scanner;

public class ScheduleDate {
//variables for schedule page contents declared 
	private int day,hour,minute,second;
	public static String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	public ScheduleDate(int day, int hour, int minute, int second) {
		setScheduleDate(day,hour,minute,second);
	}
	
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
		String[] time = sc.next().split(":");
		this.hour=Integer.parseInt(time[0]);
		this.minute = (time.length >= 2) ? Integer.parseInt(time[1]) : 0;
		this.second = (time.length >= 3) ? Integer.parseInt(time[2]) : 0;
		day_t = day_t.toLowerCase();
		char c1 = day_t.charAt(0);
		
		switch(c1) {
		case 's':
			if(day_t.length() >= 2)
				day = (day_t.charAt(1) == 'u') ? 0 : 6;
			else {
				System.out.println("Failed to read day");
				sc.close();
				return;
			}
			break;
		case 't':
			if(day_t.length() >= 2)
				day = (day_t.charAt(1) == 'u') ? 2 : 4;
			else {
				System.out.println("Failed to read day");
				sc.close();
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
			System.out.println("Failed to read day");
		}
		sc.close();
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
	
	
}
