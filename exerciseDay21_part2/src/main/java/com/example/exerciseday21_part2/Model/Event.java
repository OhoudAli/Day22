package com.example.exerciseday21_part2.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {

@Size(min = 3,message = "the length of the id should be longer than 2")
@NotEmpty(message = "the id should not be empty")
    private String id;

@NotEmpty(message = "the description should not be empty")
@Size(min = 16,message = "the length of description should be longer than 15 ")
private String description;


@Min(value = 26,message = "the capacity should be more than 25 ")
@NotNull(message = "the capacity should not be null")
private int capacity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;


}
