package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressMenuKey;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;

/** end-to-end test
 *Simulates user behavior only for favorites
 */
@RunWith(AndroidJUnit4.class)
public class FavoritesNeighboursTest {

    private static int ITEM_POSITION = 0;

    private ListNeighbourActivity mActivity;
    private NeighbourApiService mApiService;
    private List<Neighbour> getNeighbourList;
    private Neighbour mNeighbour;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mApiService = DI.getNewInstanceApiService();
        getNeighbourList = mApiService.getNeighbours();
        mNeighbour = getNeighbourList.get(ITEM_POSITION);
    }

    @Test
    public void userBehavior_managementFavoritesNeighbour() {
        onView(withText("MY NEIGHBOURS"))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(actionOnItemAtPosition(ITEM_POSITION, click()));

        onView(withId(R.id.fab_detail_activity_favorite))
                .perform(click());

        pressBack();

        onView(withText("MY NEIGHBOURS"))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(matches(isDisplayed()));

        onView(withText("FAVORITES"))
                .perform(click())
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.list_favorites_neighbours), isDisplayed()))
                .check(withItemCount(1))
                .perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.fab_detail_activity_favorite))
                .perform(click());

        pressBack();

        onView(withText("FAVORITES"))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.list_favorites_neighbours), isDisplayed()))
                .check(withItemCount(0));
    }
}