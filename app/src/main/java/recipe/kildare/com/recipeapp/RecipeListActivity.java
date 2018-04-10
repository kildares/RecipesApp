package recipe.kildare.com.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import recipe.kildare.com.recipeapp.Network.RecipeJSONUtils;
import recipe.kildare.com.recipeapp.Network.NetworkUtils;

/**
 * An activity representing a list of Recipes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RecipeDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RecipeListActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */

    private static final String VOLLEY_TAG = "RECIPE_LIST_TAG";

    private RequestQueue mQueue;

    private boolean mTwoPane;

    @BindView(R2.id.toolbar) Toolbar toolbar;
    @BindView(R2.id.fab) FloatingActionButton fab;

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

            View recyclerView = findViewById(R.id.recipe_list);
        assert recyclerView != null;

        setupRecyclerView((RecyclerView) recyclerView);
    }

    /**
     * Configures the recyclerView with data from the adapter. If none exists, will fetch the data online
     * @param recyclerView
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        if(recyclerView.getAdapter() == null){
            mQueue = NetworkUtils.getRecipeListFromServer(this, this, this);
            showLoadingBar();
        }

        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane));
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

    @Override
    public void onResponse(String response) {
        RecipeJSONUtils.parseRecipeList(response);
        mQueue.cancelAll(VOLLEY_TAG);
        mQueue = null;
    }
}
