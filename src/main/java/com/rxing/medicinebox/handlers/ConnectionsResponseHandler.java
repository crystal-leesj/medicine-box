package com.rxing.medicinebox.handlers;


import com.rxing.medicinebox.utils.ReminderUtil;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.interfaces.connections.ConnectionsResponse;
import com.amazon.ask.model.services.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import com.rxing.medicinebox.models.Medicine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionsResponseHandler implements com.amazon.ask.dispatcher.request.handler.impl.ConnectionsResponseHandler {
    private static final Logger log = LoggerFactory.getLogger(ConnectionsResponseHandler.class);

    public ConnectionsResponseHandler() {
    }

    public boolean canHandle(HandlerInput input, ConnectionsResponse connectionsResponse) {
        return connectionsResponse.getName().equalsIgnoreCase("AskFor");
    }

    public Optional<Response> handle(HandlerInput input, ConnectionsResponse connectionsResponse) {
        String code = connectionsResponse.getStatus().getCode();
        if (code.equalsIgnoreCase("200")) {
            String permissionRequestResult = (String)connectionsResponse.getPayload().getOrDefault("status", "");
            byte var6 = -1;
            switch(permissionRequestResult.hashCode()) {
                case -1995366167:
                    if (permissionRequestResult.equals("NOT_ANSWERED")) {
                        var6 = 2;
                    }
                    break;
                case -1363898457:
                    if (permissionRequestResult.equals("ACCEPTED")) {
                        var6 = 0;
                    }
                    break;
                case 2012901275:
                    if (permissionRequestResult.equals("DENIED")) {
                        var6 = 1;
                    }
            }

            switch(var6) {
                case 0:
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.registerModule(new JavaTimeModule());
                    String token = connectionsResponse.getToken();

                    Medicine medicine;
                    try {
                        medicine = mapper.readValue(token, Medicine.class);
                    } catch (IOException var12) {
                        log.error("Error deserializing token", var12);
                        return input.getResponseBuilder().withSpeech("There was an error when setting the reminder.").withShouldEndSession(true).build();
                    }

                    try {
                        ReminderUtil.createReminder(input);
                        String speechText = String.format("Reminder for %s set", medicine.getDrugName());
                        return input.getResponseBuilder().withSimpleCard("Channel Guide", speechText).withSpeech(speechText).withShouldEndSession(true).build();
                    } catch (ServiceException var13) {
                        log.error("Error creating reminder", var13);
                        if (var13.getStatusCode() == 403) {
                            return input.getResponseBuilder().withSpeech("Sorry, this device doesn't support reminders.").withShouldEndSession(true).build();
                        }

                        return input.getResponseBuilder().withSpeech("There was an error when setting the reminder.").withShouldEndSession(true).build();
                    }
                case 1:
                    return input.getResponseBuilder().withShouldEndSession(true).build();
                case 2:
                default:
                    return input.getResponseBuilder().withSpeech("Please enable Reminder permissions in the Amazon Alexa app using the card I've sent to your Alexa app.").withShouldEndSession(true).build();
            }
        } else {
            log.error("ConnectionsResponse indicated failure. Error: " + connectionsResponse.getStatus().getMessage());
            return input.getResponseBuilder().withSpeech("Please enable Reminder permissions in the Amazon Alexa app using the card I've sent to your Alexa app.").withAskForPermissionsConsentCard(Collections.singletonList("alexa::alerts:reminders:skill:readwrite")).withShouldEndSession(true).build();
        }
    }
}
