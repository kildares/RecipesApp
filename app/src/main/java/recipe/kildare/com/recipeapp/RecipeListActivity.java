package recipe.kildare.com.recipeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import recipe.kildare.com.recipeapp.Entities.Recipe;
import recipe.kildare.com.recipeapp.Network.NetworkUtils;
import recipe.kildare.com.recipeapp.RecyclerView.ParseRecipeData;
import recipe.kildare.com.recipeapp.RecyclerView.RecipeAsyncTask;
import recipe.kildare.com.recipeapp.RecyclerView.RecipeListRecyclerViewAdapter;

/**
 * An activity representing a list of Recipes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RecipeDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RecipeListActivity extends AppCompatActivity implements    Response.ErrorListener,
                                                                        Response.Listener<String>,
                                                                        ParseRecipeData
                                                                        {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */

    private static final String VOLLEY_TAG = "RECIPE_LIST_TAG";

    private RequestQueue mQueue;

    private boolean mTwoPane;

    private RecipeListRecyclerViewAdapter mAdapter;

    private RecipeAsyncTask mAsync;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recipe_list) RecyclerView mRecyclerView;
    @BindView(R.id.fl_recycler_view) FrameLayout mFrame;
    @BindView(R.id.tv_error) TextView mErrorMsg;
    @BindView(R.id.pb_loading) ProgressBar mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.recipe_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        loadData();
    }

    public void loadData()
    {
        if(mAdapter == null || mAdapter.getItemCount() <=0) {
            NetworkUtils.getRecipeListFromServer(this, this,this);
            mLoadingBar.setVisibility(View.VISIBLE);
            mErrorMsg.setVisibility(View.INVISIBLE);
        }
        else{
            mLoadingBar.setVisibility(View.VISIBLE);
            mErrorMsg.setVisibility(View.INVISIBLE);
        }
    }


    /**
     * Configures the recyclerView with data from the adapter. If none exists, will fetch the data online
     */
    private void setupRecyclerView(List<Recipe> data) {

        if(mAdapter != null)
            mAdapter.setRecipeData(data);
        else{
            mAdapter = new RecipeListRecyclerViewAdapter(this, data, mTwoPane);
            mRecyclerView.setAdapter(mAdapter);
        }

        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorMsg.setVisibility(View.INVISIBLE);
        mLoadingBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mQueue != null){
            mQueue.cancelAll(VOLLEY_TAG);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        showErrorMessage();
        mQueue.cancelAll(VOLLEY_TAG);
        mQueue = null;
    }

    /**
     * When the data is received, parse and insert the data in background
     * @param response
     */
    @Override
    public void onResponse(String response) {

        if(response!= null)
        {
            Log.d("RESPONSE",response);
            mAsync = new RecipeAsyncTask(this);
            mAsync.execute(response);
            if(mQueue!= null)
                mQueue.cancelAll(VOLLEY_TAG);
            mQueue = null;
        }
        else
            showErrorMessage();
    }

    @Override
    public void onParseRecipeDataResult(List<Recipe> recipes) {
        setupRecyclerView(recipes);
    }

    public void showErrorMessage()
    {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMsg.setVisibility(View.VISIBLE);
        mLoadingBar.setVisibility(View.INVISIBLE);
    }
}
