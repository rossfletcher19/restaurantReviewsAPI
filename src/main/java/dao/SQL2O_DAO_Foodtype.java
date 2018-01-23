package dao;

import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class SQL2O_DAO_Foodtype implements DAO_Foodtype {

    private final Sql2o sql2o;

    public SQL2O_DAO_Foodtype(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void addFoodtype(Foodtype foodtype) {
        String sql = "INSERT INTO foodtypes (foodtype) VALUES (:foodtype)";
        try (Connection con = sql2o.open()) {
            int foodtypeId = (int) con.createQuery(sql)
                    .bind(foodtype)
                    .executeUpdate()
                    .getKey();
            foodtype.setIdFoodtype(foodtypeId);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Foodtype> getAllFoodtypes() {
        String sql = "SELECT * FROM foodtypes";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Foodtype.class);
        }
    }

    @Override
    public Foodtype findbyIdFoodtype(int foodtypeId) {
        String sql = "SELECT * FROM foodtypes WHERE foodtypeId = :foodtypeId";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("foodtypeId", foodtypeId)
                    .executeAndFetchFirst(Foodtype.class);
        }
    }

    @Override
    public void updateFoodtype(int foodtypeId, String foodtype) {
        String sql = "UPDATE foodtypes SET foodtype = :foodtype WHERE foodtypeId = :foodtypeId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("foodtype", foodtype)
                    .addParameter("foodtypeId", foodtypeId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteByIdFoodtype(int foodtypeId) {
        String sql = "DELETE FROM foodtypes WHERE foodtypeId = :foodtypeId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("foodtypeId", foodtypeId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllFoodtypes() {
        String sql = "DELETE FROM foodtypes";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
