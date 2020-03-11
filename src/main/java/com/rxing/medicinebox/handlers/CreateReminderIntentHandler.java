package com.rxing.medicinebox.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rxing.medicinebox.models.Medicine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CreateReminderIntentHandler implements RequestHandler {
    public CreateReminderIntentHandler(){}

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("CreateReminder"));
    }

    //"create reminder"
    //Alexa "for what medication?"
    //"tylenol"
    //Alexa " Confirmation"
    //TODO: calculate the actual time given by the 24hours / #
    //take once a day
    //TODO: predetermine what time starts, add to Medicine the current time or say that all medications start at a predefined time
    // 1800
    //grab from medicine, frequencyByPeriod & doseAmount

    //output on the reminder "take {doseAmount} of {name} by {time}
    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        Request request = handlerInput.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot medicationName = slots.get("name");
        if (medicationName == null) {
            return this.createResponse(handlerInput, "Please provide a medication to lookup.", false);
        } else {
            Medicine medMatch = this.lookupMed(medicationName.getValue(), handlerInput);
            String speechText;
            if (medMatch == null) {
                speechText = String.format("Couldn't find any medication called %s", medicationName.getValue());
                return this.createResponse(handlerInput, speechText, true);
            } else {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("medicine", medMatch);
                attributes.put("previousIntent", "CreateReminder");
                handlerInput.getAttributesManager().setSessionAttributes(attributes);
                speechText = String.format("%s beginning on %s. Would you like me to remind you when to take it?", medMatch.getDrugName(), medMatch.getStartDate());
                return this.createResponse(handlerInput, speechText, false);
            }
        }
    }
    private Optional<Response> createResponse(HandlerInput input, String speechText, boolean endSession) {
        return input.getResponseBuilder().withSimpleCard("Channel Guide", speechText).withSpeech(speechText).withShouldEndSession(endSession).build();
    }

    private Medicine lookupMed(String med , HandlerInput handlerInput) {
        Map<String, Object> persistentStorage = handlerInput.getAttributesManager().getPersistentAttributes();
        if (persistentStorage.containsKey(med)) {
            String jsonGarbage = (String) persistentStorage.get(med);
            Gson gsonBuilder = new GsonBuilder().create();
            return gsonBuilder.fromJson(jsonGarbage, Medicine.class);
        }
        return null;
    }
}
