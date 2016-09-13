package com.ikamobile.pa.service;

/**
 * Created by guest on 16/8/2.
 */
public interface ScheduledOrderService {
    void catchExOrder();
    void clearTimeOutOrderLock();
}
