package com.riwi.RiwiTech.application.services.generic;

import org.springframework.http.ResponseEntity;

public interface Create<Entity, EntityRequest> {
    public Entity create(EntityRequest request);
}
