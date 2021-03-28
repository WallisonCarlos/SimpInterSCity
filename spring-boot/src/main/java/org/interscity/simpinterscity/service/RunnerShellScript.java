package org.interscity.simpinterscity.service;

import java.io.IOException;
import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RunnerShellScript {

	@Async
	public void runner(String file, String vars) throws IOException, InterruptedException {
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		Process process;
		if (isWindows) {
			process = Runtime.getRuntime().exec(new String[] { "cmd.exe", file, vars });
		} else {
			process = Runtime.getRuntime().exec(new String[] { "sh", file, vars });
		}
		StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
		Executors.newSingleThreadExecutor().submit(streamGobbler);
		int exitCode = process.waitFor();
		assert exitCode == 0;
	}
	
	@Async
	public void runner(String file) throws IOException, InterruptedException {
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		Process process;
		if (isWindows) {
			process = Runtime.getRuntime().exec(new String[] { "cmd.exe", file });
		} else {
			process = Runtime.getRuntime().exec(new String[] { "sh", file });
		}
		StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
		Executors.newSingleThreadExecutor().submit(streamGobbler);
		int exitCode = process.waitFor();
		assert exitCode == 0;
	}
}
