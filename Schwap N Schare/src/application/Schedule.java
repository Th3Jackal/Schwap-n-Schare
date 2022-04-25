package application;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
	private void addEvent_concurrent(ScheduleEvent e) {
		int day = e.getDate().getDay();
		data[day].add(e);
	}
	public void addEvent(ScheduleEvent e) {
		int day = e.getDate().getDay();
		data[day].add(e);
		Collections.sort(data[day]);
	}
	public void removeEvent(ScheduleEvent e){
		data[e.getDate().getDay()].remove(e);
	}
	ArrayList<ScheduleEvent> getEvents(int day){
		if(day < 0 || day > 6) {
			System.out.print("getEvents(day): day must be within [0,6]\n");
			System.exit(-53);
		}
		return data[day];
	}
	public void writeToFile(String filename) {
		try {
			File file = new File(System.getProperty("user.dir") +"/"+ filename);
			FileWriter writer = new FileWriter(file);
			for(int i=0; i<7; i++) {
				for(ScheduleEvent se : getEvents(i)) {
					writer.write(se.getName()+"\n");
					writer.write(se.getDate().toString()+"\n");
					writer.write(se.getLocation()+"\n");
				}
			}
		    writer.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void readFromFile(String filename) {
		Scanner sc;
		File file;
		try {
			file = new File(System.getProperty("user.dir") +"/"+ filename);
			sc = new Scanner(file);
			
			while(sc.hasNextLine()) {
				String line1,line2,line3;
				line1=sc.nextLine();
				line2=sc.nextLine();
				line3=sc.nextLine();
				ScheduleDate sd = new ScheduleDate(0,0,0,0);
				sd.parseString(line2);
				ScheduleEvent se = new ScheduleEvent(line1,sd,line3);
				addEvent_concurrent(se);
			}
			for(int i=0; i < 7; i++)
				Collections.sort(data[i]);
			sc.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}