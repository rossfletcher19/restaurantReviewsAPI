package dao;
import models.*;
import java.util.List;

public interface DAO_Foodtype {
    void addFoodtype(Foodtype foodtype);
    List<Foodtype> getAllFoodtypes();
    Foodtype findbyIdFoodtype(int foodtypeId);
    void updateFoodtype(int foodtypeId, String foodtype);
    void deleteByIdFoodtype(int foodtypeId);
    void clearAllFoodtypes();
}
