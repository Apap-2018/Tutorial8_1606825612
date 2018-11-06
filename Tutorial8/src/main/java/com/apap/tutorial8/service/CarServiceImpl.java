package com.apap.tutorial8.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial8.model.CarModel;
import com.apap.tutorial8.repository.CarDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CarServiceImpl
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDb carDb;

    @Override
    public CarModel addCar(CarModel car) {
        return carDb.save(car);
    }

    @Override
    public List<CarModel> getAllCar() {
        return carDb.findAll();
    }

    @Override
    public Optional<CarModel> getCarDetailById(Long id) {
        return carDb.findById(id);
    }

    @Override
    public Optional<CarModel> getCarDetailByType(String type) {
        return carDb.findByType(type);
    }

    @Override
    public void deleteCar(CarModel car) {
        carDb.delete(car);
    }

    @Override
    public List<CarModel> getListCarOrderByPriceAsc(Long dealerId) {
        return carDb.findByDealerIdOrderByPriceAsc(dealerId);
    }
}