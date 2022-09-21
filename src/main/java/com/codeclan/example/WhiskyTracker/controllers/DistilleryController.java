package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RequestWrapper;
import java.util.List;

@RestController
public class DistilleryController {
    @Autowired
    DistilleryRepository distilleryRepository;
    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value="/distilleries/region")
    public ResponseEntity<List<Distillery>> getByRegion(@RequestParam(name="region") String region){
        return new ResponseEntity<>(distilleryRepository.findByRegion(region), HttpStatus.OK);
    }
    @GetMapping(value="/distilleries/{id}/whiskies/age")
    public ResponseEntity<List<Whisky>> getWhiskyByAgeAndDistillery(@PathVariable Long id, @RequestParam(name="age") int age){
        return new ResponseEntity<>(whiskyRepository.findByAgeAndDistillery(age, distilleryRepository.findById(id).get()), HttpStatus.OK);
    }
    @GetMapping(value="/distilleries/whiskies/age")
    public ResponseEntity<List<Distillery>> getDistilleriesThatHaveWhiskyOfAge(@RequestParam(name="age") int age){
        return new ResponseEntity<>(distilleryRepository.findByWhiskiesAge(age), HttpStatus.OK);
    }

}
