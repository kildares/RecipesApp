package recipe.kildare.com.recipeapp.Entities;

import java.util.List;

/**
 * Created by kilda on 4/10/2018.
 */

public class Recipe {

    public String Title;
    public String Image_URL;
    public String Recipe_ID;
    public List<Ingredient> Ingredients;

    public String getIngredientList()
    {
        String ingredientList = "";

        for(Ingredient ingredient : Ingredients){
            ingredientList += " \n" + ingredient.Value;
        }
        return ingredientList;
    }
}
