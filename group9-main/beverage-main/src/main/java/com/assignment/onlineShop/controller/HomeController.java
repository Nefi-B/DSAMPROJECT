package com.assignment.onlineShop.controller;

import com.assignment.onlineShop.service.BottleService;
import com.assignment.onlineShop.service.CartService;
import com.assignment.onlineShop.service.CrateService;
import com.assignment.onlineShop.service.model.BottleDto;
import com.assignment.onlineShop.service.model.CrateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final CartService cartService;
    private final CrateService crateService;
    private final BottleService bottleService;

    @Autowired
    HomeController(
            CartService cartService,
            CrateService crateService,
            BottleService bottleService) {
        this.cartService = cartService;
        this.crateService = crateService;
        this.bottleService = bottleService;
    }

    @GetMapping
    public String showHomePage(Model model) {
        model.addAttribute("bottles", bottleService.getAll());
        model.addAttribute("crates", crateService.getAll());
        return "index";
    }

    @PostMapping("/addBottle/{id}")
    public String addBottleToBasket(@PathVariable Long id) {
        BottleDto bottle = bottleService.findById(id);
        cartService.addBottleToBasket(bottle);
        return "redirect:/";
    }

    @PostMapping("/addCrate/{id}")
    public String addCrateToBasket(@PathVariable Long id) {
        CrateDto crate = crateService.getCrateById(id);
        cartService.addCrateToBasket(crate);
        return "redirect:/";
    }
}
