package com.example.events;

import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

import com.example.filter.AuthoritiesLoggingAfterFilter;

@Component
@Slf4j
public class AuthorizationEvents {

	
	Logger log = Logger.getLogger(AuthorizationEvents.class.getName());
	
    @EventListener
    public void onFailure(AuthorizationDeniedEvent deniedEvent) {
        log.info("Authorization failed for the user : {} due to : {}"+ deniedEvent.getAuthentication().get().getName()	 +"" +
                deniedEvent.getAuthorizationDecision().toString());
    }

}