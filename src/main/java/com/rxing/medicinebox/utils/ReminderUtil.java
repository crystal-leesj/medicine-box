package com.rxing.medicinebox.utils;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.services.reminderManagement.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class ReminderUtil {

    public static ReminderResponse createReminder(HandlerInput input) {
//        String reminderLabel = String.format("It's time for %s", show.getName());

        SpokenText spokenText = SpokenText.builder()
                .withText("Time To Medicate!")
                .withLocale("en-US")
                .build();

        AlertInfoSpokenInfo alertInfoSpokenInfo = AlertInfoSpokenInfo.builder()
                .addContentItem(spokenText)
                .build();

        AlertInfo alertInfo = AlertInfo.builder()
                .withSpokenInfo(alertInfoSpokenInfo)
                .build();

        // For recurring reminders, the trigger date can be set to now() with the time component set to the trigger
        // time. The reminder will automatically trigger at the trigger time at the next occurrence based on the
        // recurrence pattern.
        LocalDateTime triggerTime = LocalDateTime.now();

//        Recurrence recurrence = Recurrence.builder()
//                .withByDay(RecurrenceDay.FR)
//                .withFreq(RecurrenceFreq.WEEKLY)
//                .build();

        Trigger trigger = Trigger.builder()
                .withType(TriggerType.SCHEDULED_ABSOLUTE)
                .withScheduledTime(triggerTime)
//                .withRecurrence(recurrence)
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
}
