package com.demo.Quiz_app.controller;


import com.demo.Quiz_app.dto.QuestionDTO;
import com.demo.Quiz_app.dto.SubmitTestDTO;
import com.demo.Quiz_app.dto.TestDTO;
import com.demo.Quiz_app.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/test")
@CrossOrigin("*")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping()
    public ResponseEntity<?> createTest(@RequestBody TestDTO dto){

        try{
            return new ResponseEntity<>(testService.createTest(dto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/question")
    public ResponseEntity<?> addQuestionInTest (@RequestBody QuestionDTO dto){
        try{
            return new ResponseEntity<>(testService.addQuestionInTest(dto),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllTest(){
        try{
            return new ResponseEntity<>(testService.getAllTests(),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllQuestions(@PathVariable Long id){
        try{
            return new ResponseEntity<>(testService.getAllQuestionsByTest(id),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/submit-test")
    public ResponseEntity<?> submitTest(@RequestBody SubmitTestDTO dto) {

        try {
            return new ResponseEntity<>(testService.submitTest(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/test-result")
    public ResponseEntity<?> getAllTestResults(){
        try {
            return new ResponseEntity<>(testService.getAllTestResult(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTest(@PathVariable Long id, @RequestBody TestDTO dto) {
        try {
            return new ResponseEntity<>(testService.updateTest(id, dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTest(@PathVariable Long id) {
        try {
            testService.deleteTest(id);
            return new ResponseEntity<>("Test deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
