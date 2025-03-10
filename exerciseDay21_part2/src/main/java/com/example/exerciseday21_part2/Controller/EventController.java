package com.example.exerciseday21_part2.Controller;


import com.example.exerciseday21_part2.Api.ApiResponse;
import com.example.exerciseday21_part2.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();

@GetMapping("/get")
    public ArrayList<Event> getEvent(){
        return events;
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors){
         if(errors.hasErrors()){
             String message = errors.getFieldError().getDefaultMessage();
             return ResponseEntity.status(400).body(new ApiResponse(message));
         }
         events.add(event);
         return ResponseEntity.status(200).body(new ApiResponse("add event successfully"));
    }


    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @RequestBody @Valid Event event){

    if(index <0 || index >= events.size()){
        return ResponseEntity.status(400).body(new ApiResponse("Not a correct index"));

    }
    events.set(index, event);
    return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
    }


    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index){

    if(index<0 || index>= events.size()){
        return ResponseEntity.status(400).body(new ApiResponse("enter correct index"));
    }
    events.remove(index);
    return ResponseEntity.status(200).body(new ApiResponse("deleted successfully"));
    }



    @PutMapping("/change/{index}/{capacity}")
    public ResponseEntity changeEvent(@PathVariable int index, @PathVariable int capacity){

        if (index < 0 || index >= events.size()) {
            return ResponseEntity.status(400).body(new ApiResponse("Invalid index! Please provide a correct index."));
        }
        if (capacity < 26) {
            return ResponseEntity.status(400).body(new ApiResponse("Capacity must be greater than 25."));
        }
    events.get(index).setCapacity(capacity);
    return ResponseEntity.status(200).body("change capacity successfully");
    }


    @GetMapping("/search/{id}")
    public ResponseEntity searchEvent(@PathVariable String id){

    for (Event e: events){
        if(e.getId().equals(id)) {
            return ResponseEntity.status(200).body(e);
        }
    }
    return ResponseEntity.status(400).body(" try an existing id");

    }


}
