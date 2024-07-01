package com.work.skill.sync.service.ratingservice;

import com.work.skill.sync.entity.rating.Rating;
import com.work.skill.sync.entity.user.User;
import com.work.skill.sync.repository.ratingrepo.RatingRepository;
import com.work.skill.sync.repository.userrepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImp implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Rating giveRating(Long raterId, Long rateeId, Rating rating) {
        User rater = userRepository.findById(raterId).orElseThrow(() -> new RuntimeException("Rater not found"));
        User ratee = userRepository.findById(rateeId).orElseThrow(() -> new RuntimeException("Ratee not found"));

        rating.setRater(rater);
        rating.setRatee(ratee);
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Long ratingId, Rating ratingDetails) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new RuntimeException("Rating not found"));
        rating.setRating(ratingDetails.getRating());
        rating.setComment(ratingDetails.getComment());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatingsByRateeId(Long rateeId) {
        return ratingRepository.findByRatee_Id(rateeId);
    }
}
