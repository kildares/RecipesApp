package recipe.kildare.com.recipeapp.Network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import recipe.kildare.com.recipeapp.Entities.Ingredient;
import recipe.kildare.com.recipeapp.Entities.Recipe;
import recipe.kildare.com.recipeapp.Entities.Step;

/**
 * Created by kilda on 4/9/2018.
 */

public class RecipeJSONUtils {

    public static final String RECIPE_ID = "id";
    public static final String RECIPE_NAME = "name";
    public static final String RECIPE_INGREDIENTS = "ingredients";
    public static final String RECIPE_STEPS = "steps";
    public static final String RECIPE_SERVINGS = "serving";
    public static final String RECIPE_IMAGE = "image";
    public static final String INGREDIENT_QUANTITY = "quantity";
    public static final String INGREDIENT_MEASURE = "measure";
    public static final String INGREDIENT_NAME = "ingredient";
    public static final String STEP_id = "id";
    public static final String STEP_SHORT_DESCRIPTION = "shortDescription";
    public static final String STEP_DESCRIPTION = "description";
    public static final String STEP_VIDEO_URL = "videoURL";
    public static final String STEP_THUMBNAIL = "thumbnailURL";

    public static List<Recipe> parseRecipeList(String response) {

        try {

            JSONObject json = new JSONObject(response);
            List<Recipe> recipes = new ArrayList<>();

            for(int i=0; i < json.length() ; i++){

                JSONObject jsonObject = json.getJSONObject(i);

                String id = jsonObject.getString(RECIPE_ID);
                String name = jsonObject.getString(RECIPE_NAME);
                List<Ingredient> ingredients = RecipeJSONUtils.parseRecipeIngredient(jsonObject.getJSONArray(RECIPE_INGREDIENTS));
                List<Step> steps = RecipeJSONUtils.parseRecipeSteps(jsonObject.getJSONArray(RECIPE_STEPS));
                String servings = jsonObject.getString(RECIPE_SERVINGS);
                String image = jsonObject.getString(RECIPE_IMAGE);

                Recipe recipe = new Recipe(id, name, ingredients, steps, servings, image);

                recipes.add(recipe);
            }

            return recipes;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static List<Ingredient> parseRecipeIngredient(JSONArray ingredients) {

        try {

            List<Ingredient> list = new ArrayList<Ingredient>();
            for(int i=0; i < list.size() ; i++){

                JSONObject jsonObject = (JSONObject) ingredients.get(i);
                String quantity = jsonObject.getString(INGREDIENT_QUANTITY);
                String measure = jsonObject.getString(INGREDIENT_MEASURE);
                String name = jsonObject.getString(INGREDIENT_NAME);


                Ingredient ingredient = new Ingredient(name, quantity, measure);
                list.add(ingredient);
            }

            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Step> parseRecipeSteps(JSONArray steps)
    {

        try {

            List<Step> list = new ArrayList<Step>();
            for(int i=0; i < list.size() ; i++){

                JSONObject jsonObject = (JSONObject) steps.get(i);
                String id = jsonObject.getString(STEP_id);
                String shortDescription = jsonObject.getString(STEP_SHORT_DESCRIPTION);
                String description = jsonObject.getString(STEP_DESCRIPTION);
                String videoURL = jsonObject.getString(STEP_VIDEO_URL);
                String thumbnailURL = jsonObject.getString(STEP_THUMBNAIL);

                Step step = new Step(id, shortDescription, description, videoURL, thumbnailURL);
                list.add(step);
            }

            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
