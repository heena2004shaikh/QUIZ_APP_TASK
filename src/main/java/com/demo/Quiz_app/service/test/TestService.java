package com.demo.Quiz_app.service.test;

import com.demo.Quiz_app.dto.*;
import com.demo.Quiz_app.entities.Test;

import java.util.List;

public interface TestService {

    TestDTO createTest(TestDTO dto);

    QuestionDTO addQuestionInTest(QuestionDTO dto);

    List<TestDTO> getAllTests();

    TestdetailsDTO getAllQuestionsByTest(Long id);

    TestResultDTO submitTest(SubmitTestDTO request);

    List<TestResultDTO> getAllTestResult();

    TestDTO updateTest(Long id, TestDTO dto);

    void deleteTest(Long id);


}
