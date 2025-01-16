package com.pluriverse.learnSpring.service;

import com.pluriverse.learnSpring.model.Spot;
import com.pluriverse.learnSpring.model.User;
import com.pluriverse.learnSpring.repository.SpotRepository;
import com.pluriverse.learnSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SpotService {

    private final SpotRepository spotRepository;
    private final UserRepository userRepository;

    @Autowired
    public SpotService(SpotRepository spotRepository, UserRepository userRepository) {
        this.spotRepository = spotRepository;
        this.userRepository = userRepository;
    }

    public Spot createSpotForUser(long userId, String coordinates, String description) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        User user = userOptional.get();

        Spot newSpot = new Spot();
        newSpot.setCoordinates(coordinates);
        newSpot.setDescription(description);
        newSpot.setDate(new Date());
        newSpot.setUser(user);

        Spot savedSpot = spotRepository.save(newSpot);

        user.getSpots().add(savedSpot);
        userRepository.save(user);

        return savedSpot;
    }


    public void deleteSpot(long spotId) {
        Optional<Spot> spotOptional = spotRepository.findById(spotId);

        if (spotOptional.isEmpty()) {
            throw new RuntimeException("Spot not found with id: " + spotId);
        }

        spotRepository.deleteById(spotId);
    }
}
