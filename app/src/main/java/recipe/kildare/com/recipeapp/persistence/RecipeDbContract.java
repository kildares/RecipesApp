package recipe.kildare.com.recipeapp.persistence;

import android.net.Uri;

/**
 * Created by kilda on 4/8/2018.
 */

public class RecipeDbContract {

    public static final String CONTENT_AUTHORITY = "recipe.kildare.com.recipeapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_RECIPE = "recipe";

    public static final class RecipeEntry
    {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPE).build();


        public static final String TABLE_NAME = "RECIPE";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_ID = "recipe_id";

        public static final String COLUMN_IMAGE = "image_url";

        public static Uri buildQueryRecipeId(String id)
        {
            Uri uri = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPE).appendPath(id).build();
            return uri;
        }

    }
}
