package com.rxing.medicinebox.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Permissions;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.services.reminderManagement.*;
import com.amazon.ask.request.Predicates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rxing.medicinebox.models.Medicine;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CreateReminderIntentHandler implements RequestHandler {
    private ReminderResponse createReminder(HandlerInput input, String locale, String reminderLabel) {

        SpokenText spokenText = SpokenText.builder()
                .withText(reminderLabel)
                .withLocale(locale)
                .build();

        AlertInfoSpokenInfo alertInfoSpokenInfo = AlertInfoSpokenInfo.builder()
                .addContentItem(spokenText)
                .build();

        AlertInfo alertInfo = AlertInfo.builder()
                .withSpokenInfo(alertInfoSpokenInfo)
                .build();

        LocalDateTime triggerTime = LocalDateTime.now().plusSeconds(5);

//        Recurrence recurrence = Recurrence.builder()
////                .addByDayItem(RecurrenceDay.FR)
////                .withFreq(RecurrenceFreq.WEEKLY)
////                .build();

        Trigger trigger = Trigger.builder()
                .withType(TriggerType.SCHEDULED_ABSOLUTE)
                .withScheduledTime(triggerTime)

                .withTimeZoneId("America/Los_Angeles")
                .build();

        PushNotification pushNotification = PushNotification.builder()
                .withStatus(PushNotificationStatus.ENABLED)
                .build();

        ReminderRequest reminderRequest = ReminderRequest.builder()
                .withAlertInfo(alertInfo)
                .withRequestTime(OffsetDateTime.now())
                .withTrigger(trigger)
                .withPushNotification(pushNotification)
                .build();

        return input.getServiceClientFactory().getReminderManagementService().createReminder(reminderRequest);
    }
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
        // persistent storage from dynamo db
//        Map<String, Object> persistentStorage = handlerInput.getAttributesManager().getPersistentAttributes();
//
//        // get slot values
//        IntentRequest intentRequest = (IntentRequest) handlerInput.getRequestEnvelope().getRequest();
//        Map<String, Slot> slots = intentRequest.getIntent().getSlots();
//        String drugName = slots.get("name").getValue();
//        String doseAmount = slots.get("doseAmount").getValue();
//        String doseScale = slots.get("doseScale").getValue();
//        String startDate = slots.get("start_Date").getValue();
//        String endDate = slots.get("end_Date").getValue();
//        String frequencyPeriod = slots.get("frequencyPeriod").getValue();
//        String frequencyByPeriod = slots.get("frequencyByPeriod").getValue();
//
//        // create medicine and jsonify object
//        Medicine newMed = new Medicine(drugName, doseAmount, doseScale, startDate, endDate, frequencyPeriod, frequencyByPeriod);
//        Gson gsonBuilder = new GsonBuilder().create();
//        String newMedJson = gsonBuilder.toJson(newMed);
//
//        // put attribute and save
//        persistentStorage.put(drugName, newMedJson);
//        handlerInput.getAttributesManager().setPersistentAttributes(persistentStorage);
//        handlerInput.getAttributesManager().savePersistentAttributes();
//        //TODO: label is "take {doseAmount} of {name}"
//        // take your 2 pills of tylenol by {current time that the reminder goes}

        //TODO: permissions does not have to be done
        
//        Permissions permissions = handlerInput.getRequestEnvelope().getContext().getSystem().getUser().getPermissions();
//        if(null!=permissions){
//            String speechText = "In order for this skill to create a reminder, please grant permission using the card I sent to your Alexa app";
//            List<String> list = new ArrayList<>();
//            list.add("alexa::alerts:reminders:skill:readwrite");
//            return handlerInput.getResponseBuilder()
//                    .withSpeech(speechText)
//                    .withAskForPermissionsConsentCard(list)
//                    .build();
//        }
        String locale = handlerInput.getRequestEnvelope().getRequest().getLocale();
        createReminder(handlerInput, locale, "Time To Medicate!");
        String speechText = "Ok, I will remind you to medicate every 5 secs";
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();



        // create speech for return statement
//        String speechText = "Reminders??";
//        return handlerInput.getResponseBuilder()
//                .withSpeech(speechText)
//                .withShouldEndSession(false)
//                .build();
    }
}
