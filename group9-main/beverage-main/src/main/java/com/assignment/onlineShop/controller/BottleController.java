package com.assignment.onlineShop.controller;

import com.assignment.onlineShop.service.model.BottleDto;
import com.assignment.onlineShop.service.BottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bottles")
public class BottleController {
    private final BottleService bottleService;

    @Autowired
    BottleController(BottleService bottleService) {
        this.bottleService = bottleService;
    }

    @GetMapping
    public String getBottles(Model model) {
        model.addAttribute("bottles", bottleService.getAll());
        return "bottle-list";
    }

    @PostMapping
    public BottleDto createBottle(@RequestBody BottleDto bottle) {
        return bottleService.save(bottle);
    }

    @GetMapping("/{id}")
    public String getBottleById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bottle", bottleService.findById(id));
        return "bottle-detail";
    }

    @PutMapping
    public BottleDto updateBottle(@RequestBody BottleDto bottle) {
        return bottleService.save(bottle);
    }

    @DeleteMapping
    public void deleteBottleById(@RequestParam("id") Long id) {
        bottleService.delete(id);
    }
}
