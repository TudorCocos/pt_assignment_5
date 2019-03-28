import java.sql.Time;

public class MonitoredData {
	private String startTime, endTime, activity;

	public MonitoredData(String startTime, String endTime, String activity) {
		this.startTime=startTime;
		this.endTime=endTime;
		this.activity=activity;
	}
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public String getDay() {
		return this.startTime.split(" ")[0];
	}
	
	public long getDuration() {
		int hours1, hours2, minutes1, minutes2, seconds1, seconds2;
		hours1=Integer.parseInt(this.getStartTime().split(" ")[1].split(":")[0]);
		hours2=Integer.parseInt(this.getEndTime().split(" ")[1].split(":")[0]);
		minutes1=Integer.parseInt(this.getStartTime().split(" ")[1].split(":")[1]);
		minutes2=Integer.parseInt(this.getEndTime().split(" ")[1].split(":")[1]);
		seconds1=Integer.parseInt(this.getStartTime().split(" ")[1].split(":")[2]);
		seconds2=Integer.parseInt(this.getEndTime().split(" ")[1].split(":")[2]);
		int rezHours=hours2-hours1;
		int rezMinutes=minutes2-minutes1;
		int rezSeconds=seconds2-seconds1;
		Time time = new Time(rezHours, rezMinutes, rezSeconds);
		return time.getTime();
	}
	
	public String toString() {
		return this.startTime+" 	"+this.endTime+" 	"+this.activity+"\n";
	}
}
