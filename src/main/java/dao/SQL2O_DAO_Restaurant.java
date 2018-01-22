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
        String sql = "INSERT INTO restaurant (name, address, zipcode, phone, website, email) VALUES (:name, :address, :zipcode, :phone, :website, :email)";
        try (Connection con = sql2o.open()) {
            int idRestaurant = (int) con.createQuery(sql)
                    .bind(restaurant)
                    .executeUpdate()
                    .getKey();
            restaurant.setIdRestaurant(idRestaurant);
        } catch (Sql2oException ex) {
            System.out.println(ex);
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
    public List<Restaurant> findByZipcode() {
        String sql = "SELECT * FROM restaurants WHERE zipcode = :zipcode";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Restaurant.class);
        }
    }


    @Override
    public Restaurant findByIdRestaurant(int idRestaurant) {
        String sql = "SELECT * FROM restaurants WHERE idRestaurant = :idRestaurant";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("idRestaurant", idRestaurant)
                    .executeAndFetchFirst(Restaurant.class);
        }
    }

    @Override
    public int findRestaurantAverageRating(int restaurantId) {
        String sql = "SELECT * FROM reviews WHERE idRestaurant2 = :idRestaurant2";
        int reviewTotal = 0;
        try (Connection con = sql2o.open()) {
            List<Review> reviews = con.createQuery(sql)
                    .addParameter("idRestaurant2", restaurantId)
                    .executeAndFetch(Review.class);
            for (Review review : reviews) {
                reviewTotal += review.getIdReview();
            } return reviewTotal / reviews.size();
//            Aaron's for loop.
//            for (int i = 0; i < reviews.size(); i++) {
//                total += reviews.get(i).getRating();
        }
    }

    @Override
    public void updateRestaurants(int id, String name, String address, String zipcode, String phone, String website, String email) {
        String sql = "UPDATE restaurants SET name = :name, address = :address, zipcode = :zipcode, phone = :phone, website = :website, email = :email";
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
    public void deleteByIdRestaurants(int idRestaurant) {
        String sql = "DELETE FROM restaurants WHERE idRestaurant = :idRestaurant";
            try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("idRestaurant", idRestaurant)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}