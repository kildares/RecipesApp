package recipe.kildare.com.recipeapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.zip.Inflater;

import butterknife.BindView;
import recipe.kildare.com.recipeapp.Entities.Ingredient;
import recipe.kildare.com.recipeapp.Entities.Recipe;
import recipe.kildare.com.recipeapp.Entities.Step;
import recipe.kildare.com.recipeapp.ListView.IngredientAdapter;
import recipe.kildare.com.recipeapp.ListView.StepAdapter;

/**
 * A fragment representing a single Recipe detail screen.
 * This fragment is either contained in a {@link RecipeListActivity}
 * in two-pane mode (on tablets) or a {@link RecipeDetailActivity}
 * on handsets.
 */
public class RecipeDetailFragment extends Fragment{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    public Context mContext;

    private Recipe mRecipe;

    private ListView mViewIngredients;

    private ListView mViewSteps;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeDetailFragment() {

    }


    public void setCurrentRecipe(Recipe recipe){
        mRecipe = recipe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mRecipe = savedInstanceState.getParcelable(getActivity().getString(R.string.key_recipe_data));
        }

        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        mViewIngredients = rootView.findViewById(R.id.lv_ingredients);
        mViewSteps = rootView.findViewById(R.id.lv_steps);

        showRecipeData();

        return rootView;
    }

    public void showRecipeData()
    {
        if(mRecipe != null){
            ArrayAdapter<Ingredient> ingredientArrayAdapter = new IngredientAdapter(getActivity(), mRecipe.getIngredients());
            mViewIngredients.setAdapter(ingredientArrayAdapter);
            ArrayAdapter<Step> stepArrayAdapter = new StepAdapter(getActivity(),mRecipe.getSteps());
            mViewSteps.setAdapter(stepArrayAdapter);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(getActivity().getString(R.string.key_recipe_data),mRecipe);
    }
}
