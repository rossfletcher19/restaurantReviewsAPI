package dao;

import models.*;
import java.util.List;

public interface DAO_Restaurant {
    //create
    void addRestaurant (Restaurant restaurant);

    //read
    List<Restaurant> getAllRestaurants();
    List<Restaurant> findByZipcode();

    Restaurant findByIdRestaurant(int idRestaurants);
    int findRestaurantAverageRating(int restaurantId);



    //update
    void updateRestaurants(int id, String name, String address, String zipcode, String Phone, String website, String email);

    //delete
    void deleteByIdRestaurants(int idRestaurant);

}
