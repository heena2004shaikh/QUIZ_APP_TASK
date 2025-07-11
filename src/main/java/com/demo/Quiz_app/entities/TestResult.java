package com.demo.Quiz_app.entities;


import com.demo.Quiz_app.dto.TestResultDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalQuestion;

    private int correctAnswer;

    private double percentage;


    @ManyToOne
    @JoinColumn(name ="test_id")
    private Test test;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public TestResultDTO getDTO(){
        TestResultDTO dto=new TestResultDTO();

        dto.setId(id);
        dto.setUserId(this.user.getId());
        dto.setTestId(this.test.getId());

        dto.setTotalQuestion(totalQuestion);
        dto.setCorrectAnswer(correctAnswer);
        dto.setPercentage(percentage);
        dto.setTestName(test.getTitle());
        dto.setUserName(user.getName());

        return dto;
    }
}
