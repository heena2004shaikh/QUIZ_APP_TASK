package com.demo.Quiz_app.dto;


import lombok.Data;

@Data
public class TestResultDTO {
    private Long id;


    private Long userId;
    private Long testId;

    private int totalQuestion;

    private int correctAnswer;

    private double percentage;

    private String testName;

    private String userName;
}
