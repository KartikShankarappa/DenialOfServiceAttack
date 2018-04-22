package com.kartik.dos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDoSDefense extends AbstractConcurrencyFactorProvider implements DoSDefense {

	private final ExecutorService executorService;
	
	public ExecutorDoSDefense(int concurrencyFactor) {
		super(concurrencyFactor);
		this.executorService = Executors.newFixedThreadPool(concurrencyFactor);
	}
	
	
    @Override public void processRequest(final RequestHandler handler) {
        // TODO - handle via an ExecutorService taking into account the concurrency factor
    	
    	executorService.submit(new Runnable() { 
    		@Override
    		public void run() {
    			handler.handleRequest();
    		}
    	});
    }


    @Override public void stop() {
        // TODO - implement a graceful shutdown
    	executorService.shutdown();
    }
}
