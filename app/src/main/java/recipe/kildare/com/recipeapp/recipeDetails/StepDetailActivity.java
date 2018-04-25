package recipe.kildare.com.recipeapp.recipeDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import recipe.kildare.com.recipeapp.Entities.Ingredient;
import recipe.kildare.com.recipeapp.Entities.Recipe;
import recipe.kildare.com.recipeapp.Entities.Step;
import recipe.kildare.com.recipeapp.R;

public class StepDetailActivity extends AppCompatActivity {

    private Recipe mRecipe;

    private int mChosenOption;

    @BindView(R.id.bt_prev_step)
    Button mPrevButton;

    @BindView(R.id.bt_next_step)
    Button mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            mRecipe = extras.getParcelable(getString(R.string.key_recipe_data));
            mChosenOption = extras.getInt(getString(R.string.key_step_pos));
        }
        else{
            mRecipe = new Recipe("1","",new ArrayList<>(),new ArrayList<>(), "", "");
        }


        boolean isStep = (mChosenOption != -1);

        if(mRecipe.getSteps().size() > 0)
            replaceDetailedFragment(isStep);
    }

    /**
     * Adds the fragment with the detailed step list
     */
    public void replaceDetailedFragment(boolean isStep)
    {
        if(isStep)
        {
            StepDetailFragment fragment = new StepDetailFragment();
            fragment.setStep(mRecipe.getSteps().get(mChosenOption));
            getSupportFragmentManager().beginTransaction().replace(R.id.fg_step_detail, fragment).commit();
        }
        else
        {
            IngredientDetailFragment ingredientFragment= new IngredientDetailFragment();
            ingredientFragment.setContext(this);
            ingredientFragment.setCurrentIngredient(mRecipe.getIngredients());
            getSupportFragmentManager().beginTransaction().replace(R.id.fg_step_detail, ingredientFragment).commit();
        }
    }

    @OnClick({R.id.bt_next_step, R.id.bt_prev_step})
    public void loadNextStep(Button button)
    {
        boolean isNextBt = (button.getId() == mNextButton.getId());
        if(isNextBt){
            if(mChosenOption < mRecipe.getSteps().size() - 1 ){
                mChosenOption++;
                replaceDetailedFragment(true);
            }
        }
        else{
            if(mChosenOption > -1)
            {
                mChosenOption--;
                boolean isStep = (mChosenOption != -1);
                replaceDetailedFragment(isStep);
            }
        }
    }
}
