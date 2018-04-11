package recipe.kildare.com.recipeapp;

import android.content.ContentValues;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import butterknife.BindView;
import butterknife.ButterKnife;
import recipe.kildare.com.recipeapp.Network.RecipeJSONUtils;
import recipe.kildare.com.recipeapp.RecyclerView.RecipeListRecyclerViewAdapter;
import recipe.kildare.com.recipeapp.persistence.RecipeDB;
import recipe.kildare.com.recipeapp.persistence.RecipeDataLoad;

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
        LoaderManager.LoaderCallbacks<Cursor>,RecipeDataLoad {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */

    private static final String VOLLEY_TAG = "RECIPE_LIST_TAG";

    private final int ID_RECIPE_LOADER = 77;

    private RequestQueue mQueue;

    private boolean mTwoPane;

    private RecipeListRecyclerViewAdapter mAdapter;

    private RecipeAsyncTask mAsync;

    @BindView(R2.id.toolbar) Toolbar toolbar;
    @BindView(R2.id.fab) FloatingActionButton fab;
    @BindView(R2.id.recipe_list) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.recipe_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        getSupportLoaderManager().initLoader(ID_RECIPE_LOADER, null, null);
    }

    /**
     * Configures the recyclerView with data from the adapter. If none exists, will fetch the data online
     */
    private void setupRecyclerView(Cursor data) {

        if(mAdapter == null)
            mAdapter.setRecipeData(data);
        else
            mAdapter = new RecipeListRecyclerViewAdapter(this, data, mTwoPane);
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
        ContentValues[] contentValues = RecipeJSONUtils.parseRecipeList(response);
        if(contentValues == null){
            showErrorMessage();
        }
        else
        {
            mAsync = new RecipeAsyncTask(this, this);
            mAsync.execute(contentValues);
            mQueue.cancelAll(VOLLEY_TAG);
            mQueue = null;
        }
    }



    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {

        switch(id){
            case ID_RECIPE_LOADER:{

                return new CursorLoader(    this,
                                                RecipeDB.buildQueryAllRecipes(),
                                                null,
                                                null,
                                                null,
                                                null);

            }
            default: throw new UnsupportedOperationException("UKNOWN LOADER ID: " + Integer.toString(id));
        }

    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        setupRecyclerView(data);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
        mAdapter.setRecipeData(null);
    }

    @Override
    public void onRecipeDataLoaded() {
        getSupportLoaderManager().restartLoader(ID_RECIPE_LOADER, null, null);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
