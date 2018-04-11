package recipe.kildare.com.recipeapp;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import recipe.kildare.com.recipeapp.persistence.RecipeDB;
import recipe.kildare.com.recipeapp.persistence.RecipeDataLoad;

/**
 * Created by kilda on 4/10/2018.
 */

public class RecipeAsyncTask extends AsyncTask<ContentValues, Void, Integer> {

    RecipeDataLoad mRecipeDataLoad;

    Context mContext;

    public RecipeAsyncTask(Context context, RecipeDataLoad recipeDataLoad){
        mContext = context ;
        mRecipeDataLoad = recipeDataLoad;
    }

    @Override
    protected Integer doInBackground(ContentValues... contentValues) {

        if(contentValues == null || contentValues.length == 0){
            return 0;
        }

        int result = mContext.getContentResolver().bulkInsert(RecipeDB.CONTENT_URI, contentValues);

        mRecipeDataLoad.onRecipeDataLoaded();
        return result;
    }

}
