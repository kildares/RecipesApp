package recipe.kildare.com.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import recipe.kildare.com.recipeapp.Entities.Recipe;
import recipe.kildare.com.recipeapp.utils.RecipeUtils;

/**
 * An activity representing a single Recipe detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link RecipeListActivity}.
 */
public class RecipeDetailActivity extends AppCompatActivity {


    private boolean mTwoPane;
    private Recipe mRecipe;
    private int mChosenOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mTwoPane = RecipeUtils.isTwoPane(this);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //

        Bundle intentBundle = getIntent().getExtras();
        if(intentBundle != null)
        {
            Recipe recipe = intentBundle.getParcelable(getString(R.string.key_recipe_data));
            if(recipe != null){
                mRecipe = recipe;
                mChosenOption = 0;
            }
        }

        if(mTwoPane && savedInstanceState == null){
            addRecipeDetailsFragment();
            if(mChosenOption != 0)
                addRecipeDetailListFragment();
            else
                addRecipeIngredientDetailsFragment();
        }
        else if(!mTwoPane && savedInstanceState == null){
            addRecipeDetailsFragment();
        }
    }

    public void addRecipeDetailListFragment()
    {
        RecipeDetailListFragment recipeDetailListFragment = new RecipeDetailListFragment();
        recipeDetailListFragment;
    }

    public void addRecipeIngredientDetailsFragment()
    {
        IngredientDetailFragment ingredientFragment= new IngredientDetailFragment();
        ingredientFragment.setContext(this);
        ingredientFragment.setCurrentIngredient(mRecipe.getIngredients());
        getSupportFragmentManager().beginTransaction().replace(R.id.fg_step_detail, ingredientFragment).commit();
    }

    public void addRecipeDetailsFragment()
    {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setContext(this);
        fragment.setSteps(mRecipe.getSteps());
        getSupportFragmentManager().beginTransaction().add(R.id.fg_recipe_detail_list, fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, RecipeListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
