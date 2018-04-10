package recipe.kildare.com.recipeapp.Network;

import android.content.ContentValues;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import recipe.kildare.com.recipeapp.persistence.RecipeDbContract;

/**
 * Created by kilda on 4/9/2018.
 */

public class RecipeJSONUtils {

    public static final String RECIPES = "recipes";
    public static final String RECIPE = "recipe";
    public static final String PUBLISHER = "publisher";
    public static final String F2F_URL = "f2f_url";
    public static final String TITLE = "title";
    public static final String SOURCE_URL = "source_url";
    public static final String RECIPE_ID = "recipe_id";
    public static final String IMAGE_URL = "image_url";
    public static final String SOCIAL_RANK = "social_rank";
    public static final String PUBLISHER_URL = "publisher_url";
    public static final String INGREDIENTS = "ingredients";

    public static ContentValues[] parseRecipeList(String response) {

        try {
            JSONObject json = new JSONObject(response);

            JSONArray jsonArray = json.getJSONArray(RecipeJSONUtils.RECIPES);

            ContentValues[] contentValues = new ContentValues[jsonArray.length()];

            for(int i=0; i < jsonArray.length() ; i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                ContentValues contentValue = new ContentValues();
                contentValue.put(RecipeDbContract.RecipeEntry.COLUMN_ID, jsonObject.getString(RECIPE_ID));
                contentValue.put(RecipeDbContract.RecipeEntry.COLUMN_IMAGE, jsonObject.getString(IMAGE_URL));
                contentValue.put(RecipeDbContract.RecipeEntry.COLUMN_TITLE, jsonObject.getString(TITLE));

                contentValues[i] = contentValue;
            }

            return contentValues;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ContentValues[] parseRecipeDetail(String response) {

        try {
            JSONObject json = new JSONObject(response);

            JSONArray jsonArray = json.getJSONArray(RecipeJSONUtils.RECIPE);

            ContentValues[] contentValues = new ContentValues[jsonArray.length()];

            for(int i=0; i < jsonArray.length() ; i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                ContentValues contentValue = new ContentValues();
                contentValue.put(RecipeDbContract.RecipeEntry.COLUMN_ID, jsonObject.getString(RECIPE_ID));
                contentValue.put(RecipeDbContract.RecipeEntry.COLUMN_IMAGE, jsonObject.getString(IMAGE_URL));
                contentValue.put(RecipeDbContract.RecipeEntry.COLUMN_TITLE, jsonObject.getString(TITLE));

                contentValues[i] = contentValue;
            }

            return contentValues;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
