package com.work.skill.sync.repository.ratingrepo;

import com.work.skill.sync.entity.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT r FROM Rating r WHERE r.ratee.id = :rateeId")
    List<Rating> findByRatee_Id(@Param("rateeId") Long rateeId);
}
