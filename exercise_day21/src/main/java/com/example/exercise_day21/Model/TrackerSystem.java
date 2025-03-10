package com.example.exercise_day21.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TrackerSystem {


    @NotEmpty(message = "ID should not be empty!!")
    @Size(min=3, message = "the length of id should be more than 2")
    private String id;

    @NotEmpty(message = "title should not be empty ")
    @Size(min = 9,message = "the title should be more than 8")
    private String title;

    @NotEmpty(message = "description should not be empty")
    @Size(min= 16, message = "the description should be more than 15")
    private String description;

    @NotEmpty(message = "status should not be empty")
    @Pattern(regexp = "Not Started|In Progress|Completed", message = "Status must be 'Not Started', 'In Progress', or 'Completed' only")
    private String status;

    @NotEmpty(message = "company name should not be empty")
    @Size(min = 7, message = "te length of company name should be more than 6")
    private String companyName;
}
