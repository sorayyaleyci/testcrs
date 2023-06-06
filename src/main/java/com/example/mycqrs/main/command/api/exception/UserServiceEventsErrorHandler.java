package com.example.mycqrs.main.command.api.exception;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

import javax.annotation.Nonnull;

public class UserServiceEventsErrorHandler implements ListenerInvocationErrorHandler {
    @Override
    public void onError(@Nonnull Exception e, @Nonnull EventMessage<?> eventMessage, @Nonnull EventMessageHandler eventMessageHandler) throws Exception {
        throw e;
    }
}
