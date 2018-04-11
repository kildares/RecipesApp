package recipe.kildare.com.recipeapp.Entities;

import java.util.List;

/**
 * Created by kilda on 4/10/2018.
 */

public class Recipe {

    private String Title;
    private String Image_URL;
    private String Recipe_ID;
    private List<Ingredient> Ingredients;
    private List<Step> Steps;
    private String Servings;
    public Recipe(String recipe_ID, String title, List<Ingredient> ingredients, List<Step> steps, String servings, String imageUrl){
        Recipe_ID = recipe_ID;
        this.Title = title;
        Ingredients = ingredients;
        Steps = steps;
        Servings = servings;
        Image_URL = imageUrl;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage_URL() {
        return Image_URL;
    }

    public void setImage_URL(String image_URL) {
        Image_URL = image_URL;
    }

    public String getRecipe_ID() {
        return Recipe_ID;
    }

    public void setRecipe_ID(String recipe_ID) {
        Recipe_ID = recipe_ID;
    }

    public List<Ingredient> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        Ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return Steps;
    }

    public void setSteps(List<Step> steps) {
        Steps = steps;
    }
}
