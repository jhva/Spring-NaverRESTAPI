package com.example.restaurant.controller;

import com.example.restaurant.wishlist.dto.WishListDTo;
import com.example.restaurant.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class ApiController {


    private final WishListService wishListService;
//    private final


    @GetMapping("/search")
    public WishListDTo search(@RequestParam String query) {

        return wishListService.search(query);

    }


    @PostMapping("")
    public WishListDTo add(@RequestBody WishListDTo wishListDTo) {

        log.info("{}", wishListDTo);
        return wishListService.add(wishListDTo);

    }


    @GetMapping("/all")
    public List<WishListDTo> findAll() {
        return wishListService.findAll();
    }

    @DeleteMapping("/{index}")
    public void delete(@PathVariable int index) {
        wishListService.delete(index);
    }

    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index){
        wishListService.addVisit(index);
    }
}
