package dao;
import com.sun.org.apache.regexp.internal.RE;
import models.*;
import java.util.List;

public interface DAO_Foodtype {
    //create
    void addFoodtype(Foodtype foodtype);
    void addRestaurantToFoodtype(Restaurant restaurant, Foodtype foodtype);
    void addFoodtypeToRestaurant(Foodtype foodtype, Restaurant restaurant);

    //read
    List<Restaurant> getAllRestaurantsForAFoodtype(int id);
    List<Foodtype> getAllFoodtypes();

    Foodtype findbyIdFoodtype(int foodtypeId);

    //update
    void updateFoodtype(int foodtypeId, String foodtype);

    //delete
    void deleteByIdFoodtype(int foodtypeId);
    void clearAllFoodtypes();
}