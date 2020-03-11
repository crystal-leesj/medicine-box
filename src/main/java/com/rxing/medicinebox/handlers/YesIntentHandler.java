package com.rxing.medicinebox.handlers;

import com.rxing.medicinebox.models.Medicine;
import com.rxing.medicinebox.utils.ReminderUtil;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.interfaces.connections.SendRequestDirective;
import com.amazon.ask.model.services.ServiceException;
import com.amazon.ask.request.Predicates;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YesIntentHandler implements RequestHandler {
    private static final Logger log = LoggerFactory.getLogger(YesIntentHandler.class);

    public YesIntentHandler() {
    }

    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("AMAZON.YesIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
        String previousIntent = (String)input.getAttributesManager().getSessionAttributes().get("previousIntent");
        if (previousIntent != null && previousIntent.equals("CreateReminder")) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            Medicine medicine = mapper.convertValue(input.getAttributesManager().getSessionAttributes().get("name"), Medicine.class);

            String token;
            try {
                ReminderUtil.createReminder(input);
                token = String.format("Reminder for %s medication", medicine.getDrugName());
                return input.getResponseBuilder().withSimpleCard("Channel Guide", token).withSpeech(token).withShouldEndSession(true).build();
            } catch (ServiceException var9) {
                log.error("Error creating reminder", var9);
                if (var9.getStatusCode() == 401) {
                    token = "";

                    try {
                        token = mapper.writeValueAsString(medicine);
                    } catch (Exception var8) {
                        log.error("Error when calling ObjectMapper.writeValueAsString", var9);
                    }

                    SendRequestDirective directive = this.getRequestSkillPermissionRequestDirective(token);
                    return input.getResponseBuilder().addDirective(directive).withShouldEndSession(true).build();
                } else {
                    return var9.getStatusCode() == 403 ? input.getResponseBuilder().withSpeech("Sorry, this device doesn't support reminders.").withShouldEndSession(true).build() : input.getResponseBuilder().withSpeech("There was an error when setting the reminder.").withShouldEndSession(true).build();
                }
            }
        } else {
            return input.getResponseBuilder().withSpeech("Hmm. I'm not sure how to help you with that.").withShouldEndSession(false).build();
        }
    }

    private SendRequestDirective getRequestSkillPermissionRequestDirective(String token) {
        return SendRequestDirective.builder().withName("AskFor").putPayloadItem("@type", "AskForPermissionsConsentRequest").putPayloadItem("@version", "1").putPayloadItem("permissionScope", "alexa::alerts:reminders:skill:readwrite").withToken(token).build();
    }
}
