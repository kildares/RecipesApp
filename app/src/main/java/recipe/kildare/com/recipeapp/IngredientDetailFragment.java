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
import recipe.kildare.com.recipeapp.Entities.Recipe;
import recipe.kildare.com.recipeapp.Entities.Step;
import recipe.kildare.com.recipeapp.ListView.IngredientAdapter;

/**
 * A fragment representing a single Recipe detail screen.
 * This fragment is either contained in a {@link RecipeListActivity}
 * in two-pane mode (on tablets) or a {@link RecipeDetailActivity}
 * on handsets.
 */
public class IngredientDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    private Context mContext;
    private List<Ingredient> mIngredient;
    private ListView mViewIngredients;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public IngredientDetailFragment() {}

    public void setCurrentIngredient(List<Ingredient> ingredients){
        mIngredient= ingredients;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mIngredient = savedInstanceState.getParcelable(getActivity().getString(R.string.key_recipe_data));
        }

        View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);

        mViewIngredients = rootView.findViewById(R.id.lv_ingredients);

        ArrayAdapter<Ingredient> arrayAdapter = new IngredientAdapter(mContext,mIngredient);
        mViewIngredients.setAdapter(arrayAdapter);

        return rootView;
    }

    public void setContext(Context context){
        this.mContext = context;
    }

}
