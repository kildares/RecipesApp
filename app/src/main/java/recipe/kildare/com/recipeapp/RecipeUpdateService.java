package recipe.kildare.com.recipeapp;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import java.util.Set;

/**
 * Created by kilda on 4/24/2018.
 */

public class RecipeUpdateService extends IntentService {

    public static final String ACTION_LOAD_NEXT_INGREDIENT = "recipe.kildare.com.recipeapp.load_next_ingredient";
    public static final String ACTION_LOAD_PREV_INGREDIENT = "recipe.kildare.com.recipeapp.load_next_prev";

    public RecipeUpdateService(String name) {
        super(name);
    }

    public RecipeUpdateService()
    {
        super("RecipeUpdateService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null){
            final String action = intent.getAction();
            Bundle extras = intent.getExtras();
            final int AppWidgetId = (extras == null) ? 0 : extras.getInt(getString(R.string.key_widget_id),0);
            handleActionLoadRecipeData(action, AppWidgetId );
        }
    }

    private void handleActionLoadRecipeData(String action, int appWidgetId)
    {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        int position = preferences.getInt(getString(R.string.key_pref_ing_pos),0);

        Set<String> recipes = preferences.getStringSet(getString(R.string.key_pref_ing_set),null);
        if(action == ACTION_LOAD_NEXT_INGREDIENT){
            if(position < (recipes.size() - 1)){
                position++;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(getString(R.string.key_pref_ing_pos),position);
                editor.apply();
            }
        }
        else if(action == ACTION_LOAD_PREV_INGREDIENT){
            if(position > 0 ){
                position--;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(getString(R.string.key_pref_ing_pos),position);
                editor.apply();
            }
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        if(appWidgetId == 0){
            int [] widgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, IngredientsWidget.class));
            for(int i = 0; i < widgetIds.length ; i++)
                IngredientsWidget.updateAppWidget(this, appWidgetManager, widgetIds[i]);
        }
        else
            IngredientsWidget.updateAppWidget(this, appWidgetManager, appWidgetId);
    }


}
