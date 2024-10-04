package com.riwi.RiwiTech.application.services.generic;

import org.springframework.http.ResponseEntity;

public interface ReadByName<Entity, NAME>{
    public Entity readByName(NAME name);
}
