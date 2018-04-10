package recipe.kildare.com.recipeapp.Network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import recipe.kildare.com.recipeapp.Food2ForkAPI;

/**
 * Created by kilda on 4/9/2018.
 */

public class NetworkUtils {

    public static RequestQueue getRecipeListFromServer(Context context, Response.Listener<String> response, Response.ErrorListener errorListener)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Food2ForkAPI.buildSearchQuery();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response, errorListener);
        queue.add(stringRequest);
        return queue;
    }
}
