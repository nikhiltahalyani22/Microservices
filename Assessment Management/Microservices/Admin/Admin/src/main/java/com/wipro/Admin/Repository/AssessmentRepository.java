package com.wipro.Admin.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.Admin.Entity.Assessment;


@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {

    List<Assessment> findByAssessmentType(String theAssessmentType);

}
