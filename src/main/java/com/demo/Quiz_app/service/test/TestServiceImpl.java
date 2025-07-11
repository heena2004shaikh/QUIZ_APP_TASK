package com.demo.Quiz_app.service.test;


import com.demo.Quiz_app.dto.*;
import com.demo.Quiz_app.entities.Question;
import com.demo.Quiz_app.entities.Test;
import com.demo.Quiz_app.entities.TestResult;
import com.demo.Quiz_app.entities.User;
import com.demo.Quiz_app.repository.QuestionRepository;
import com.demo.Quiz_app.repository.TestRepository;
import com.demo.Quiz_app.repository.TestResultRepository;
import com.demo.Quiz_app.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

@Autowired
    private TestRepository testRepository;

@Autowired
private UserRepository userRepository;

@Autowired
private QuestionRepository questionRepository;

public TestDTO createTest(TestDTO dto){
    Test test = new Test();

    test.setTitle(dto.getTitle());
    test.setDescription(dto.getDescription());
    test.setTime(dto.getTime());

    return testRepository.save(test).getDto();
}

    @Override
    public QuestionDTO addQuestionInTest(QuestionDTO dto) {
        return addQuestionTest(dto);
    }

    @Autowired
    private TestResultRepository testResultRepository;


    public QuestionDTO addQuestionTest(QuestionDTO dto){
    Optional<Test> optionalTest= testRepository.findById(dto.getId());
    if(optionalTest.isPresent()){
        Question question =new Question();

        question.setTest(optionalTest.get());
        question.setQuestionText(dto.getQuestionText());
        question.setOptionA(dto.getOptionA());
        question.setOptionB(dto.getOptionB());
        question.setOptionC(dto.getOptionC());
        question.setOptionD(dto.getOptionD());
        question.setCorrectOption(dto.getCorrectOption());

        return questionRepository.save(question).getDto();

    }throw new EntityNotFoundException("Test Not Found");
}

public List<TestDTO> getAllTests(){
    return testRepository.findAll().stream().peek(
            test ->test.setTime(test.getQuestions().size() * test.getTime())).collect(Collectors.toList())
            .stream().map(Test::getDto).collect(Collectors.toList());
}

public TestdetailsDTO getAllQuestionsByTest(Long id){
    Optional<Test> optionalTest=testRepository.findById(id);
    TestdetailsDTO testdetailsDTO=new TestdetailsDTO();
    if(optionalTest.isPresent()){
        TestDTO testDTO = optionalTest.get().getDto();
        testDTO.setTime(optionalTest.get().getTime()* optionalTest.get().getQuestions().size());

        testdetailsDTO.setTestDTO(testDTO);
        testdetailsDTO.setQuestions(optionalTest.get().getQuestions().stream().map(Question::getDto).toList());
        return testdetailsDTO;
    }
    return testdetailsDTO;
}


public TestResultDTO submitTest(SubmitTestDTO request){
        Test test= testRepository.findById(request.getTestId()).orElseThrow(()->new EntityNotFoundException("Test Not Found"));

    User user= userRepository.findById(request.getUserId()).orElseThrow(()->new EntityNotFoundException("user not found"));

    int correctAnswers=0;
       for(QuestionResponse response: request.getResponses() ){
      Question question=questionRepository.findById(response.getQuestionId())
              .orElseThrow(()->new EntityNotFoundException("Question not found"));

      if(question.getCorrectOption().trim().equalsIgnoreCase(response.getSelectedOption().trim())) {
          correctAnswers++;
      }
      }
       int totalQuestions=test.getQuestions().size();
       double percentage=((double)correctAnswers/totalQuestions)*100;

       TestResult testResult = new TestResult();
       testResult.setTest(test);
       testResult.setUser(user);
       testResult.setTotalQuestion(totalQuestions);
       testResult.setCorrectAnswer(correctAnswers);
       testResult.setPercentage(percentage);

       return testResultRepository.save(testResult).getDTO();
    }

    public List<TestResultDTO> getAllTestResult(){
        return testResultRepository.findAll().stream().map(TestResult::getDTO).collect(Collectors.toList());
    }

    @Override
    public TestDTO updateTest(Long id, TestDTO dto) {
        Test test = testRepository.findById(id).orElseThrow(() -> new RuntimeException("Test not found"));
        test.setTitle(dto.getTitle());
        test.setDescription(dto.getDescription());
        test.setTime(dto.getTime());
        return testRepository.save(test).getDto();
    }
    @Override
    @Transactional
    public void deleteTest(Long id) {
        if (!testRepository.existsById(id)) {
            throw new RuntimeException("Test not found");
        }

        // First delete all test results linked to this test
        testResultRepository.deleteAllByTestId(id);

        // Now delete the test
        testRepository.deleteById(id);
    }

}



