package com.openclassrooms.entrevoisins.service;

import android.support.design.snackbar.ContentViewCallback;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void changeStatusOfNeighbour(){
        Neighbour neighbour = service.getFavoritesNeighbours().get(0);
        service.changeStatusNeighbour(neighbour);
        assertFalse(neighbour.isFavorite());
    }

    @Test
    public void addNeighbourToFavoriteList(){
        Neighbour neighbour = service.getNeighbours().get(0);
        List<Neighbour> neighbourFavoritesList = service.getFavoritesNeighbours();
        neighbourFavoritesList.add(neighbour);
        assertThat(neighbourFavoritesList.size(), is(1));
    }

    @Test
    public void removeNeighbourToFavoriteList(){
        Neighbour neighbour = service.getNeighbours().get(0);
        List<Neighbour> neighbourFavoritesList = service.getFavoritesNeighbours();
        neighbourFavoritesList.remove(neighbour);
        assertThat(neighbourFavoritesList.size(), is(1));
    }
}
