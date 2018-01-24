package dao;

import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

public class SQL2O_DAO_Restaurant implements DAO_Restaurant {

    private final Sql2o sql2o;

    public SQL2O_DAO_Restaurant(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        String sql = "INSERT INTO restaurants (name, address, zipcode, phone, website, email) VALUES (:name, :address, :zipcode, :phone, :website, :email)";
        try (Connection con = sql2o.open()) {
            int restaurantId = (int) con.createQuery(sql)
                    .bind(restaurant)
                    .executeUpdate()
                    .getKey();
            restaurant.setRestaurantId(restaurantId);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addRestaurantToFoodtype(Restaurant restaurant, Foodtype foodtype) {
        String sql = "INSERT INTO restaurants_foodtypes (restaurantId, foodtypeId) VALUES (:restaurantId, :foodtypeId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("restaurantId", restaurant.getRestaurantId())
                    .addParameter("foodtypeId", foodtype.getIdFoodtype())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
            System.out.println("error message");
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        String sql = "SELECT * FROM restaurants";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                .executeAndFetch(Restaurant.class);
        }
    }

    @Override
    public List<Foodtype> getAllFoodTypesByRestaurant(int restaurantId) {
        List<Foodtype> foodtypes = new ArrayList();
        return foodtypes;
    }

    @Override
    public List<Restaurant> findByZipcode() {
        String sql = "SELECT * FROM restaurants WHERE zipcode = :zipcode";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Restaurant.class);
        }
    }


    @Override
    public Restaurant findByIdRestaurant(int restaurantId) {
        String sql = "SELECT * FROM restaurants WHERE restaurantId = :restaurantId";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("restaurantId", restaurantId)
                    .executeAndFetchFirst(Restaurant.class);
        }
    }

    @Override
    public int findRestaurantAverageRating(int restaurantId) {
        String sql = "SELECT * FROM reviews WHERE restaurantId = :restaurantId";
        int reviewTotal = 0;
        try (Connection con = sql2o.open()) {
            List<Review> reviews = con.createQuery(sql)
                    .addParameter("restaurantId", restaurantId)
                    .executeAndFetch(Review.class);
            // AARON'S FOR LOOP
//            for (int i = 0; i < reviews.size(); i++) {
//                reviewTotal += reviews.get(i).getRating();
//            } return reviewTotal / reviews.size();

            for (Review review : reviews) {
                reviewTotal += review.getRating();
            } return reviewTotal / reviews.size();
        }
    }

    @Override
    public void updateRestaurants(int restaurantId, String name, String address, String zipcode, String phone, String website, String email) {
        String sql = "UPDATE restaurants SET name = :name, address = :address, zipcode = :zipcode, phone = :phone, website = :website, email = :email WHERE restaurantId = :restaurantId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("address", address)
                    .addParameter("zipcode", zipcode)
                    .addParameter("phone", phone)
                    .addParameter("website", website)
                    .addParameter("email", email)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteByIdRestaurants(int restaurantId) {
        String sql = "DELETE FROM restaurants WHERE restaurantId = :restaurantId";
            try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("restaurantId", restaurantId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}