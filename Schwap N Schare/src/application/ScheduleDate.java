package application;

public class ScheduleDate {
	private int day,hour,minute,second;
	
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

	public int compareTo(ScheduleDate date) {
		return Integer.compare(this.getRealTime(),date.getRealTime());
	}
	
	
}
