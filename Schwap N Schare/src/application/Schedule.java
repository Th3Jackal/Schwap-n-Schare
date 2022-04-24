package application;

import java.util.ArrayList;
import java.util.Collections;

public class Schedule {
	private ArrayList<ScheduleEvent>[] data;
	public Schedule() {
		data = new ArrayList[7];
		for(int i=0; i <7; i++)
			data[i] = new ArrayList<ScheduleEvent>();
	}
	public void addEvent(String name,int day, int hour, int minute, int second,String location) {
		ScheduleDate date = new ScheduleDate(day,hour,minute,second);
		ScheduleEvent e = new ScheduleEvent(name,date,location);
		data[day].add(e);
		Collections.sort(data[day]);
	}
	public void addEvent(String name,ScheduleDate date,String location) {
		ScheduleEvent e = new ScheduleEvent(name,date,location);
		int day = e.getDate().getDay();
		data[day].add(e);
		Collections.sort(data[day]);
	}
	public void addEvent(ScheduleEvent e) {
		int day = e.getDate().getDay();
		data[day].add(e);
		Collections.sort(data[day]);
	}
	ArrayList<ScheduleEvent> getEvents(int day){
		if(day < 0 || day > 6) {
			System.out.print("getEvents(day): day must be within [0,6]\n");
			System.exit(-53);
		}
		return data[day];
	}
}
