package recipe.kildare.com.recipeapp.ListView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import recipe.kildare.com.recipeapp.Entities.Ingredient;
import recipe.kildare.com.recipeapp.Entities.Step;
import recipe.kildare.com.recipeapp.R;
import recipe.kildare.com.recipeapp.StepDetailActivity;

/**
 * Created by kilda on 4/14/2018.
 */

public class StepAdapter extends ArrayAdapter<Step> {

    List<Step> mSteps;
    Context mContext;

    private static final String LOG_STEP_ADAPTER = "LOG_STEP_ADAPTER";

    public StepAdapter(@NonNull Context context, List<Step> step) {
        super(context, 0, step);
        mContext = context;
        mSteps = step;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.step_details, parent, false);
        TextView name = view.findViewById(R.id.tv_step_short_description);
        name.setText(mSteps.get(position).getShortDescription());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_STEP_ADAPTER ,"CHANGING CONTEXT");
                Step step = mSteps.get(position);
                Intent intent = new Intent(mContext, StepDetailActivity.class);
                intent.putExtra(mContext.getString(R.string.key_step_data), step);
                mContext.startActivity(intent);
            }
        });

        return view;
    }
}
