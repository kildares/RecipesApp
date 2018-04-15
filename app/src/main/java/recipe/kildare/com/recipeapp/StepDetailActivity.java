package recipe.kildare.com.recipeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import recipe.kildare.com.recipeapp.Entities.Step;

public class StepDetailActivity extends AppCompatActivity {

    @BindView(R.id.exo_step_detail)
     SimpleExoPlayerView mExoPlayerView;

    @BindView(R.id.tv_step_full_description)
     TextView mFullStepDescription;

    private Step mStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        ButterKnife.bind(this);
        mStep = getIntent().getExtras().getParcelable(getString(R.string.key_step_data));



        mFullStepDescription.setText(mStep.getDescription());
    }
}
