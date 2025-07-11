package com.demo.Quiz_app.dto;

import lombok.Data;

import java.util.List;

@Data
public class TestdetailsDTO {

    private TestDTO testDTO;

    private List<QuestionDTO> questions;

}
