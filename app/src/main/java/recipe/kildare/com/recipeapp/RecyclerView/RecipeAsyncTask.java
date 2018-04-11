package recipe.kildare.com.recipeapp.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import recipe.kildare.com.recipeapp.Entities.Recipe;
import recipe.kildare.com.recipeapp.Network.RecipeJSONUtils;

/**
 * Created by kilda on 4/10/2018.
 */

public class RecipeAsyncTask extends AsyncTask<String, Void, Integer> {

    ParseRecipeData mParseRecipeData;

    public RecipeAsyncTask(ParseRecipeData parseRecipeData){
        this.mParseRecipeData = parseRecipeData;
    }

    @Override
    protected Integer doInBackground(String... data) {

        if(data == null || data.length == 0){
            return 0;
        }
        List<Recipe> parsedData = RecipeJSONUtils.parseRecipeList(data[0]);

        mParseRecipeData.onParseRecipeDataResult(parsedData);
        return parsedData.size();
    }

}
