package com.laerson.techsolutio.techproduct.domain.event;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

public class ResourceCreatedEvent extends ApplicationEvent {

    private HttpServletResponse response;
    private UUID id;

    public ResourceCreatedEvent(Object source, HttpServletResponse response,UUID id){
        super(source);
        this.response = response;
        this.id = id;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public UUID getId() {
        return id;
    }
}
