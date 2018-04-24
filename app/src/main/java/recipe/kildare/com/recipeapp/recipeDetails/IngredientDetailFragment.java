package recipe.kildare.com.recipeapp.recipeDetails;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import recipe.kildare.com.recipeapp.Entities.Ingredient;
import recipe.kildare.com.recipeapp.ListView.IngredientAdapter;
import recipe.kildare.com.recipeapp.R;
import recipe.kildare.com.recipeapp.RecipeListActivity;

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
            List<Ingredient> ingredients = savedInstanceState.getParcelableArrayList(getActivity().getString(R.string.key_recipe_data));

            mIngredient = ingredients;
            mContext = getActivity();
        }

        View rootView = inflater.inflate(R.layout.fragment_ingredient_detail, container, false);

        mViewIngredients = rootView.findViewById(R.id.lv_ingredients);

        ArrayAdapter<Ingredient> arrayAdapter = new IngredientAdapter(mContext,mIngredient);
        mViewIngredients.setAdapter(arrayAdapter);

        return rootView;
    }

    public void setContext(Context context){
        this.mContext = context;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(getActivity().getString(R.string.key_recipe_data), (ArrayList<Ingredient>) mIngredient);
        super.onSaveInstanceState(outState);
    }
}
