package recipe.kildare.com.recipeapp.Entities;

/**
 * Created by kilda on 4/10/2018.
 */

public class Ingredient {

    public String Quantity;
    public String Measure;
    public String Name;

    public Ingredient(String name, String quantity, String measure){
        this.Name  = name;
        this.Quantity = quantity;
        this.Measure = measure;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getMeasure() {
        return Measure;
    }

    public void setMeasure(String measure) {
        Measure = measure;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
