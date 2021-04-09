package com.cognizant.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.model.AuditRequestModel;

@Repository
public interface RequestRepository extends JpaRepository<AuditRequestModel, Integer>{
}