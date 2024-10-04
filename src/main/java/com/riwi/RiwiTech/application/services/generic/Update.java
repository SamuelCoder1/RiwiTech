package com.riwi.RiwiTech.application.services.generic;

import org.springframework.http.ResponseEntity;

public interface Update<EntityRequest, Entity,ID> {
    public Entity update( EntityRequest request,ID id);
}
