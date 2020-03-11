package com.rxing.medicinebox.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rxing.medicinebox.models.Medicine;

import java.util.Map;
import java.util.Optional;

public class GetSingleMedicationIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("GetSingleMedication"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        // persistent storage from dynamo db
        Map<String, Object> persistentStorage = handlerInput.getAttributesManager().getPersistentAttributes();

        // get slot values
        IntentRequest intentRequest = (IntentRequest) handlerInput.getRequestEnvelope().getRequest();
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();
        String drugName = slots.get("name").getValue();

        String speechText;
        if (persistentStorage.containsKey(drugName)) {
            String jsonGarbage = (String) persistentStorage.get(drugName);
            Gson gsonBuilder = new GsonBuilder().create();
            Medicine m = gsonBuilder.fromJson(jsonGarbage, Medicine.class);
            speechText = "You are taking: " + m.getDrugName();
        } else {
            speechText = "You don't have that medicine in your box.";
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
