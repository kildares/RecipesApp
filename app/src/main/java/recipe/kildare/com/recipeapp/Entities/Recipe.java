package recipe.kildare.com.recipeapp.Entities;

import java.util.List;

import recipe.kildare.com.recipeapp.persistence.RecipeDB;

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


    public static final String[] RECIPE_PROJECTION = {
            RecipeDB.COLUMN_ID,
            RecipeDB.COLUMN_TITLE,
            RecipeDB.COLUMN_IMAGE,
    };

    public static final int INDEX_RECIPE_ID = 0;
    public static final int INDEX_RECIPE_TITLE = 1;
    public static final int INDEX_RECIPE_IMAGE = 2;


}
