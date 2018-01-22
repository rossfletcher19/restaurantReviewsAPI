package dao;
import models.*;
import java.util.List;

public interface DAO_Foodtype {
    void addFoodtype(Foodtype foodtype);
    List<Foodtype> getAllFoodtypes();
    Foodtype findbyIdFoodtype(int idFoodtype);
    void updateFoodtype(int idFoodtype, String foodtype);
    void deleteByIdFoodtype(int idFoodtype);
    void clearAllFoodtypes();
}
