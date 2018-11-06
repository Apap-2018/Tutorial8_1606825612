package com.apap.tutorial8.repository;

import com.apap.tutorial8.model.DealerModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DealerDb
 */
@Repository
public interface DealerDb extends JpaRepository<DealerModel, Long> {

}