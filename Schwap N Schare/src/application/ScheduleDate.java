package application;

import java.util.Scanner;

public class ScheduleDate {
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
		this.minute=Integer.parseInt(time[1]);
		this.second=Integer.parseInt(time[2]);
		
		if(day_t.equals( "Sunday")) {
			day=0;
		}else if(day_t.equals("Monday")) {
			day=1;
		}else if(day_t.equals("Tuesday")) {
			day=2;
		}else if(day_t.equals("Wednesday")) {
			day=3;
		}else if(day_t.equals("Thursday")) {
			day=4;
		}else if(day_t.equals("Friday")) {
			day=5;
		}else if(day_t.equals("Saturday")) {
			day=6;
		}else {
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
