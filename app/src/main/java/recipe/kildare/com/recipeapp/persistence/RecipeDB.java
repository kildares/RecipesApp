package recipe.kildare.com.recipeapp.persistence;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by kilda on 4/10/2018.
 */

public class RecipeDB implements BaseColumns
{

    public static final Uri CONTENT_URI = RecipeDbContract.BASE_CONTENT_URI.buildUpon().appendPath(RecipeDbContract.PATH_RECIPE).build();

    public static final String TABLE_NAME = "RECIPE";

    public static final String COLUMN_TITLE = "title";

    public static final String COLUMN_ID = "recipe_id";

    public static final String COLUMN_IMAGE = "image_url";


    public static String createDbSQL()
    {
        return "CREATE TABLE " + TABLE_NAME + " ("              +
        RecipeDB._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "   +
        RecipeDB.COLUMN_TITLE + " TEXT NOT NULL, "              +
        RecipeDB.COLUMN_IMAGE + " TEXT NOT NULL, "              +
        RecipeDB.COLUMN_ID+ " TEXT NOT NULL, "                  +
        " UNIQUE (" + RecipeDB.COLUMN_ID                        +
                                        ") ON CONFLICT IGNORE)";
    }

    public static Uri buildQueryRecipeId(String id)
    {
        Uri uri = RecipeDbContract.BASE_CONTENT_URI.buildUpon().appendPath(RecipeDbContract.PATH_RECIPE).appendPath(id).build();
        return uri;
    }

}
