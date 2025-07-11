package com.demo.Quiz_app.dto;

import lombok.Data;

@Data
public class QuestionResponse {
    private Long questionId;

    private String selectedOption;
}
