package com.kartik.dos;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadedDoSDefense extends AbstractConcurrencyFactorProvider implements DoSDefense {
	
	// a list to maintain the threads
	private final ArrayList<Thread> threads;		
	
	// A blocking queue to deal with the incoming requests.
	private final BlockingQueue<RequestHandler> requests;
	
	public ThreadedDoSDefense(int concurrencyFactor) {
		super(concurrencyFactor);
		
		this.threads = new ArrayList<Thread>(concurrencyFactor);
		this.requests = new LinkedBlockingQueue<RequestHandler>(concurrencyFactor);
		
		for (int i =  0; i < concurrencyFactor; i++) {
			threads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					while (!Thread.currentThread().isInterrupted()) {
						try {
							RequestHandler handler = requests.poll(100, TimeUnit.MILLISECONDS);
							if (handler != null) {
								handler.handleRequest();
							}
						}	catch (InterruptedException ie) {
							Thread.currentThread().interrupt();
						}
					}
				}
			})) ;
		}
		for (int i = 0; i < threads.size() ; i++) {
			threads.get(i).start();
		}
	}
	

    @Override public synchronized void processRequest(RequestHandler handler) {
        // TODO - handle via a thread pool taking into account the concurrency factor
    	
    	try  {
    		requests.put(handler);
    	}	catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
    	}
    }


    @Override public synchronized void stop() {
        // TODO - implement a graceful shutdown
    	
    	for (int i = 0; i < threads.size(); i++) {
    		threads.get(i).interrupt();
    	}
    }
}
