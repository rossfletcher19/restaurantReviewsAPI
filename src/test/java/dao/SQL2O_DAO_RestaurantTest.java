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

    public Restaurant setupRestaurant() {
        return new Restaurant("Screen Door", "1234 SE Burnside", "97232", "503-123-4567", "www.screendoor.com", "screendoor@email.com");
    }

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
        Restaurant testRestaurant1 = setupRestaurant();
        DAO_Restaurant.addRestaurant(testRestaurant1);

        assertEquals("97232", testRestaurant1.getZipcode());

    }

    @Test
    public void findByIdRestaurant() throws Exception {
        Restaurant testRestaurant1 = setupRestaurant();
        DAO_Restaurant.addRestaurant(testRestaurant1);

        Restaurant testRestaurant2 = new Restaurant("Arby's", "1600 Pennsylvania Ave NW, Washington, DC ", "20500", "202-456-1111");
        DAO_Restaurant.addRestaurant(testRestaurant2);

        assertEquals(2, DAO_Restaurant.findByIdRestaurant(testRestaurant2.getRestaurantId()).getRestaurantId());
    }

    @Test
    public void findRestaurantAverageRating() throws Exception {
        Restaurant testRestaurant = new Restaurant("Arby's", "1600 Pennsylvania Ave NW, Washington, DC ", "20500", "202-456-1111");
        DAO_Restaurant.addRestaurant(testRestaurant);

        int testRestaurantId =testRestaurant.getRestaurantId();

        Review testReview1 = new Review("Wendy", 100, "foodcoma!", testRestaurant.getRestaurantId());
        DAO_Review.addReview(testReview1);

        Review testReview2 = new Review("Ronald McDonald", 10, "Adequate appetizers.!", testRestaurant.getRestaurantId());
        DAO_Review.addReview(testReview2);

        assertEquals(55, DAO_Restaurant.findRestaurantAverageRating(testRestaurantId));
    }

    @Test
    public void updateRestaurants() throws Exception {
        Restaurant testRestaurant1 = new Restaurant("Arby's", "1600 Pennsylvania Ave NW, Washington, DC ", "20500", "202-456-1111");
        DAO_Restaurant.addRestaurant(testRestaurant1);

        DAO_Restaurant.updateRestaurants(1, "Fuddruckers", "1600 Pennsylvania Ave NW, Washington, DC ", "20500", "202-456-1111", "www.whitehouse.gov", "potus@us.gov");

        assertEquals("Fuddruckers", DAO_Restaurant.findByIdRestaurant(1).getName());
    }

    @Test
    public void deleteByIdRestaurants() throws Exception {
        Restaurant testRestaurant1 = setupRestaurant();
        DAO_Restaurant.addRestaurant(testRestaurant1);

        Restaurant testRestaurant2 = new Restaurant("Arby's", "1600 Pennsylvania Ave NW, Washington, DC ", "20500", "202-456-1111");
        DAO_Restaurant.addRestaurant(testRestaurant2);

        DAO_Restaurant.deleteByIdRestaurants(testRestaurant1.getRestaurantId());

        assertEquals(1, DAO_Restaurant.getAllRestaurants().size());
    }
}