package org.interscity.simpinterscity.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManagerCsv {

	public static List<String[]> reader(File file, String delimiter, Boolean header) throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(file));
		List<String[]> fileReaded = new ArrayList<>();
		String row = null;
		if (!header) {
			row = csvReader.readLine();
		}
		while ((row = csvReader.readLine()) != null) {
			String[] line = row.split(delimiter);
			fileReaded.add(line);
		}
		csvReader.close();
		return fileReaded;
	}
	
}
