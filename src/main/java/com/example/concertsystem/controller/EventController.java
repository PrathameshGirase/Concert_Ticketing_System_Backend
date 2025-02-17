package com.example.concertsystem.controller;

import com.example.concertsystem.constants.GlobalConstants;
import com.example.concertsystem.dto.EventImageResponse;
import com.example.concertsystem.dto.EventResponse;
import com.example.concertsystem.dto.SuccessResponse;
import com.example.concertsystem.service.event.EventService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/events")
@Validated
public class EventController {

    @Autowired
    private EventService eventService;


    @GetMapping("/id")
    public ResponseEntity<EventResponse> getEventById(@NotEmpty(message = "EventId cannot be null or empty") @RequestParam("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEventById(id));
    }

    @GetMapping("/city")
    public ResponseEntity<List<EventResponse>> getEventByName(@NotEmpty(message = "City Name cannot be null or empty") @RequestParam("city") String  city){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEventByPlaceName(city).getList());

    }
    @GetMapping("/venue")
    public ResponseEntity<List<EventResponse>> getEventByVenue(@NotEmpty(message = "Venue Name cannot be null or empty") @RequestParam("venue") String venue){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEventByVenueName(venue).getList());
    }
    @GetMapping("/artist")
    public ResponseEntity<List<EventResponse>> getEventByArtist(@NotEmpty(message = "Artist Name cannot be null or empty") @RequestParam("artist") String artist){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEventByArtistName(artist).getList());
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventResponse>> getAllEvents(){
        eventService.getAllEvents();
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getAllEvents());
    }

    @GetMapping("/relatedPosts")
    public ResponseEntity<List<EventResponse>> getRelatedPosts(@NotEmpty(message = "EventId cannot be null or empty") @RequestParam("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getSimilarEvents(id));
    }
    @PostMapping(value = "/")
    public ResponseEntity<SuccessResponse> addEvent(@Valid @RequestBody EventImageResponse eventImageResponse){
        boolean result = eventService.addEvent2(eventImageResponse.getEvent() ,eventImageResponse.getImgUrls());
        if(result){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new SuccessResponse(GlobalConstants.STATUS_201, GlobalConstants.MESSAGE_201_Event));
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new SuccessResponse(GlobalConstants.STATUS_417, GlobalConstants.MESSAGE_417_DELETE));
        }
    }

    @PutMapping("/update/id")
    public ResponseEntity<SuccessResponse> updateEventById(@NotEmpty(message = "EventId cannot be null or empty") @RequestParam("id") String id, @Valid @RequestBody EventImageResponse event){
        boolean result = eventService.updateEvent(id, event.getEvent(), event.getImgUrls());
        if(result) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SuccessResponse(GlobalConstants.STATUS_200, GlobalConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new SuccessResponse(GlobalConstants.STATUS_417, GlobalConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete/id")
    public ResponseEntity<SuccessResponse> deleteEventById(@NotEmpty(message = "EventId cannot be null or empty") @RequestParam("id") String id){
        boolean isDeleted = eventService.deleteEventById(id);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SuccessResponse(GlobalConstants.STATUS_200, GlobalConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new SuccessResponse(GlobalConstants.STATUS_417, GlobalConstants.MESSAGE_417_DELETE));
        }
    }
}
