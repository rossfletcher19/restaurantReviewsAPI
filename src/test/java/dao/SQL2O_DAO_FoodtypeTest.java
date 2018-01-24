package dao;

import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SQL2O_DAO_FoodtypeTest {

    private org.sql2o.Connection conn;
    private SQL2O_DAO_Restaurant DAO_Restaurant;
    private SQL2O_DAO_Foodtype DAO_Foodtype;

    public Foodtype setupNewFoodType() {
        return new Foodtype("BBQ");
    }

    public Restaurant setupRestaurant() {
        return new Restaurant("Arby's", "1600 Pennsylvania Ave NW, Washington, DC ", "20500", "202-456-1111", "http://arbys.com", "arbys@email.com");
    }

    public Restaurant setupAltRestaurant() {
        return new Restaurant("Outback Steak House", "Portland, Oregon", "97211", "503-867-5309");
    }

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        DAO_Foodtype = new SQL2O_DAO_Foodtype(sql2o);
        DAO_Restaurant = new SQL2O_DAO_Restaurant(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addFoodtype() throws Exception {
        Foodtype foodtype = setupNewFoodType();
        DAO_Foodtype.addFoodtype(foodtype);

        assertEquals(1, foodtype.getIdFoodtype());
    }

    @Test
    public void getAllFoodtypes() throws Exception {
        Foodtype foodtype = setupNewFoodType();
        DAO_Foodtype.addFoodtype(foodtype);

        assertEquals(1, DAO_Foodtype.getAllFoodtypes().size());
    }

    @Test
    public void findbyIdFoodtype() throws Exception {
        Foodtype foodtype = setupNewFoodType();
        DAO_Foodtype.addFoodtype(foodtype);

        assertEquals("BBQ", DAO_Foodtype.findbyIdFoodtype(1).getFoodtype());
    }

    @Test
    public void updateFoodtype() throws Exception {
        Foodtype testFoodtype = setupNewFoodType();
        DAO_Foodtype.addFoodtype(testFoodtype);

        DAO_Foodtype.updateFoodtype(testFoodtype.getIdFoodtype(), "Vegan BBQ");

        assertEquals("Vegan BBQ", DAO_Foodtype.findbyIdFoodtype(testFoodtype.getIdFoodtype()).getFoodtype());
    }

    @Test
    public void addFoodTypeToRestaurantAddsTypeCorrectly() throws Exception {

        Restaurant testRestaurant = setupRestaurant();
        Restaurant altRestaurant = setupAltRestaurant();

        DAO_Restaurant.addRestaurant(testRestaurant);
        DAO_Restaurant.addRestaurant(altRestaurant);

        Foodtype testFoodtype = setupNewFoodType();

        DAO_Foodtype.addFoodtype(testFoodtype);

        DAO_Foodtype.addFoodtypeToRestaurant(testFoodtype, testRestaurant);
        DAO_Foodtype.addFoodtypeToRestaurant(testFoodtype, altRestaurant);

        assertEquals(2, DAO_Foodtype.getAllRestaurantsForAFoodtype(testFoodtype.getIdFoodtype()).size());
    }

    @Test
    public void getAllRestaurantsForAFoodtype() throws Exception {
        Foodtype foodtype1 = new Foodtype("Ramen");
        DAO_Foodtype.addFoodtype(foodtype1);

        Restaurant restaurant1 = setupRestaurant();
        DAO_Restaurant.addRestaurant(restaurant1);
        DAO_Foodtype.addFoodtypeToRestaurant(foodtype1, restaurant1);

        Restaurant restaurant2 = setupAltRestaurant();
        DAO_Restaurant.addRestaurant(restaurant2);
        DAO_Foodtype.addFoodtypeToRestaurant(foodtype1, restaurant2);

        assertEquals(2, DAO_Foodtype.getAllRestaurantsForAFoodtype(foodtype1.getIdFoodtype()).size());
    }

    //WORK IN PROGRESS
    @Test
    public void deleteingRestaurantAlsoUpdatesJoinTable() throws Exception {
        Foodtype foodtype1 = new Foodtype("Seafood");
        DAO_Foodtype.addFoodtype(foodtype1);

        Restaurant restaurant1 = setupRestaurant();
        DAO_Restaurant.addRestaurant(restaurant1);

        Restaurant restaurant2 = setupAltRestaurant();
        DAO_Restaurant.addRestaurant(restaurant2);

        DAO_Restaurant.addRestaurantToFoodtype(restaurant1, foodtype1);
        DAO_Restaurant.addRestaurantToFoodtype(restaurant2, foodtype1);

        List<Foodtype> someList = DAO_Restaurant.getAllFoodTypesByRestaurant(1);

        assertEquals(1, DAO_Restaurant.getAllFoodTypesByRestaurant(restaurant1.getRestaurantId()).size());
        DAO_Restaurant.deleteByIdRestaurants(restaurant1.getRestaurantId());

        assertEquals(0, DAO_Restaurant.getAllFoodTypesByRestaurant(restaurant1.getRestaurantId()).size());
    }

    //MISSING TESTS
    @Test
    public void deleteByIdFoodtype() throws Exception {

    }

    @Test
    public void clearAllFoodtypes() throws Exception {
    }

    @Test
    public void addRestaurantToFoodtype() throws Exception {
    }

    @Test
    public void addFoodtypeToRestaurant() throws Exception {
    }

}