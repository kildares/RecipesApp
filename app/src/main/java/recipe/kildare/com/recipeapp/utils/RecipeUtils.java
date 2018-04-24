package recipe.kildare.com.recipeapp.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

/**
 * Created by kilda on 4/23/2018.
 */

public class RecipeUtils {

    private static final int TWO_PANE_WIDTH = 600;

    public static final boolean isTwoPane(Context context)
    {
        Configuration configuration = context.getResources().getConfiguration();
        int screenWitdhDp = configuration.screenWidthDp;
        Log.d("Screen WIDTH",Integer.toString(screenWitdhDp));
        return screenWitdhDp >= TWO_PANE_WIDTH;
    }
}
