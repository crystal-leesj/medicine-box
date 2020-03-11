package com.rxing.medicinebox.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import java.util.Optional;

public class NoIntentHandler implements RequestHandler {
    public NoIntentHandler() {
    }

    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("AMAZON.NoIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
        return input.getResponseBuilder().withSpeech("Ok, I will not set a reminder.").withShouldEndSession(false).build();
    }
}

