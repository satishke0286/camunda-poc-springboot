package com.citizen.camunda.poc.repo;

import com.citizen.camunda.poc.entity.EmployeeDetails;
import com.citizen.camunda.poc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails, Long> {
}
