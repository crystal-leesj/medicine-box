package com.rxing.medicinebox.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Permissions;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.interfaces.connections.ConnectionsResponse;
import com.amazon.ask.model.services.reminderManagement.*;
import com.amazon.ask.request.Predicates;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rxing.medicinebox.models.Medicine;
import com.rxing.medicinebox.utils.ReminderUtil;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CreateReminderIntentHandler implements RequestHandler {

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

        //TODO: Grab data from Medicine model, add Reminder data



        // create speech for return statement
        String speechText = "Reminders??";
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
