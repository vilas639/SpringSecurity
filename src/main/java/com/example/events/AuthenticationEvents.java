package com.example.events;

import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;



@Component
@Slf4j
public class AuthenticationEvents {
	
	Logger log = Logger.getLogger(AuthenticationEvents.class.getName());

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent successEvent) {
        log.info("Login successful for the user : {}"+ successEvent.getAuthentication().getName());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failureEvent) {
    	
        log.info("Login failed for the user : {} due to : {}"+ failureEvent.getAuthentication().getName() +
                failureEvent.getException().getMessage());
    }

}