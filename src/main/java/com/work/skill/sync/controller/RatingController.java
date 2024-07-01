package com.work.skill.sync.controller;

import com.work.skill.sync.entity.rating.Rating;
import com.work.skill.sync.service.ratingservice.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating")
public class RatingController  {
    @Autowired
    RatingService ratingService;

    // give rating
    @PostMapping("/give/{raterId}/{rateeId}")
    public ResponseEntity<Rating> giveRating( @PathVariable Long raterId, @PathVariable Long rateeId, @RequestBody Rating rating) {
        try {
            Rating savedRating = ratingService.giveRating(raterId, rateeId, rating);
            return ResponseEntity.ok(savedRating);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // update rating
    @PutMapping("/update/{ratingId}")
    public ResponseEntity<Rating> updateRating(
            @PathVariable Long ratingId,
            @RequestBody Rating ratingDetails) {
        Rating updatedRating = ratingService.updateRating(ratingId, ratingDetails);
        return ResponseEntity.ok(updatedRating);
    }

    // get all rating by ratee id
    @GetMapping("/ratee/{rateeId}")
    public ResponseEntity<List<Rating>> getAllRatingsByRateeId(@PathVariable Long rateeId) {
        List<Rating> ratings = ratingService.getAllRatingsByRateeId(rateeId);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/give")
    public String tmp(){
        return "This is working";
    }


}
