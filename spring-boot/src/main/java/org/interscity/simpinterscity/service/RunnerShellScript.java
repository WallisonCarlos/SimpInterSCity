package org.interscity.simpinterscity.service;

import java.io.*;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.FluxSink;

@Getter
@Setter
@Service
public class RunnerShellScript {

	private FluxSink fluxSink;

	public void runner(String file, String vars) throws IOException, InterruptedException {
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		Process process;
		if (isWindows) {
			process = Runtime.getRuntime().exec(new String[] { "cmd.exe", file, vars });
		} else {
			process = Runtime.getRuntime().exec(new String[] { "sh", file, vars });
		}
		StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), fluxSink);
		Executors.newSingleThreadExecutor().submit(streamGobbler);
		int exitCode = process.waitFor();
		assert exitCode == 0;
	}
	
	public void runner(String file) throws IOException, InterruptedException {
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		Process process;
		if (isWindows) {
			process = Runtime.getRuntime().exec(new String[] { "cmd.exe", file });
		} else {
			process = Runtime.getRuntime().exec(new String[] { "sh", file });
		}
		StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), fluxSink);
		Executors.newSingleThreadExecutor().submit(streamGobbler);
		int exitCode = process.waitFor();
		assert exitCode == 0;
	}

	public void putOutput(InputStream inputStream) {
		BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                line += "\n";
                fluxSink.next(line);
            }
        } catch (IOException e) {
            fluxSink.next(e.getMessage());
        }
	}
}
