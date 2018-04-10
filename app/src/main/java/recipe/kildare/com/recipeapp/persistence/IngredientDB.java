package recipe.kildare.com.recipeapp.persistence;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by kilda on 4/10/2018.
 */

public class IngredientDB implements BaseColumns{


    public static final Uri CONTENT_URI = RecipeDbContract.BASE_CONTENT_URI.buildUpon().appendPath(RecipeDbContract.PATH_INGREDIENT).build();

    public static final String TABLE_NAME = "INGREDIENT";

    public static final String COLUMN_VALUE = "value";

    public static final String COLUMN_ID = "ingredient_id";

    public static final String COLUMN_RECIPE_FK = "recipe_fk";

    public static Uri buildQueryRecipeId(String id)
    {
        Uri uri = RecipeDbContract.BASE_CONTENT_URI.buildUpon().appendPath(RecipeDbContract.PATH_INGREDIENT).appendPath(id).build();
        return uri;
    }

    public static String createDbSQL()
    {
        return "CREATE TABLE " + TABLE_NAME + " ("              +
                    IngredientDB._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "   +
                    IngredientDB.COLUMN_VALUE+ " TEXT NOT NULL, "              +
                    IngredientDB.COLUMN_RECIPE_FK+ " TEXT NOT NULL, "              +
                    "FOREIGN KEY(" + IngredientDB.COLUMN_RECIPE_FK + ") REFERENCES " + RecipeDB.TABLE_NAME + "(" + RecipeDB.COLUMN_ID +") " +
                ")";
    }

}
