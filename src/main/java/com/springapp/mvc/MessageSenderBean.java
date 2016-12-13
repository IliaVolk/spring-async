package com.springapp.mvc;


import org.slf4j.Logger;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.PostConstruct;
import java.util.*;


@Scope("singleton")
@Component
public class MessageSenderBean {


    int count = 0;
    @Autowired
    private MessageDao messageDao;

    @Autowired
    private Logger logger;

    @PostConstruct
    public void postConstruct(){
        timer = new Timer("Async Answerer");
        requests = Collections.synchronizedList(new LinkedList<>());
    }

    private Timer timer;

    private class AnswerRequestsTimerTask extends TimerTask {
        @Override
        public void run() {
            isRequestToDatabasePreparing = false;
            answerForRequests();
        }
    }
    private class DeferredResultOnCompletion implements Runnable{
        private DeferredResult<List<String>> deferredResult;

        public DeferredResultOnCompletion(DeferredResult<List<String>> deferredResult){
            this.deferredResult = deferredResult;
        }
        @Override
        public void run() {
            requests.remove(deferredResult);
        }
    }

    private volatile List<DeferredResult<List<String>>> requests;

    private volatile boolean isRequestToDatabasePreparing;

    public synchronized DeferredResult<List<String>> getMessages(){
        DeferredResult<List<String>> deferredResult =
                new DeferredResult<>((long) 10000, Arrays.asList("result on end timeout "+count));

        logger.debug("### processing request #"+count);
        deferredResult.onCompletion(new DeferredResultOnCompletion(deferredResult));
        requests.add(deferredResult);

        if (!isRequestToDatabasePreparing){
            isRequestToDatabasePreparing = true;
            timer.schedule(new AnswerRequestsTimerTask(), 3000);
        }
        logger.debug("requests size: ");
        System.out.println(requests.size());

        return deferredResult;
    }


    public void answerForRequests(){
        logger.debug("### starting answering");

        try {
            List<String> messages = messageDao.getMessages();
            messages.add("answer for request #"+ count);
            requests.forEach(r -> r.setResult(messages));

        } catch (Throwable e) {
            e.printStackTrace();
        }


        logger.debug("### after answering");
    }

}
