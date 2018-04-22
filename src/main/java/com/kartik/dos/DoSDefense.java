package com.kartik.dos;

public interface DoSDefense {

    /**
     * Ensures there are resources to handle the request and if so invokes the appropriate method on {@code handle}
     * @param handler to invoke when there are resources to handle the request
     */
    void processRequest(RequestHandler handler);

    /**
     * Stop any threads, called on system shutdown
     */
    void stop();
}
