
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int ITEM_POSITION = 0;

    private ListNeighbourActivity mActivity;

    private NeighbourApiService mApiService;
    private List<Neighbour> getNeighbourList;
    private Neighbour neighbour;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mApiService = DI.getNewInstanceApiService();
        getNeighbourList = mApiService.getNeighbours();
        neighbour = getNeighbourList.get(ITEM_POSITION);
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {

        //TODO : Passe le 24/3 à 9h50

        // First scroll to the position that needs to be matched and click on it.

        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {

        //TODO : Passe le 24/3 à 9h50

        // Given : We remove the element at position 2
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void myNeighboursList_launchDetailActivity_whenClickOnItem(){
//TODO passe à 9h56
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(actionOnItemAtPosition(ITEM_POSITION, click()));
        onView(withId(R.id.toolbar_activity_detail))
                .check(matches(isDisplayed()));
    }

    @Test
    public void myNeighboursList_checkIf_TextViewIsRight(){
    //TODO : Passe le 24/3 à 9h50
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                       .perform(actionOnItemAtPosition(ITEM_POSITION, click()));
        onView(allOf(withId(R.id.textView_detail_activity_name_neighbour_title),isDisplayed()))
                       .check(matches(withText(neighbour.getName())));
    }

    @Ignore("En cours d'écriture")
    @Test
    public void myNeighboursList_checkSizeList_atClickOnButtonRemove(){
    // si je clic sur le bouton de suppression la liste des user compte bien
        // NON !! C'est bien déjà réalisé au dessus non ?? shouldremoveItem
    }

    @Test
    public void myNeighboursList_checkDisplay_fragmentFavorisIsEmptyWithNeighboursFavorites(){
  //TODO passe le 26/3 à 10h45

        onView(allOf(withContentDescription("Favorites"), isDisplayed()))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.list_favorites_neighbours)))
                .check(withItemCount(1));
    }
}