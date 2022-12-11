package com.fullstack.fullstackproject.controller;

import com.fullstack.fullstackproject.model.Language;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("languages")
@RestController
@CrossOrigin
public class LanguageController {

    @GetMapping("")
    public ResponseEntity<Language[]> getLanguages() {
        return ResponseEntity.status(HttpStatus.OK).body(Language.values());
    }

}
