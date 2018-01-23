package dao;

import models.Foodtype;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 1/23/18.
 */
public class SQL2O_DAO_FoodtypeTest {

    private org.sql2o.Connection conn;
    private SQL2O_DAO_Restaurant DAO_Restaurant;
    private SQL2O_DAO_Foodtype DAO_Foodtype;

    public Foodtype setupNewFoodType() {
        return new Foodtype("BBQ");
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
    public void deleteByIdFoodtype() throws Exception {


    }

    @Test
    public void clearAllFoodtypes() throws Exception {
    }

}