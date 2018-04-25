package recipe.kildare.com.recipeapp.recipeDetails;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import recipe.kildare.com.recipeapp.Entities.Step;
import recipe.kildare.com.recipeapp.R;
import recipe.kildare.com.recipeapp.RecipeListActivity;

/**
 * A fragment representing a single Recipe detail screen.
 * This fragment is either contained in a {@link RecipeListActivity}
 * in two-pane mode (on tablets) or a {@link RecipeDetailActivity}
 * on handsets.
 */
public class StepDetailFragment extends Fragment implements ExoPlayer.EventListener{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    private Step mStep;

    private SimpleExoPlayer mPlayer;
    private SimpleExoPlayerView mPlayerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StepDetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mStep = savedInstanceState.getParcelable(getActivity().getString(R.string.key_recipe_data));
        }

        View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);

        TextView textView = rootView.findViewById(R.id.tv_step_full_description);

        textView.setText(mStep.getDescription());

        mPlayerView = rootView.findViewById(R.id.exo_step_detail);

        String videoUrl = mStep.getVideoURL();
        if(videoUrl != null && !videoUrl.isEmpty()){
            Uri uri = Uri.parse(videoUrl);
            initializePlayer(uri);
        }
        else{

            mPlayerView.setVisibility(View.INVISIBLE);
        }
        return rootView;
    }

    public void initializePlayer(Uri uri)
    {
        if(mPlayer == null){
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mPlayer);
            mPlayer.addListener(this);
            mPlayerView.setVisibility(View.VISIBLE);
            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getContext(), "recipeApp");
            MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mPlayer.prepare(mediaSource);
            mPlayer.setPlayWhenReady(true);

        }
    }

    public void setStep(Step step)
    {
        mStep = step;
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


    public void releasePlayer()
    {
        if(mPlayer!= null){
            mPlayer.stop();
            mPlayer.release();
            mPlayer= null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(getActivity().getString(R.string.key_recipe_data), mStep);
        super.onSaveInstanceState(outState);
    }
}
