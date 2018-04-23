package recipe.kildare.com.recipeapp;

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
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import recipe.kildare.com.recipeapp.Entities.Recipe;
import recipe.kildare.com.recipeapp.Entities.Step;
import recipe.kildare.com.recipeapp.ListView.StepAdapter;

public class StepDetailActivity extends AppCompatActivity implements ExoPlayer.EventListener, LoadStepDetail {

    @BindView(R.id.exo_step_detail)
     SimpleExoPlayerView mExoPlayerView;

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

        loadStepDetail(mStepPosition);

        if (mStepsView != null) {
            showRecipeList();
        }
    }


    public void showRecipeList()
    {
        ArrayAdapter<Step> stepArrayAdapter = new StepAdapter(this,mRecipe, this);
        mStepsView.setAdapter(stepArrayAdapter );
    }

    @Override
    public void loadStepDetail(int position) {
        String videoUrl = mRecipe.getSteps().get(mStepPosition).getVideoURL();

        String description = mRecipe.getSteps().get(mStepPosition).getDescription();
        mFullStepDescription.setText(description);

        if(videoUrl != null && !videoUrl.isEmpty()){
            Uri videoUri = Uri.parse(videoUrl);
            initializePlayer(videoUri);
            mExoPlayerView.setVisibility(View.VISIBLE);
        }
        else
            mExoPlayerView.setVisibility(View.INVISIBLE);

    }

    public void initializePlayer(Uri uri)
    {
        if(mExoPlayer== null){
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
            mExoPlayerView.setPlayer(mExoPlayer);
            mExoPlayer.addListener(this);

        }
        // Prepare the MediaSource.
        String userAgent = Util.getUserAgent(this, "ClassicalMusicQuiz");
        MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(
                this, userAgent), new DefaultExtractorsFactory(), null, null);
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);

    }

    public void releasePlayer()
    {
        if(mExoPlayer != null){
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }

    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

}
