package com.kartik.dos;

public class DosDefenseFactory {

    public static DoSDefense createThreaded(int concurrencyFactor) {
        // TODO - construct an instance of the ThreadedDoSDefense with the given concurrencyFactory
    	return new ThreadedDoSDefense(concurrencyFactor);
    }

    public static DoSDefense createExecutor(final int concurrencyFactor) {
        // TODO - construct an instance of the ExecutorDoSDefense with the given concurrencyFactory
    	return new ExecutorDoSDefense(concurrencyFactor);
    }

}
