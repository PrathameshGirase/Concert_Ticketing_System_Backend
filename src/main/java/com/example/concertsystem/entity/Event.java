package com.example.concertsystem.entity;

import java.io.Serializable;
import java.util.List;

public record Event(
        String id,
        String name,
        String dateAndTime,
        String description,
        String eventDuration,
        String venueId,
        List<String> userId,
        List<Tier> tierId
) implements Serializable {
}
