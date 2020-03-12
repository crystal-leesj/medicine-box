package com.rxing.medicinebox.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;
import com.amazon.ask.request.Predicates;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionEndedRequestHandler implements RequestHandler {
    private static final Logger log = LoggerFactory.getLogger(SessionEndedRequestHandler.class);

    public SessionEndedRequestHandler() {
    }

    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(SessionEndedRequest.class));
    }

    public Optional<Response> handle(HandlerInput input) {
        RequestEnvelope envelope = input.getRequestEnvelope();
        log.info("onSessionEnded requestId={}, sessionId={}", envelope.getRequest().getRequestId(), envelope.getSession().getSessionId());
        return input.getResponseBuilder().withSpeech("Goodbye, talk to you tomorrow.").build();
    }
}


