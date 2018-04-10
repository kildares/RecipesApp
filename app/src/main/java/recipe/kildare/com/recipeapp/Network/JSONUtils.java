package recipe.kildare.com.recipeapp.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import org.json.JSONObject;

/**
 * Created by kilda on 4/9/2018.
 */

public class JSONUtils {


    public static void parseRecipeList(String response) {

        Gson gson = new Gson();

        JsonParser parser = new JsonParser();

        JSONObject object1 = parser.parse(response)
    }
}
