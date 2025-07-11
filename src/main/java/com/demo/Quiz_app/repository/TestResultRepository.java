package com.demo.Quiz_app.repository;

import com.demo.Quiz_app.entities.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM TestResult tr WHERE tr.test.id = :testId")
    void deleteAllByTestId(@Param("testId") Long testId);
}
