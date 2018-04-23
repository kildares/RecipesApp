package recipe.kildare.com.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import recipe.kildare.com.recipeapp.Entities.Ingredient;
import recipe.kildare.com.recipeapp.Entities.Step;
import recipe.kildare.com.recipeapp.ListView.IngredientAdapter;
import recipe.kildare.com.recipeapp.ListView.StepAdapter;

/**
 * A fragment representing a single Recipe detail screen.
 * This fragment is either contained in a {@link RecipeListActivity}
 * in two-pane mode (on tablets) or a {@link RecipeDetailActivity}
 * on handsets.
 */
public class RecipeDetailFragment extends Fragment implements LoadStepDetail {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    public Context mContext;

    public List<Step> mSteps;

    private ListView mViewSteps;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeDetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mRecipe = savedInstanceState.getParcelable(getActivity().getString(R.string.key_recipe_data));
        }

        View rootView = inflater.inflate(R.layout.fragment_recipe_detail_list, container, false);

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
            ArrayAdapter<Step> stepArrayAdapter = new StepAdapter(getActivity(),mRecipe, this);
            mViewSteps.setAdapter(stepArrayAdapter);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(getActivity().getString(R.string.key_recipe_data),mRecipe);
    }

    @Override
    public void loadStepDetail(int position) {
        Intent intent = new Intent(mContext, StepDetailActivity.class);
        intent.putExtra(mContext.getString(R.string.key_step_pos), position);
        intent.putExtra(mContext.getString(R.string.key_recipe_data), mRecipe);
        mContext.startActivity(intent);
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
