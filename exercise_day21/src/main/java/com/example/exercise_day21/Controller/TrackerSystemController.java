package com.example.exercise_day21.Controller;


import com.example.exercise_day21.Api.ApiResponse;
import com.example.exercise_day21.Model.TrackerSystem;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/trackerSystem")
public class TrackerSystemController {

    ArrayList<TrackerSystem> trackerSystems = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<TrackerSystem> getTrackerSystems(){
        return trackerSystems;
    }

    @PostMapping("/add")
    public ResponseEntity addTrackerSystem(@RequestBody @Valid TrackerSystem trackerSystem, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        trackerSystems.add(trackerSystem);
        return ResponseEntity.status(200).body(new ApiResponse("added successfully"));

    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateTrackerSystem(@PathVariable int index,@RequestBody@Valid TrackerSystem trackerSystem){

        if (index < 0 || index >= trackerSystems.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("enter correct index"));
        }
        trackerSystems.set(index,trackerSystem);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("update tracker successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteTrackerSystem(@PathVariable int index){

        if (index < 0 || index >= trackerSystems.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("enter correct index"));
        }
        trackerSystems.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
    }

    @GetMapping("/search/{title}")
    public ResponseEntity searchTracker(@PathVariable String title){
        ArrayList<TrackerSystem> t = new ArrayList<>();
        for (TrackerSystem track : trackerSystems) {
            if (track.getTitle().equalsIgnoreCase(title)) {
                t.add(track);
            }
        }
        if (t.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No tracker found with this title"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(t);

    }

    @GetMapping("/searchCompanyName/{companyName}")
    public ResponseEntity displayTrackerByCompanyName(@PathVariable String companyName){
        ArrayList<TrackerSystem> trackerByCompany = new ArrayList<>();
        for (TrackerSystem t : trackerSystems) {
            if (t.getCompanyName().equalsIgnoreCase(companyName)) {
                trackerByCompany.add(t);
            }
        }

        if (trackerByCompany.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No tracker found for this company"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(trackerByCompany);

    }

    @PutMapping("/Change/{index}/{status}")
    public ResponseEntity changeStatus(@PathVariable int index, @PathVariable String status){

        if (index < 0 || index >= trackerSystems.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Invalid index, tracker not found"));
        }

        TrackerSystem tracker = trackerSystems.get(index);
        tracker.setStatus(status);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Status updated successfully"));
    }
}
