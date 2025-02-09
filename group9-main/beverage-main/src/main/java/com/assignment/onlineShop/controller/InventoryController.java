package com.assignment.onlineShop.controller;

import com.assignment.onlineShop.service.BottleService;
import com.assignment.onlineShop.service.CrateService;
import com.assignment.onlineShop.service.model.BottleDto;
import com.assignment.onlineShop.service.model.CrateDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private final BottleService bottleService;
    private final CrateService crateService;

    public InventoryController(BottleService bottleService, CrateService crateService) {
        this.bottleService = bottleService;
        this.crateService = crateService;
    }

    @GetMapping
    public String showInventoryPage(Model model) {
        model.addAttribute("bottle", new BottleDto());
        model.addAttribute("crate", new CrateDto());
        return "inventory";
    }


    @PostMapping("/createBottle")
    public String addBottle(@Valid BottleDto bottle, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bottle", bottle);
            model.addAttribute("crate", new CrateDto());
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            return "inventory";
        }
        if (bottle.getVolumePercent() > 0) {
            bottle.setIsAlcoholic(true);
        }
        bottleService.save(bottle);
        return "redirect:/inventory";
    }

    @PostMapping("/createCrate")
    public String addCrate(@Valid CrateDto crate, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bottle", new BottleDto());
            model.addAttribute("crate", crate);
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            return "inventory";
        }

        crateService.saveCrate(crate);
        return "redirect:/inventory";
    }
}
