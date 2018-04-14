package recipe.kildare.com.recipeapp.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import recipe.kildare.com.recipeapp.Entities.Recipe;
import recipe.kildare.com.recipeapp.LoadRecipeOnFragment;
import recipe.kildare.com.recipeapp.R;
import recipe.kildare.com.recipeapp.RecipeListActivity;

/**
 * Created by kilda on 4/10/2018.
 */

public class RecipeListRecyclerViewAdapter extends RecyclerView.Adapter<RecipeListRecyclerViewAdapter.ViewHolder> {

        LoadRecipeOnFragment mLoadRecipeOnFragment;

        private final RecipeListActivity mParentActivity;
        private List<Recipe> mList;

        public RecipeListRecyclerViewAdapter(RecipeListActivity parent, List<Recipe> recipes, LoadRecipeOnFragment loadRecipeOnFragment) {
            mList = recipes;
            mParentActivity = parent;
            mLoadRecipeOnFragment = loadRecipeOnFragment;
        }

        public void setRecipeData(List<Recipe> recipes){
            mList = recipes;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recipe_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            Recipe recipe = mList.get(position);
            holder.mTitle.setText(recipe.getTitle());

            holder.itemView.setTag(recipe.getRecipe_ID());
            holder.itemView.setOnClickListener(view -> mLoadRecipeOnFragment.loadRecipeData(recipe));

        }

        @Override
        public int getItemCount() {
            if(mList == null)
                return 0;

            return mList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            //@BindView(R.id.tv_title)
            TextView mTitle;
            ViewHolder(View view) {
                super(view);
                mTitle = view.findViewById(R.id.tv_title);
            }
        }
}
