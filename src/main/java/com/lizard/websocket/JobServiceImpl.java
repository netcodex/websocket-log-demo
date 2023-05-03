package com.lizard.websocket;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author X
 * @version 1.0
 * @since 2023-05-03 23:08
 **/
@Service
public class JobServiceImpl implements JobService{
    @Async("logListenerExecutor")
    @Override
    public void asyncStartOneJob(String jobId) {
        LoggerDelegate.info("preparing start job {}...", jobId);
        try {
            long startTime = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(2);
            long endTime = System.currentTimeMillis();
            LoggerDelegate.info("job {} finished, cost: {}", jobId, endTime - startTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
            LoggerDelegate.error("job {} failed, cause: {}", jobId, e.toString());
        }
    }
}
