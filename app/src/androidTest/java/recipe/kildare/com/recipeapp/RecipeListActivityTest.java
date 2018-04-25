package recipe.kildare.com.recipeapp;

import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import recipe.kildare.com.recipeapp.recipeDetails.StepDetailActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by kilda on 4/24/2018.
 */

@RunWith(AndroidJUnit4.class)
public class RecipeListActivityTest {

    public static final String POSITION_1_TEXT = "Nutella Pie";

    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<StepDetailActivity> mRecipeListTestRule = new ActivityTestRule<>(StepDetailActivity.class);

    @Test
    public void clickGridViewItem_OpensRecipeDetailActivity()
    {

        onView(withId(R.id.bt_prev_step)).check(matches(withText("Previous Step")));
        onView(withId(R.id.bt_next_step)).check(matches(withText("Next Step")));

    }

}
