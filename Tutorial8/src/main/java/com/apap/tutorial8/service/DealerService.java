package com.apap.tutorial8.service;

import java.util.Optional;

import com.apap.tutorial8.model.DealerModel;

/**
 * DealerService
 */
public interface DealerService {
    Optional<DealerModel> getDealerDetailById(Long id);

    DealerModel addDealer(DealerModel dealer);

    void deleteById(Long id);
}