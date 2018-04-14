package recipe.kildare.com.recipeapp.ListView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import recipe.kildare.com.recipeapp.Entities.Ingredient;

/**
 * Created by kilda on 4/14/2018.
 */

public class IngredientAdapter extends ArrayAdapter<Ingredient> {

    List<Ingredient> mIngredients;
    Context mContext;

    public IngredientAdapter(@NonNull Context context, ArrayList<Ingredient> list) {
        super(context, 0, list);
        mContext = context;
        mIngredients = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(R.id.ingredient_details, parent, false);

        TextView name = view.findViewById(R.id.tv_ingredient_title);
        TextView measure = view.findViewsWithText(R.id.tv_ingredient_measure);
        TextView quantity = view.findViewsWithText(R.id.tv_ingredient_quantity);

        name.setText(mIngredients.get(position).getName());
        measure.setText(mIngredients.get(position).getMeasure());
        quantity.setText(mIngredients.get(position).getQuantity());

        return view;
    }


}
