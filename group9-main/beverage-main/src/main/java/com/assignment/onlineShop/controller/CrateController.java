package com.assignment.onlineShop.controller;

import com.assignment.onlineShop.service.CrateService;
import com.assignment.onlineShop.service.model.CrateDto;
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
@RequestMapping("/crates")
public class CrateController {
    private final CrateService crateService;

    @Autowired
    CrateController(CrateService crateService) {
        this.crateService = crateService;
    }

    @GetMapping
    public String getAllCrates(Model model) {
        model.addAttribute("crates", crateService.getAll());
        return "crate-list";
    }

    @GetMapping("/{id}")
    public String viewCrate(@PathVariable Long id, Model model) {
        model.addAttribute("crate", crateService.getCrateById(id));
        return "crate-details";
    }

    @PostMapping
    public CrateDto createCrate(@RequestBody CrateDto crate) {
        return crateService.saveCrate(crate);
    }

    @PutMapping
    public CrateDto updateCrate(@RequestBody CrateDto crate) {
        return crateService.saveCrate(crate);
    }

    @DeleteMapping
    public void deleteCrateById(@RequestParam Long id) {
        crateService.deleteCrate(id);
    }
}
