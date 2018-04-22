package com.kartik.dos;

abstract public class AbstractConcurrencyFactorProvider implements ConcurrencyFactorProvider {
	
    private final int concurrencyFactor;

    protected AbstractConcurrencyFactorProvider(int concurrencyFactor) {
        this.concurrencyFactor = concurrencyFactor;
    }
    
    /**
     * A getter method to obtain the concurrency factor
     */
    @Override public int getConcurrencyFactor() {
        return concurrencyFactor;
    }

}
