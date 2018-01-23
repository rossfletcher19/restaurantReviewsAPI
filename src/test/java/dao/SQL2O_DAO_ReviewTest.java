package dao;

import com.sun.org.apache.regexp.internal.RE;
import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class SQL2O_DAO_ReviewTest {

    private org.sql2o.Connection conn;
    private SQL2O_DAO_Review DAO_Review;
    private SQL2O_DAO_Restaurant DAO_Restaurant;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "","");
        DAO_Review = new SQL2O_DAO_Review(sql2o);
        DAO_Restaurant = new SQL2O_DAO_Restaurant(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addReview() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        DAO_Restaurant.addRestaurant(testRestaurant);
        Review testReview = new Review("Captain Kirk", 3, "foodcoma!", 1);
        DAO_Review.addReview(testReview);
        assertEquals(1, testReview.getIdReview());
    }

    @Test
    public void getAllReviewsByRestaurant() throws Exception {

        Restaurant testRestaurant = setupRestaurant();
        DAO_Restaurant.addRestaurant(testRestaurant);

        Restaurant newRestaurant = setupRestaurant();
        DAO_Restaurant.addRestaurant(newRestaurant);

        Review testReview = new Review("Captain Kirk", 3, "foodcoma!", testRestaurant.getRestaurantId());
        DAO_Review.addReview(testReview);

        Review otherReview = new Review("Mr. Spock", 1, "passable", testRestaurant.getRestaurantId());
        DAO_Review.addReview(testReview);

        assertEquals(2, DAO_Review.getAllReviewsByRestaurant(testRestaurant.getRestaurantId()).size());
        assertEquals(0, DAO_Review.getAllReviewsByRestaurant(newRestaurant.getRestaurantId()).size());
    }

    @Test
    public void deleteByIdReview() throws Exception {
        Restaurant newRestaurant1 = setupRestaurant();
        DAO_Restaurant.addRestaurant(newRestaurant1);

        Review newReview1 = new Review("Captain Kirk", 8, "BARF!", newRestaurant1.getRestaurantId());
        DAO_Review.addReview(newReview1);

        assertEquals(1,DAO_Review.getAllReviewsByRestaurant(newRestaurant1.getRestaurantId()).size());
        DAO_Review.deleteByIdReview(newReview1.getIdReview());
        assertEquals(0,DAO_Review.getAllReviewsByRestaurant(newRestaurant1.getRestaurantId()).size());
    }

    public Restaurant setupRestaurant() {
        return new Restaurant("Screen Door","1234 SE Burnside", "97232", "503-123-4567","www.screendoor.com", "screendoor@email.com");
    }

}