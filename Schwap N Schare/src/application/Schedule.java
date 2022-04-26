package application;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Schedule {
//schedule page functionality
//size of the schedule page content declared 
//function to add event into the schedule page with the variables declared
//function to remove any event 
//function to write to a file for storing event details 
//function to read from a file that stores user's event details 
	private ArrayList<ScheduleEvent>[] data;
	private String filename;
	public Schedule() {
		allocateData();
	}
	public Schedule(String filename) {
		this.filename=filename;
		allocateData();
		try {
			readFromFile();
		}catch(Exception e) {
			
		}
	}
	public void setFilename(String filename) {
		this.filename=filename;
	}
	public String getFilename() {
		return filename;
	}
	private void allocateData() {
		data = new ArrayList[7];
		for(int i=0; i <7; i++)
			data[i] = new ArrayList<ScheduleEvent>();
	}
	public void addEvent(String name,int day, int hour, int minute, int second,String location) {
		ScheduleDate date = new ScheduleDate(day,hour,minute,second);
		ScheduleEvent e = new ScheduleEvent(name,date,location);
		addEvent(e);
	}
	public void addEvent(String name,ScheduleDate date,String location) {
		ScheduleEvent e = new ScheduleEvent(name,date,location);
		addEvent(e);
	}
	public void addEvent(ScheduleEvent e) {
		readFromFile();
		int day = e.getDate().getDay();
		if(!contains(e,day)) {
			data[day].add(e);
			Collections.sort(data[day]);
		}
		writeToFile();
	}
	private void addEvent_concurrent(ScheduleEvent e) {
		int day = e.getDate().getDay();
		data[day].add(e);
	}
	public void removeEvent(ScheduleEvent e){
		int day = e.getDate().getDay();
		if(!remove(e,day))
			System.err.println("Remove failed: could not find event");
		else
			writeToFile();
	}
	private int binarySearch(ScheduleEvent e, int day, int start, int end) {
		if(end >= start) {
			int mid = (end+start)/2;
			switch(e.compareTo(data[day].get(mid))) {
				case 0:
					if (ScheduleEvent.testEquality(data[day].get(mid),e))
						return mid;
					break;
				case 1:
					return binarySearch(e,day,start,mid-1);
				case -1:
					return binarySearch(e,day,mid+1,end);
			}
		}
		return -1;
	}
	private boolean contains(ScheduleEvent e, int day) {
		return binarySearch(e,day,0,data[day].size()-1) != -1;
	}
	private boolean remove(ScheduleEvent e, int day) {
		int index = binarySearch(e,day,0,data[day].size()-1);
		if (index != -1) {
			data[day].remove(index);
			return true;
		}
		return false;
	}
	public ArrayList<ScheduleEvent> getEvents(int day){
		if(day < 0 || day > 6) {
			System.err.println("getEvents(day): day must be within [0,6]");
			System.exit(-53);
		}
		return data[day];
	}
	public void writeToFile() {
		try {
			File file = new File(System.getProperty("user.dir") +"/src/"+ filename);
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
	public void readFromFile() {
		//clear array
		allocateData();
		
		Scanner sc;
		File file;
		try {
			file = new File(System.getProperty("user.dir") +"/src/"+ filename);
			sc = new Scanner(file);
			
			while(sc.hasNextLine()) {
				String line1,line2,line3;
				line1=sc.nextLine();
				line2=sc.nextLine();
				line3=sc.nextLine();
				ScheduleDate sd = new ScheduleDate();
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
