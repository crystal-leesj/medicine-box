package com.rxing.medicinebox.handlers;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class ListAllCommandsIntentHandler implements RequestHandler {


    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("ListAllCommands"));

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = "Welcome to your Medring service, here is a list of commands you can use. Add medication. You " +
                "can learn details about a certain medication by saying get a single med. You can get a list of all your " +
                "medications by saying list all meds.";
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
