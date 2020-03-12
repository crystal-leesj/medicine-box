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
        StringBuilder speechText = new StringBuilder();
        speechText.append("Welcome to your virtual medicine box,  To use this prescription calling service, here's a list of available commands you can say");
        speechText.append("If you want to add a medication to your box, say add a medication");
        speechText.append("If you want to get details about a single medication, say get one med");
        speechText.append("If you want to get a list of all your medications in your box, say list every medications");
        speechText.append("If you want to create a reminder for your medication, say add a reminder");
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText.toString())
                .withShouldEndSession(false)
                .build();
    }
}
