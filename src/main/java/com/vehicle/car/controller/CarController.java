package com.vehicle.car.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vehicle.car.model.*;
import com.vehicle.car.service.CarService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/suvs")
    @ResponseBody
    public List<SUV> getSuvsApi() {
        return carService.getAllSuvs();
    }

    @GetMapping("/sedans")
    @ResponseBody
    public List<Sedan> getSedansApi() {
        return carService.getAllSedans();
    }

    @GetMapping("/hatchbacks")
    @ResponseBody
    public List<Hatchback> getHatchbacks() {
        return carService.getAllHatchbacks();
    }

    @GetMapping("/coupes")
    @ResponseBody
    public List<Coupe> getCoupes() {
        return carService.getAllCoupes();
    }

    @PostMapping("/add")
    public String addCar(@RequestParam Map<String, String> params,
            RedirectAttributes redirectAttributes) {
        try {
            String type = params.get("type");
            Car car;

            switch (type.toUpperCase()) {
                case "SUV":
                    SUV suv = new SUV();
                    setCommonCarFields(suv, params);
                    suv.setWeight(Integer.parseInt(params.getOrDefault("weight", "0")));
                    suv.setIs4wd(params.containsKey("is4wd"));
                    suv.setHasThirdRowSeat(params.containsKey("hasThirdRowSeat"));
                    car = suv;
                    break;

                case "SEDAN":
                    Sedan sedan = new Sedan();
                    setCommonCarFields(sedan, params);
                    sedan.setYear(Integer.parseInt(params.getOrDefault("year", "0")));
                    sedan.setManufacturerDiscount(Double.parseDouble(params.getOrDefault("manufacturerDiscount", "0")));
                    sedan.setHasSunroof(params.containsKey("hasSunroof"));
                    car = sedan;
                    break;

                case "HATCHBACK":
                    Hatchback hatchback = new Hatchback();
                    setCommonCarFields(hatchback, params);
                    hatchback.setBootspace(Double.parseDouble(params.getOrDefault("bootspace", "0")));
                    car = hatchback;
                    break;

                case "COUPE":
                    Coupe coupe = new Coupe();
                    setCommonCarFields(coupe, params);
                    coupe.setConvertible(params.containsKey("isConvertible"));
                    coupe.setSeatCount(Integer.parseInt(params.getOrDefault("seatCount", "2")));
                    car = coupe;
                    break;

                default:
                    throw new IllegalArgumentException("Invalid vehicle type");
            }

            carService.saveCar(car);
            redirectAttributes.addFlashAttribute("success", "Car added successfully!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Failed to add car. Error: " + e.getMessage());
        }
        return "redirect:/";
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        boolean deleted = carService.deleteCarById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping
    @ResponseBody
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    private void setCommonCarFields(Car car, Map<String, String> params) {
        car.setModel(params.get("model"));
        car.setColor(params.get("color"));
        car.setSpeed(Integer.parseInt(params.get("speed")));
        car.setRegularPrice(Double.parseDouble(params.get("regularPrice")));
        car.setFuelType(params.get("fuelType"));
        car.setTransmission(params.get("transmission"));
        car.setMileage(Integer.parseInt(params.getOrDefault("mileage", "0")));
        car.setCTSEmployee(params.containsKey("isCTSEmployee"));


    }
}