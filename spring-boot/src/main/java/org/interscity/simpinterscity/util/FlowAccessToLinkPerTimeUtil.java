package org.interscity.simpinterscity.util;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.time.LocalDateTime.now;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.interscity.simpinterscity.util.file.FileManager;

public class FlowAccessToLinkPerTimeUtil {
	
	private static Map<Long, List<TupleCountTime>> accessPerTime = new HashMap<>();
	private static Integer index = 1;
	private static String outputFolder = "events-movment-wally-fixed";
	private static final Integer ENTERED_LINK = 1;
	private static final Integer LEFT_LINK = 2;
	private static final String FLOW_REPORT_PATH = "output/reports/flow/{{simulation}}/";
	
	
	private static void reader(File file, String delimiter, Boolean header) throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(file));
		String row = null;
		if (!header) {
			row = csvReader.readLine();
		}
		while ((row = csvReader.readLine()) != null) {
			String[] line = row.split(delimiter);
			if (line[1].equals("entered link") || line[1].equals("left link")) {
				List<TupleCountTime> counts = accessPerTime.get(parseLong(line[3]));
				Integer indecrement = (line[1].equals("entered link")) ? 1 : -1;
				if (counts == null) {
					counts = new ArrayList<>();
					counts.add(new TupleCountTime(indecrement, parseInt(line[0]), line[2], ENTERED_LINK));
				} else {
					Integer count = counts.get(counts.size() - 1).getCount() + indecrement;
					counts.add(new TupleCountTime(count, parseInt(line[0]), line[2], LEFT_LINK));
				}
				accessPerTime.put(parseLong(line[3]), counts);
			}
		}
		csvReader.close();
	}

	public static void generate(File file, String delimiter, String folder, Boolean header) throws IOException {
		StringBuilder content = new StringBuilder();
		reader(file, delimiter, header);
		File dir = new File(FLOW_REPORT_PATH.replace("{{simulation}}", folder));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (dir.listFiles().length == 0) {
			for (Entry<Long, List<TupleCountTime>> entry : accessPerTime.entrySet()) {
				content.append("time;vehicle;count\n");
				for (TupleCountTime count : entry.getValue()) {
					content.append(count.getTime())
					.append(";")
					.append(count.getVehicle())
					.append(";")
					.append(count.getCount()) 
					.append("\n");
				}			
				entry.getValue().clear();
				FileManager.writer(new File(FLOW_REPORT_PATH.replace("{{simulation}}", folder).concat(entry.getKey()+"-flow-access-to-link-per-time-"+now()+".csv")), content.toString());
				content = new StringBuilder();
			}
		}
		
	}
	
	private static class TupleCountTime {
		
		private Integer count;
		private Integer time;
		private String vehicle;
		private Integer event;
		
		public TupleCountTime(Integer count, Integer time, String vehicle, Integer event) {
			setCount(count);
			setTime(time);
			setVehicle(vehicle);
			setEvent(event);
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		public Integer getTime() {
			return time;
		}

		public void setTime(Integer time) {
			this.time = time;
		}

		public String getVehicle() {
			return vehicle;
		}

		public void setVehicle(String vehicle) {
			this.vehicle = vehicle;
		}

		public Integer getEvent() {
			return event;
		}

		public void setEvent(Integer event) {
			this.event = event;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((count == null) ? 0 : count.hashCode());
			result = prime * result + ((event == null) ? 0 : event.hashCode());
			result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
			result = prime * result + ((time == null) ? 0 : time.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TupleCountTime other = (TupleCountTime) obj;
			if (count == null) {
				if (other.count != null)
					return false;
			} else if (!count.equals(other.count))
				return false;
			if (event == null) {
				if (other.event != null)
					return false;
			} else if (!event.equals(other.event))
				return false;
			if (vehicle == null) {
				if (other.vehicle != null)
					return false;
			} else if (!vehicle.equals(other.vehicle))
				return false;
			if (time == null) {
				if (other.time != null)
					return false;
			} else if (!time.equals(other.time))
				return false;
			return true;
		}		
		
	}
}
