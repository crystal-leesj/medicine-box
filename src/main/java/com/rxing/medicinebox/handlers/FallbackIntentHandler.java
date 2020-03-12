package com.rxing.medicinebox.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import java.util.Optional;

public class FallbackIntentHandler implements RequestHandler {
    public FallbackIntentHandler() {
    }

    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("AMAZON.FallbackIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
        return input.getResponseBuilder().withSpeech("Hmm. I'm not sure how to help you with that. This skill can set reminders for your medication.").withShouldEndSession(false).build();
    }
}
