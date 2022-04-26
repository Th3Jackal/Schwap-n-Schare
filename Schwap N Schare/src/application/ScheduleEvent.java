package application;

public class ScheduleEvent implements Comparable<ScheduleEvent>{
//variables for the user's input event details declared 
	private String name;
	private ScheduleDate date;
	private String location;
	public ScheduleEvent(String name,ScheduleDate date,String location) {
		this.name=name;
		this.date=date;
		this.location=location;
	}
	public void setName(String name) {this.name=name;}
	public String getName() {return name;}
	public void setDate(ScheduleDate date) {this.date=date;}
	public ScheduleDate getDate() {return date;}
	public void setLocation(String location) {this.location=location;}
	public String getLocation() {return location;}
	@Override
	public int compareTo(ScheduleEvent e) {
		return date.compareTo(e.getDate());
	}
	public static boolean testEquality(ScheduleEvent a, ScheduleEvent b) {
		return a.name.equalsIgnoreCase(b.name) && ScheduleDate.testEquality(a.getDate(), b.getDate()) && a.location.equalsIgnoreCase(b.location);
	}
}
