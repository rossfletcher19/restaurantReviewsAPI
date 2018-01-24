package dao;

import models.*;
import java.util.List;

public interface DAO_Restaurant {
    //create
    void addRestaurant (Restaurant restaurant);
    void addRestaurantToFoodtype(Restaurant restaurant, Foodtype foodtype);

    //read
    List<Restaurant> findByZipcode();
    List<Restaurant> getAllRestaurants();
    List<Foodtype> getAllFoodTypesByRestaurant(int restaurantId);

    Restaurant findByIdRestaurant(int idRestaurants);
    int findRestaurantAverageRating(int restaurantId);


    //update
    void updateRestaurants(int id, String name, String address, String zipcode, String Phone, String website, String email);

    //delete
    void deleteByIdRestaurants(int idRestaurant);

}
