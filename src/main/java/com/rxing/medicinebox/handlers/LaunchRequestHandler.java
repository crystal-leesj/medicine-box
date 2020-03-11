package com.rxing.medicinebox.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.requestType(LaunchRequest.class));
    }

    public Optional<Response> handle(HandlerInput handlerInput) {

        String speechText = "I am opening your medicine box.  How can I assist you?";
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
