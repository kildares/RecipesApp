package recipe.kildare.com.recipeapp.Network;

import android.util.Log;

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

    private static final String LOG_ERROR_TAG = "LOG_ERROR";
    public static List<Recipe> parseRecipeList(String response) {

        JSONArray jsonArray= null;

        try {
            jsonArray = new JSONArray(response);
        }
        catch(JSONException e) {
            Log.d(LOG_ERROR_TAG, "Invalid JSON file");
            return null;
        }

        List<Recipe> recipes = new ArrayList<>();
        for(int i=0; i < jsonArray.length() ; i++){

            List<Ingredient> ingredients = new ArrayList<>();
            List<Step> steps = new ArrayList<>();
            JSONObject jsonObject = null;
            Recipe recipe;
            String id , name , servings = null, image = null;
            try{
                jsonObject = jsonArray.getJSONObject(i);

                id = jsonObject.getString(RECIPE_ID);
                name = jsonObject.getString(RECIPE_NAME);
            }catch(JSONException e) {
                Log.e(LOG_ERROR_TAG,"recipe without obligatory value RECIPE_ID or NAME");
                continue;
            }

            try {

                ingredients = RecipeJSONUtils.parseRecipeIngredient(jsonObject.getJSONArray(RECIPE_INGREDIENTS));
            }catch(JSONException e)
            {
                Log.e(LOG_ERROR_TAG,"recipe without ingredients");
            }
            try {
                steps = RecipeJSONUtils.parseRecipeSteps(jsonObject.getJSONArray(RECIPE_STEPS));
            }catch(JSONException e) {
                Log.e(LOG_ERROR_TAG, "recipe without steps");
            }

            try {
                servings = jsonObject.getString(RECIPE_SERVINGS);
            }catch(JSONException e) {

            }
            try {
                image = jsonObject.getString(RECIPE_IMAGE);
            }catch(JSONException e) {
            }

            recipe = new Recipe(id, name, ingredients, steps, servings, image);
            recipes.add(recipe);
        }

        return recipes;
    }



    public static List<Ingredient> parseRecipeIngredient(JSONArray ingredients) {

        List<Ingredient> list = new ArrayList<Ingredient>();
        for(int i=0; i < ingredients.length() ; i++){

            try{
                JSONObject jsonObject = (JSONObject) ingredients.get(i);
                String quantity = jsonObject.getString(INGREDIENT_QUANTITY);
                String measure = jsonObject.getString(INGREDIENT_MEASURE);
                String name = jsonObject.getString(INGREDIENT_NAME);
                Ingredient ingredient = new Ingredient(name, quantity, measure);
                list.add(ingredient);

            }catch(JSONException e){
                Log.e(LOG_ERROR_TAG,"PARSING RECIPE INGREDIENTS");
                continue;
            }
        }

        return list;

    }

    public static List<Step> parseRecipeSteps(JSONArray steps)
    {
        List<Step> list = new ArrayList<Step>();
        for(int i=0; i < steps.length() ; i++){

            JSONObject jsonObject = null;
            String id, shortDescription, description, videoURL = "";
            try {
                jsonObject = (JSONObject) steps.get(i);
                id = jsonObject.getString(STEP_id);
                shortDescription = jsonObject.getString(STEP_SHORT_DESCRIPTION);
                description = jsonObject.getString(STEP_DESCRIPTION);

            } catch (JSONException e) {
                Log.e(LOG_ERROR_TAG,"PARSING OBLIGATORY RECIPE STEPS");
                continue;
            }

            try{
                videoURL = jsonObject.getString(STEP_VIDEO_URL);
            }catch(JSONException e){
                Log.e(LOG_ERROR_TAG,"PARSING OBLIGATORY RECIPE VIDEO");
            }

            Step step = new Step(id, shortDescription, description, videoURL, null);
            list.add(step);

        }
        return list;

    }
}
