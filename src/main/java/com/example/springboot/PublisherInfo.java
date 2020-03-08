package com.example.springboot;

import java.util.Objects;
import java.util.Optional;

public class PublisherInfo {

    public String courseNumber;
    public String courseName;
    public String title;
    public String description;
    public String professor;
    public String time;
    public String aSeats;

    public PublisherInfo(String courseNumber, String title, String description, String professor, String time, String aSeats) {

        this.courseNumber = courseNumber;
        this.title = title;
        this.description = description;
        this.professor = professor;
        this.time = time;
        this.aSeats = aSeats;
    }

}
