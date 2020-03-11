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

public class AddMedicationIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("AddMedication"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        // persistent storage from dynamo db
        Map<String, Object> persistentStorage = handlerInput.getAttributesManager().getPersistentAttributes();

        // get slot values
        IntentRequest intentRequest = (IntentRequest) handlerInput.getRequestEnvelope().getRequest();
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();
        String drugName =          slots.get("name").getValue();
        String doseAmount =        slots.get("doseAmount").getValue();
        String doseScale =         slots.get("doseScale").getValue();
        String startDate =         slots.get("start_Date").getValue();
        String endDate =           slots.get("end_Date").getValue();
        String frequencyPeriod =   slots.get("frequencyPeriod").getValue();
        String frequencyByPeriod = slots.get("frequencyByPeriod").getValue();

        // create medicine and jsonify object
        Medicine newMed = new Medicine(drugName, doseAmount, doseScale, startDate, endDate, frequencyPeriod, frequencyByPeriod);
        Gson gsonBuilder = new GsonBuilder().create();
        String newMedJson = gsonBuilder.toJson(newMed);

        // put attribute and save
        persistentStorage.put(drugName, newMedJson);
        handlerInput.getAttributesManager().setPersistentAttributes(persistentStorage);
        handlerInput.getAttributesManager().savePersistentAttributes();

        // create speech for return statement
        String speechText = String.format(
                "You added %s with a dosage of %s %s.  You are starting this medication on %s and ending on %s.  You are scheduled to take this %s a %s",
                drugName, doseAmount, doseScale, startDate, endDate, frequencyPeriod, frequencyByPeriod);
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
