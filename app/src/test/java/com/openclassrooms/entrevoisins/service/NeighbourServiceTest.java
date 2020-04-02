package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;
    private Neighbour neighbour;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
        neighbour = service.getNeighbours().get(0);
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void getFavoritesNeighboursWithSuccess(){
        service.changeStatusNeighbour(neighbour);
        assertTrue(service.getFavoritesNeighbours().contains(neighbour));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        service.deleteNeighbour(neighbour);
        assertFalse(service.getNeighbours().contains(neighbour));
    }

    @Test
    public void addNeighbourToFavoriteList(){
        neighbour = service.getNeighbours().get(1);
        service.changeStatusNeighbour(neighbour);
        assertTrue(service.getFavoritesNeighbours().contains(neighbour));
    }

    @Test
    public void removeNeighbourToFavoriteList(){
        service.changeStatusNeighbour(neighbour);
        service.changeStatusNeighbour(neighbour);
        assertFalse(service.getFavoritesNeighbours().contains(neighbour));
    }
}