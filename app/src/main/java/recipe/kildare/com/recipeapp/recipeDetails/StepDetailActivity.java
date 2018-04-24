package recipe.kildare.com.recipeapp.recipeDetails;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import recipe.kildare.com.recipeapp.Entities.Recipe;
import recipe.kildare.com.recipeapp.Entities.Step;
import recipe.kildare.com.recipeapp.ListView.StepAdapter;
import recipe.kildare.com.recipeapp.recipeDetails.interfaces.LoadStepDetail;
import recipe.kildare.com.recipeapp.R;

public class StepDetailActivity extends AppCompatActivity {


    private Recipe mRecipe;

    private int mChosenOption;

    @BindView(R.id.bt_prev_step)
    Button mPrevButton;

    @BindView(R.id.bt_next_step)
    Button mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        mRecipe = extras.getParcelable(getString(R.string.key_recipe_data));
        mChosenOption = extras.getInt(getString(R.string.key_step_pos));

        boolean isStep = (mChosenOption != -1);

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
            fragment.setContext(this);
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
