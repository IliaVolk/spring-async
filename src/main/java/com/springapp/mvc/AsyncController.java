package com.springapp.mvc;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

@RestController
@RequestMapping(value = "messages")
public class AsyncController {

    @Autowired
    private Logger logger;

    @Autowired
    private MessageSenderBean messageSenderBean;

    private int anotherRequestCount = 0;

    @RequestMapping(method = RequestMethod.GET)
    public DeferredResult<List<String>> getMessages(){
        logger.debug("controller start processing request #"+ ++messageSenderBean.count);
        return messageSenderBean.getMessages();
    }
    @RequestMapping(value = "/another", method = RequestMethod.GET)
    public String[] anotherRequest() throws InterruptedException {
        logger.debug("controller start processing another request #"+ ++anotherRequestCount);
        Thread.sleep(1000);
        logger.debug("controller resume processing another request #"+ anotherRequestCount);
        return new String[]{"answer for another request #"+ anotherRequestCount};
    }
}
