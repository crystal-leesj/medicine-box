package com.rxing.medicinebox.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class GetAllMedicationsIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("GetAllMedications"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        // persistent storage from dynamo db
        Map<String, Object> persistentStorage = handlerInput.getAttributesManager().getPersistentAttributes();

        String speechText = "Here's a list of your medications. ";
        if (persistentStorage.isEmpty()) {
            speechText = "You don't have any meds";
        } else {
            Set<String> meds = persistentStorage.keySet();
            Iterator<String> it = meds.iterator();
            while (it.hasNext()) {
                speechText += it.next() + " ";
            }
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}