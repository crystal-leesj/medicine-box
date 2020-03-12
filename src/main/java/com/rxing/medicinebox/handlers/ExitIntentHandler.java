package com.rxing.medicinebox.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;
import com.amazon.ask.request.Predicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ExitIntentHandler implements RequestHandler {
    private static final Logger log = LoggerFactory.getLogger(ExitIntentHandler.class);

    public ExitIntentHandler() {
    }

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("Exit"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return handlerInput.getResponseBuilder().withSpeech("Goodbye, talk to you tomorrow.").withShouldEndSession(true).build();
    }


}


//    in which it returns in the canHandle function, .getResponseBuilder with a .withShouldEndSession(true)