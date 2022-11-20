package com.fullstack.fullstackproject.controller;

import com.fullstack.fullstackproject.model.Shop;
import com.fullstack.fullstackproject.model.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("shop")
public class ShopController {

    @Autowired
    public ShopRepository shopRepository;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Iterable<Shop>> getShop() {
        return ResponseEntity.ok().body( shopRepository.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Optional<Shop>> getShopById(@PathVariable("id") Long id) {

        boolean doesShopExist = shopRepository.findById(id).isPresent();

        if (doesShopExist) {
            return ResponseEntity.ok().body(shopRepository.findById(id));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable("id") Long id) {

        boolean doesShopExist = shopRepository.findById(id).isPresent();

        if (doesShopExist) {
            shopRepository.deleteById(id);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> createShop(@RequestBody @Valid Shop shop) {
        shopRepository.save(shop);
        return new ResponseEntity<>(shop.getId(), HttpStatus.CREATED);
    }

    //change value(name, isOnLeave, openingTimes, closingTimes) -> patch
    @PatchMapping("/{id}")
    public ResponseEntity<Long> changeValue(@PathVariable("id") Long id, @RequestBody Shop shop) {

        Optional<Shop> optionalShop = shopRepository.findById(id);

        if (optionalShop.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Shop shopUpdate = optionalShop.get();

       if (shop.getName() != null) {
           shopUpdate.setName(shop.getName());
       }
       if (shop.getIsOnLeave() != null) {
           shopUpdate.setIsOnLeave(shop.getIsOnLeave());
       }
       if (shop.getOpeningTimes() != null) {
           for (Map.Entry<DayOfWeek, LocalTime> dayAndOpeningTime : shop.getOpeningTimes().entrySet()) {
               shopUpdate.setOpeningTime(dayAndOpeningTime.getKey(), dayAndOpeningTime.getValue());
           }
       }
       if (shop.getClosingTimes() != null) {
           for (Map.Entry<DayOfWeek, LocalTime> dayAndClosingTime : shop.getClosingTimes().entrySet()) {
               shopUpdate.setClosingTime(dayAndClosingTime.getKey(), dayAndClosingTime.getValue());
           }
       }

        shopRepository.save(shopUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
