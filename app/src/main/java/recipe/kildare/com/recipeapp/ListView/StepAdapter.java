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
import recipe.kildare.com.recipeapp.Entities.Recipe;
import recipe.kildare.com.recipeapp.Entities.Step;
import recipe.kildare.com.recipeapp.LoadStepDetail;
import recipe.kildare.com.recipeapp.R;
import recipe.kildare.com.recipeapp.StepDetailActivity;

/**
 * Created by kilda on 4/14/2018.
 */

public class StepAdapter extends ArrayAdapter<Step> {

    LoadStepDetail mLoadStepDetail;
    Context mContext;
    List<Step> mSteps;
    public StepAdapter(@NonNull Context context, List<Step> steps, LoadStepDetail loadStepDetail) {
        super(context, 0, steps);
        mContext = context;
        mSteps = steps;
        mLoadStepDetail = loadStepDetail;
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
                mLoadStepDetail.loadStepDetail(position);
            }
        });

        return view;
    }
}
