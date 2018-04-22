package com.kartik.dos;

public interface RequestHandler {

    /**
     * Invoked when the request has started to be processed
     * Note, this should only be invoked if the calling thread is actually able to do processing.
     */
    void handleRequest();

}
