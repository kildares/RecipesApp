package recipe.kildare.com.recipeapp.Network;

import android.content.Context;
import android.support.test.espresso.IdlingResource;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import recipe.kildare.com.recipeapp.utils.SimpleIdlingResource;

/**
 * Created by kilda on 4/9/2018.
 */

public class NetworkUtils {

    private static final String API_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    public static RequestQueue getRecipeListFromServer(Context context, Response.Listener<String> response, Response.ErrorListener errorListener, SimpleIdlingResource idlingResource)
    {

        if( idlingResource != null)
            idlingResource.setIdleState(false);


        RequestQueue queue = Volley.newRequestQueue(context);
        String url = API_URL;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response, errorListener);
        queue.add(stringRequest);
        return queue;
    }
}
