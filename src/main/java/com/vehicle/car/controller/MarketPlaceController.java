//package com.vehicle.car.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.vehicle.car.service.CarService;
//
//@Controller
//@RequestMapping("/marketPlace")
//public class MarketPlaceController {
//    private final CarService carService;
//
//    public MarketPlaceController(CarService carService) {
//        this.carService = carService;
//    }
//
//    @GetMapping
//    public String showMarketplace(Model model) {
//        model.addAttribute("cars", carService.getAllCars());
//        model.addAttribute("title", "All Vehicles");
//        return "marketplace";
//    }
//
//    @GetMapping("/suvs")
//    public String showSuvs(Model model) {
//        model.addAttribute("cars", carService.getAllSuvs());
//        model.addAttribute("title", "SUVs");
//        return "marketplace";
//    }
//
//    @GetMapping("/sedans")
//    public String showSedans(Model model) {
//        model.addAttribute("cars", carService.getAllSedans());
//        model.addAttribute("title", "Sedans");
//        return "marketplace";
//    }
//
//    @GetMapping("/hatchbacks")
//    public String showHatchbacks(Model model) {
//        model.addAttribute("cars", carService.getAllHatchbacks());
//        model.addAttribute("title", "Hatchbacks");
//        return "marketplace";
//    }
//
//    @GetMapping("/coupes")
//    public String showCoupes(Model model) {
//        model.addAttribute("cars", carService.getAllCoupes());
//        model.addAttribute("title", "Coupes");
//        return "marketplace";
//    }
//}