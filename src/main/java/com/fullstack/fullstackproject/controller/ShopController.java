package com.fullstack.fullstackproject.controller;

import com.fullstack.fullstackproject.model.Shop;
import com.fullstack.fullstackproject.model.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("shops")
@CrossOrigin
public class ShopController {

    @Autowired
    public ShopRepository shopRepository;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Iterable<Shop>> getShops() {
        return ResponseEntity.ok().body( shopRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShop(@PathVariable("id") Long id) {
        Optional<Shop> optionalShop = shopRepository.findById(id);
        if (optionalShop.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().body(optionalShop.get());
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
        if (shop.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
           shopUpdate.setOpeningTimes(shop.getOpeningTimes());
       }

       shopRepository.save(shopUpdate);
       return new ResponseEntity<>(shopUpdate.getId(), HttpStatus.OK);
    }

}
