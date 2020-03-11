package com.rxing.medicinebox.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import java.util.Optional;

public class CancelStopRequestHandler implements RequestHandler {
    public CancelStopRequestHandler() {
    }

    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("AMAZON.CancelIntent")) || input.matches(Predicates.intentName("AMAZON.StopIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
        return input.getResponseBuilder().withSpeech("Goodbye").withShouldEndSession(true).build();
    }
}
