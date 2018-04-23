package recipe.kildare.com.recipeapp.recipeDetails;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
import recipe.kildare.com.recipeapp.Entities.Recipe;
import recipe.kildare.com.recipeapp.Entities.Step;
import recipe.kildare.com.recipeapp.ListView.StepAdapter;
import recipe.kildare.com.recipeapp.recipeDetails.interfaces.LoadStepDetail;
import recipe.kildare.com.recipeapp.R;

public class StepDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_step_full_description)
     TextView mFullStepDescription;

    @BindView(R.id.lv_steps)
    ListView mStepsView;

    private Recipe mRecipe;
    private int mStepPosition;


    private SimpleExoPlayer mExoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        mRecipe = extras.getParcelable(getString(R.string.key_recipe_data));
        mStepPosition = extras.getInt(getString(R.string.key_step_pos));


    }

}
