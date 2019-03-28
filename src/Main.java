import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	private static List<MonitoredData> data = new ArrayList<MonitoredData>();
	private static Map<String,Long> mapActivitiesAllDays = new LinkedHashMap<String,Long>();
	private static Map<String, Map<String, Long>> mapActivitiesPerDay = new LinkedHashMap<String,Map<String,Long>>();
	private static List<Time> timePerActivity = new ArrayList<Time>();
	private static Map<String,Long> mapTimeAllDays = new LinkedHashMap<String,Long>();
	
	public static void main(String[] args) {
		 try (Stream<String> stream = Files.lines(Paths.get("Activities.txt"))) {
		        stream.map(line -> line.split("\\t"))
		                .forEach(tokens -> data.add(new MonitoredData(tokens[0],tokens[2],tokens[4])));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		 
		System.out.println("Days of monitored data: "+data.stream().map(x -> x.getDay()).distinct().count());
		
		mapActivitiesAllDays=data.stream().map(x->x.getActivity()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(mapActivitiesAllDays);
		
		mapActivitiesPerDay=data.stream().collect(Collectors.groupingBy(MonitoredData::getDay,Collectors.groupingBy(MonitoredData::getActivity,Collectors.counting())));
		System.out.println(mapActivitiesPerDay);
		
		timePerActivity = data.stream().map(x->x.getDuration()).map(x->new Time(x)).collect(Collectors.toList());
		System.out.println(timePerActivity);
		
		mapTimeAllDays = data.stream().collect(Collectors.groupingBy(MonitoredData::getActivity,Collectors.summingLong(MonitoredData::getDuration)));
		for(String s: mapTimeAllDays.keySet())
			System.out.println(s + ": "+new Time(mapTimeAllDays.get(s)));
	}
}
