package dao;

import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class SQL2O_DAO_RestaurantTest {


    private org.sql2o.Connection conn;
    private SQL2O_DAO_Restaurant DAO_Restaurant;
    private SQL2O_DAO_Review DAO_Review;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        DAO_Review = new SQL2O_DAO_Review(sql2o);
        DAO_Restaurant = new SQL2O_DAO_Restaurant(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addRestaurant() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        DAO_Restaurant.addRestaurant(testRestaurant);

        Restaurant testRestaurant1 = setupRestaurant();
        DAO_Restaurant.addRestaurant(testRestaurant1);

        assertEquals(2, testRestaurant1.getRestaurantId());
    }

    @Test
    public void getAllRestaurants() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        DAO_Restaurant.addRestaurant(testRestaurant);

        Restaurant testRestaurant1 = setupRestaurant();
        DAO_Restaurant.addRestaurant(testRestaurant1);

         assertEquals(2, DAO_Restaurant.getAllRestaurants().size());
    }

    @Test
    public void findByZipcode() throws Exception {
    }

    @Test
    public void findByIdRestaurant() throws Exception {
    }

    @Test
    public void findRestaurantAverageRating() throws Exception {
    }

    @Test
    public void updateRestaurants() throws Exception {
    }

    @Test
    public void deleteByIdRestaurants() throws Exception {
    }

    public Restaurant setupRestaurant() {
        return new Restaurant("Screen Door", "1234 SE Burnside", "97232", "503-123-4567", "www.screendoor.com", "screendoor@email.com");

    }
}