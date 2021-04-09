package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.model.AuditDetailModel;

@Repository
public interface DetailRepository extends JpaRepository<AuditDetailModel, Integer> {

}