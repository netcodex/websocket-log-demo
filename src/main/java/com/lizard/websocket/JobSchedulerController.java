package com.lizard.websocket;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author X
 * @version 1.0
 * @since 2023-05-03 17:33
 **/
@RestController
public class JobSchedulerController {
    @Autowired
    private JobService jobService;

    @Autowired
    private LoggerHandler loggerHandler;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/log")
    public String batchStartJobs() {
        List<String> jobIds = Arrays.asList("1001", "1002", "1003");

        loggerHandler.info("batch job started");

        for (String jobId : jobIds) {
            jobService.asyncStartOneJob(jobId);
        }

        loggerHandler.info("batch job finished");
        return "success";
    }
}
