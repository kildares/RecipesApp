package recipe.kildare.com.recipeapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import butterknife.BindView;
import recipe.kildare.com.recipeapp.Entities.Recipe;

/**
 * A fragment representing a single Recipe detail screen.
 * This fragment is either contained in a {@link RecipeListActivity}
 * in two-pane mode (on tablets) or a {@link RecipeDetailActivity}
 * on handsets.
 */
public class RecipeDetailFragment extends Fragment implements LoadRecipeOnFragment{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private Context mContext;

    private Recipe mRecipe;

    @BindView(R.id.lv_ingredients)
    ListView mViewIngredients;

    @BindView(R.id.lv_steps)
    ListView mViewSteps;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeDetailFragment() {

    }

    public void setCurrentRecipe(Recipe recipe){
        mRecipe = recipe;
        loadRecipeData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        return rootView;
    }

    public void showRecipeData()
    {
        if(mRecipe != null){
            mViewSteps.setAdapter(new ArrayAdapter<String>(mContext,R.id.cl_ingredient_detail));
    }


    @Override
    public void loadRecipeData(Recipe recipe) {

        if(recipe != null){
            mRecipe = recipe;
            showRecipeData();
        }

    }
}
