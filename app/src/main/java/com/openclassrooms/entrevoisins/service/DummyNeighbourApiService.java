package com.openclassrooms.entrevoisins.service;

import android.support.annotation.NonNull;
import android.util.Log;

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

    @Override
    public List<Neighbour> getFavoritesNeighbours() {
        List<Neighbour> favoritesListNeighbours = new ArrayList<>();
        for (Neighbour neighbour : getNeighbours()) {
            if (neighbour.getFavorite())
                favoritesListNeighbours.add(neighbour);
        }
        return favoritesListNeighbours;
    }

    /**
     * {@inheritDoc}
     * Delete neighbour on all list
     * @param neighbour
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighboursList.remove(neighbour);
    }

    /**
     * Must remove a neighbour from favorites and not from de complete list
     * @param neighbour
     */
    //TODO : JAVA Array BoundException. Je pense qu'il est renvoyé parce qu'il ne prends pas la bonne liste.
    //TODO : Ne prends pas cette m pour supprimer...
    //TODO : Il devrait prendre la liste des favoris directement et non la liste des neighbour...??
    @Override
    public void changeStatusNeighbour(Neighbour neighbour) {
        //TODO dans la version du 3/3/20 à 15h20 mais ne s'ajoute pas à la liste des favoris et quand je sors de l'activité il n'est pas maintenu.
        if (neighbour.getFavorite() == false) {
            neighbour.setFavorite(true);
        }else
            neighbour.setFavorite(false);

        //TODO ci dessous ne fonctionne pas !!!! Il reste en false que je mette "!" ou pas
       // neighboursList.get(neighboursList.indexOf(neighbour)).setFavorite(neighbour.getFavorite());
        Log.e("changement statut", ""+neighbour.getName() + " devient " + neighbour.getFavorite());

    }
}
