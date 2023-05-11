package com.lizard.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author X
 * @version 1.0
 * @since 2023-05-03 23:08
 **/
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private LoggerHandler loggerHandler;

    @Async("logListenerExecutor")
    @Override
    public void asyncStartOneJob(String jobId) {
        loggerHandler.info("preparing start job {}...", jobId);
        try {
            long startTime = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(2);
            long endTime = System.currentTimeMillis();
            loggerHandler.info("job {} finished, cost: {}", jobId, endTime - startTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
            loggerHandler.info("job {} failed, cause: {}", jobId, e.toString());
        }
    }
}
