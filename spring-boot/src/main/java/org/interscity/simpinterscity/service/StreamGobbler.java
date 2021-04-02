package org.interscity.simpinterscity.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.function.Consumer;

import reactor.core.publisher.FluxSink;

public class StreamGobbler implements Runnable {
	
	private InputStream inputStream;
	private FluxSink fluxSink;

	public StreamGobbler(InputStream inputStream, FluxSink fluxSink) {
        this.inputStream = inputStream;
        this.fluxSink = fluxSink;
    }

    @Override
    public void run() {
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
