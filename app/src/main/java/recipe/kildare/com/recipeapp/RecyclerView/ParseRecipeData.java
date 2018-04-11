package recipe.kildare.com.recipeapp.RecyclerView;

import java.util.List;

import recipe.kildare.com.recipeapp.Entities.Recipe;

/**
 * Created by kilda on 4/11/2018.
 */

public interface ParseRecipeData {

    void onParseRecipeDataResult(List<Recipe> recipes);
}
