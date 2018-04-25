package recipe.kildare.com.recipeapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import java.util.Set;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget);

        String ingredient = getIngredientText(context);

        views.setTextViewText(R.id.widget_txt_ingredient, ingredient);

        PendingIntent loadPendingIntent = createLoadPendingIntent(context, appWidgetId, false);
        views.setOnClickPendingIntent(R.id.wid_bt_ing_prev, loadPendingIntent);

        loadPendingIntent = createLoadPendingIntent(context, appWidgetId, true);
        views.setOnClickPendingIntent(R.id.wid_bt_ing_next, loadPendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static String getIngredientText(Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> ingredients = sharedPreferences.getStringSet(context.getString(R.string.key_pref_ing_set), null);
        int position = sharedPreferences.getInt(context.getString(R.string.key_pref_ing_pos),0);
        if(ingredients == null)
            return context.getString(R.string.wid_ing_default);
        else
            return (String) ingredients.toArray()[position];
    }

    private static PendingIntent createLoadPendingIntent(Context context, int appWidgetId, boolean isNext){
        Intent intent = new Intent(context, RecipeUpdateService.class);
        intent.putExtra(context.getString(R.string.key_widget_id),appWidgetId);
        if(isNext)
            intent.setAction(RecipeUpdateService.ACTION_LOAD_NEXT_INGREDIENT);
        else
            intent.setAction(RecipeUpdateService.ACTION_LOAD_PREV_INGREDIENT);

        return PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

