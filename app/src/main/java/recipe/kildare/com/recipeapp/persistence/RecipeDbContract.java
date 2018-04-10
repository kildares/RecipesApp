package recipe.kildare.com.recipeapp.persistence;

import android.net.Uri;

/**
 * Created by kilda on 4/8/2018.
 */

public class RecipeDbContract {

    public static final String CONTENT_AUTHORITY = "recipe.kildare.com.recipeapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_RECIPE = "recipe";

    public static final String PATH_INGREDIENT  = "ingredient";


}
