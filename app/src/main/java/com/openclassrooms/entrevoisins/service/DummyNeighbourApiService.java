package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighboursList = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighboursList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getFavoritesNeighbours() {
        List<Neighbour> favoritesListNeighbours = new ArrayList<>();
        for (Neighbour neighbour : getNeighbours()) {
            if (neighbour.isFavorite())
                favoritesListNeighbours.add(neighbour);
        }
        return favoritesListNeighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighboursList.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeStatusNeighbour(Neighbour neighbour) {
        if (neighbour.isFavorite()) {
            neighbour.setFavorite(false);
        } else {
            neighbour.setFavorite(true);
            neighboursList.get(neighboursList.indexOf(neighbour)).setFavorite(neighbour.isFavorite());
        }
    }
}
