package com.apap.tutorial8.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.apap.tutorial8.model.CarModel;
import com.apap.tutorial8.model.DealerModel;
import com.apap.tutorial8.service.CarService;
import com.apap.tutorial8.service.DealerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * CarController
 */
@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private DealerService dealerService;

    @RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
    private String add(@PathVariable(value = "dealerId") Long dealerId, Model model) {
        DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
        dealer.setListCar(new ArrayList<CarModel>(){
            private ArrayList<CarModel> init() {
                this.add(new CarModel());
                return this;
            }
        }.init());

        model.addAttribute("dealer", dealer);
        return "add-car";
    }

    @RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params={"addRow"})
    public String addRow(@ModelAttribute DealerModel dealer, Model model) {
        dealer.getListCar().add(new CarModel());

        model.addAttribute("dealer", dealer);
        return "add-car";
    }

    @RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params={"removeRow"})
    public String removeRow(@ModelAttribute DealerModel dealer, Model model, HttpServletRequest req) {
        Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        dealer.getListCar().remove(rowId.intValue());
        
        model.addAttribute("dealer", dealer);
        return "add-car";
    }

    @RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.POST, params={"save"})
    private String addCarSubmit(@ModelAttribute DealerModel dealer) {
        DealerModel archive = dealerService.getDealerDetailById(dealer.getId()).get();
        for (CarModel car : dealer.getListCar()) {
            if (car != null) {
                car.setDealer(archive);
                carService.addCar(car);
            }
        }
        return "add";
    }

    @RequestMapping(value = "/car/view", method = RequestMethod.GET)
    private @ResponseBody CarModel view(@RequestParam(value = "type") String type, Model model) {
        CarModel archive = carService.getCarDetailByType(type).get();
        return archive;
    }

    @RequestMapping(value = "/car/delete", method = RequestMethod.POST)
    private String delete(@ModelAttribute DealerModel dealer, Model model) {
        for (CarModel car : dealer.getListCar()) {
            carService.deleteCar(car);
        }
        return "delete";
    }

    @RequestMapping(value = "/car/update", method = RequestMethod.GET)
    private String update(@RequestParam(value = "type") String type, Model model) {
        CarModel archive = carService.getCarDetailByType(type).get();
        model.addAttribute("car", archive);
        return "update-car";
    }

    @RequestMapping(value = "/car/update", method = RequestMethod.POST)
    private @ResponseBody CarModel updatePilotSubmit(@ModelAttribute CarModel car, Model model) {
        carService.addCar(car);
        return car;
    }
}