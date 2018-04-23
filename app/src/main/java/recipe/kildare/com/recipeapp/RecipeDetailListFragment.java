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
import android.widget.TextView;

import java.util.List;

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
public class RecipeDetailListFragment extends Fragment implements LoadStepDetail {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */

    private LoadStepDetail mLoadStepDetail;
    private LoadIngredients mLoadIngredients;

    private Context mContext;
    private List<Step> mStep;
    private List<Ingredient> mIngredients;

    private ListView mList;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeDetailListFragment() {}

    public void setCurrentStep(List<Step> steps){
        mStep = steps;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mStep = savedInstanceState.getParcelable(getActivity().getString(R.string.key_recipe_data));
        }

        View rootView = inflater.inflate(R.layout.fragment_recipe_detail_list, container, false);

        TextView textView = rootView.findViewById(R.id.tv_ingredient_list);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoadIngredients.loadIngredients();
            }
        });

        mList = rootView.findViewById(R.id.lv_recipe_detail_list);

        StepAdapter stepList = new StepAdapter(mContext, mStep, this);
        mList.setAdapter(stepList);

        return rootView;
    }

    @Override
    public void loadStepDetail(int position) {
        mLoadStepDetail.loadStepDetail(position);
    }

    public void setLoadStepDetail(LoadStepDetail loadStepDetail){
        this.mLoadStepDetail = loadStepDetail;
    }

    public void setContext(Context context){
        this.mContext = context;
    }

    public void setIngredients(List<Ingredient> ingredients){
        this.mIngredients = ingredients;
    }

}