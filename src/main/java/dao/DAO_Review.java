package dao;

import models.Review;
import java.util.List;

public interface DAO_Review {

    //create
    void addReview(Review review);

    //read
    List<Review> getAllReviewsByRestaurant(int idRestaurant);

    //update
    // nothing here for now

    //delete
    void deleteByIdReview(int id);
}
