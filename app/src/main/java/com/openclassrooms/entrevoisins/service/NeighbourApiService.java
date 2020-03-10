package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get just my favorits neighbours
     * @return {@link List}
     */

    List<Neighbour> getFavoritesNeighbours();

    /**
     * Deletes a neighbour
     * @return {@link DummyNeighbourApiService}
     */

    void deleteNeighbour(Neighbour neighbour);

    /**
     * Change status favorit of neighbour in neighbour not favorit
     * @return {@link DummyNeighbourApiService}
     */
    void changeStatusNeighbour(Neighbour neighbour);

}
