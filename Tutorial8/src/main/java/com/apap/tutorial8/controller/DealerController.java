package com.apap.tutorial8.controller;

import java.util.List;

import com.apap.tutorial8.model.CarModel;
import com.apap.tutorial8.model.DealerModel;
import com.apap.tutorial8.service.CarService;
import com.apap.tutorial8.service.DealerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * DealerController
 */
@Controller
public class DealerController {
    @Autowired
    private DealerService dealerService;

    @Autowired
    private CarService carService;

    /**@RequestMapping("/")
    private String home() {
        return "home";
    }**/

    @RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
    private String add(Model model) {
        model.addAttribute("dealer", new DealerModel());
        return "add-dealer";
    }

    @RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
    private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
        dealerService.addDealer(dealer);
        return "add";
    }

    @RequestMapping(value = "/dealer/view", method = RequestMethod.GET)
    private String view(@RequestParam(value = "dealerId") Long dealerId, Model model) {
        DealerModel archiveDealer = dealerService.getDealerDetailById(dealerId).get();
        /** 
         * Untuk mendapatkan list car terurut berdasarkan harga dengan Query
         * Bisa jadi berbeda dengan cara anda
         */
        List<CarModel> archiveListCar = carService.getListCarOrderByPriceAsc(dealerId);
        archiveDealer.setListCar(archiveListCar);

        model.addAttribute("dealer", archiveDealer);
        return "view-dealer";
    }

    @RequestMapping(value = "/dealer/delete", method = RequestMethod.GET)
    private String delete(@RequestParam(value = "dealerId") Long dealerId, Model model) {
        dealerService.deleteById(dealerId);
        return "delete";
    }

    @RequestMapping(value = "/dealer/update", method = RequestMethod.GET)
    private String update(@RequestParam(value = "dealerId") Long dealerId, Model model) {
        DealerModel archive = dealerService.getDealerDetailById(dealerId).get();
        model.addAttribute("dealer", archive);
        return "update-dealer";
    }

    @RequestMapping(value = "/dealer/update", method = RequestMethod.POST)
    private @ResponseBody DealerModel updateFlightSubmit(@ModelAttribute DealerModel dealer, Model model) {
        dealerService.addDealer(dealer);
        return dealer;
    }
}