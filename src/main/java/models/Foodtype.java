package models;

public class Foodtype {
    private String foodtype;
    private int idFoodtype;

    //CONSTRUCTOR
    public Foodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    //GETTER
    public String getFoodtype() {
        return foodtype;
    }

    public int getIdFoodtype() {
        return idFoodtype;
    }

    //SETTER
    public void setName(String foodtype) {
        this.foodtype = foodtype;
    }

    public void setIdFoodtype(int idFoodtype) {
        this.idFoodtype = idFoodtype;
    }

    //EQUALS AND HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foodtype foodtype = (Foodtype) o;

        if (idFoodtype != foodtype.idFoodtype) return false;
        return foodtype.equals(foodtype.foodtype);
    }

    @Override
    public int hashCode() {
        int result = foodtype.hashCode();
        result = 31 * result + idFoodtype;
        return result;
    }
}
