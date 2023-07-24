package com.laerson.techsolutio.techproduct.domain.event.listener;

import com.laerson.techsolutio.techproduct.domain.event.ResourceCreatedEvent;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Component
public class ResourceCreatedListener implements ApplicationListener<ResourceCreatedEvent> {
    @Override
    public void onApplicationEvent(ResourceCreatedEvent event) {
        HttpServletResponse response = event.getResponse();
        UUID id = event.getId();

        addHeagerLocation(id, response);
    }

    private void addHeagerLocation(UUID id, HttpServletResponse response) {

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());

    }
}
