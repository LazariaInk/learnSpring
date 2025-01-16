package com.pluriverse.learnSpring.controller;

import com.pluriverse.learnSpring.model.Spot;
import com.pluriverse.learnSpring.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spots")
public class SpotController {

    private final SpotService spotService;

    @Autowired
    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Spot> createSpot(@PathVariable long userId, @RequestBody Spot spot) {
        spotService.createSpotForUser(userId, spot.getCoordinates(), spot.getDescription());
        return ResponseEntity.ok(spot);
    }

    @DeleteMapping("/{spotId}")
    public ResponseEntity<Void> deleteSpot(@PathVariable long spotId) {
        spotService.deleteSpot(spotId);
        return ResponseEntity.noContent().build();
    }

}
