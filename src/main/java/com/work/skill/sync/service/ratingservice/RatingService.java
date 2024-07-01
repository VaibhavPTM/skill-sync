package com.work.skill.sync.service.ratingservice;


import com.work.skill.sync.entity.rating.Rating;

import java.util.List;

public interface RatingService {
    Rating giveRating(Long raterId, Long rateeId, Rating rating);

    Rating updateRating(Long ratingId, Rating ratingDetails);

    List<Rating> getAllRatingsByRateeId(Long rateeId);
}
