package recipe.kildare.com.recipeapp;

/**
 * Created by kilda on 4/8/2018.
 */

public class Food2ForkAPI {

    public static final String API_KEY = BuildConfig.FOOD_2_FORK_KEY;
    public static final String SEARCH_BASE_URL = "http://food2fork.com/api/search";
    public static final String REQUEST_BASE_URL = "http://food2fork.com/api/get";
    public static final String SORT_RATING = "r";
    public static final String SORT_TRENDING = "t";

    public static final String buildParameterIngredient(String[] ingredients)
    {
        if(ingredients == null || ingredients.length==0)
            return "";

        String query="";
        for(String ingredient : ingredients){
            query += ingredient + ",";
        }
        query = query.substring(0,query.length()-1);
        return query;
    }

    public static final String buildParameterKey()
    {
        return "key=" + API_KEY;
    }

    public static final String buildParameterSort(String sortType)
    {
        return "sort=" + sortType;
    }

    public static final String buildParameterRid(String rId)
    {
        return "rId=" + rId;
    }

    public static final String buildSearchQuery(String[] ingredients)
    {
        return SEARCH_BASE_URL + "?" + buildParameterKey() + "&" + buildParameterIngredient(ingredients) + "&" + buildParameterSort(SORT_RATING);
    }

    public static final String buildSearchQuery()
    {
        return SEARCH_BASE_URL + "?" + buildParameterKey() + "&" + buildParameterSort(SORT_RATING);
    }

    public static final String buildRequestQuery(String rId)
    {
        return REQUEST_BASE_URL + "?" + buildParameterKey() + "&" + buildParameterRid(rId);
    }
}
