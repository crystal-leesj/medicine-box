package com.rxing.medicinebox.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

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

        String speechText;
        if (persistentStorage.isEmpty()) {
            speechText = "Currently your medicine box is empty.  Say add a medication to put something into your box or say help me to learn more.";
        } else {
            speechText = "Here are the medications currently in your box.  You are taking ";
            Set<String> meds = persistentStorage.keySet();
            for (String med : meds) {
                speechText += med + ", ";
            }
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(speechText.toString())
                .withShouldEndSession(false)
                .build();
    }
}
