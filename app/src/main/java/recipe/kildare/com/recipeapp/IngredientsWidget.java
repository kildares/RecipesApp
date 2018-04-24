package recipe.kildare.com.recipeapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.RemoteViews;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget);

        views.setTextViewText(R.id.appwidget_text, widgetText);

        PendingIntent loadNetworkPendingIntent = createNetworkPendingIntent(context, appWidgetId);
        views.setOnClickPendingIntent(R.id.widget_txt_recipe, loadNetworkPendingIntent );

        PendingIntent loadPendingIntent = createLoadPendingIntent(context, appWidgetId,false, true);
        views.setOnClickPendingIntent(R.id.wid_bt_recipe_next, loadPendingIntent);

        loadPendingIntent = createLoadPendingIntent(context, appWidgetId, false, false);
        views.setOnClickPendingIntent(R.id.wid_bt_recipe_prev, loadPendingIntent);

        loadPendingIntent = createLoadPendingIntent(context, appWidgetId, true, true);
        views.setOnClickPendingIntent(R.id.wid_bt_ing_next, loadPendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static PendingIntent createLoadPendingIntent(Context context, int appWidgetId, boolean isIngredient, boolean isNext){
        Intent intent = new Intent(context, RecipeListActivity.class);
        if(isIngredient && isNext)
            intent.setAction(RecipeUpdateService.ACTION_LOAD_NEXT_INGREDIENT);
        else if(isIngredient && !isNext)
            intent.setAction(RecipeUpdateService.ACTION_LOAD_PREV_INGREDIENT);
        else if(!isIngredient && isNext)
            intent.setAction(RecipeUpdateService.ACTION_LOAD_NEXT_RECIPE);
        else
            intent.setAction(RecipeUpdateService.ACTION_LOAD_PREV_RECIPE);
        intent.putExtra(context.getString(R.string.key_widget_id), appWidgetId);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        return pendingIntent;
    }

    private static PendingIntent createNetworkPendingIntent(Context context, int appWidgetId)
    {
        Intent intent = new Intent(context, RecipeListActivity.class);
        intent.setAction(RecipeUpdateService.ACTION_LOAD_NETWORK_RECIPES);
        intent.putExtra(context.getString(R.string.key_widget_id), appWidgetId);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        return pendingIntent;
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

