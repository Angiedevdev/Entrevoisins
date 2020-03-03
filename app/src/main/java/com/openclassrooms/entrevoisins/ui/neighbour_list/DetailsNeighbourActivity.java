package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This apps is created by Angie, in 02 2020.
 * Entrevoisins. Developped since formation.
 */

public class DetailsNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.fab_favorits)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.button_arrow_backward)
    ImageView mArrowBackward;
    @BindView(R.id.photo_neighbour)
    ImageView mAvatarNeighbour;
    @BindView(R.id.textView_name_neighbour)
    TextView mNameNeighbour;
    @BindView(R.id.textView_name_neighbour2)
    TextView mNameNeighbour2;
    @BindView(R.id.textView_adress_neighbour)
    TextView mAdressNeighbour;
    @BindView(R.id.textView_phone_neighbour)
    TextView mPhoneNeighbour;
    @BindView(R.id.textView_url_neighbour)
    TextView mURLNeighbour;
    //@BindView(R.id.toolbar)
    //Toolbar mToolbar;

    private Neighbour mNeighbour;
    private NeighbourApiService mApiService;

    public static final String EXTRA_NEIGHBOUR = "Neighbour";

    public static String NEIGHBOUR_ON_FAVORITES = "Vous avez un nouveau voisin favori";
    public static String NEIGHBOUR_OUT_FAVORITES = "Oh, ce voisin n'est plus dans vos favoris";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);
        ButterKnife.bind(this);
        //setSupportActionBar(mToolbar);

        getApiService();
        getNeighbourParcelable();
        getDetailsNeighbour();
        changeStatusFavorites();
        returnHome();
    }

    private void returnHome(){
        mArrowBackward.setOnClickListener(view -> {
            this.finish();
        });
    }

    private void getNeighbourParcelable(){
        mNeighbour = getIntent().getParcelableExtra(EXTRA_NEIGHBOUR);
    }

    private NeighbourApiService getApiService(){
        mApiService = DI.getNeighbourApiService();
        return mApiService;
    }

    private void getDetailsNeighbour(){
        mNameNeighbour.setText(mNeighbour.getName());
        Glide.with(mAvatarNeighbour.getContext())
                .load(mNeighbour.getAvatarUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(mAvatarNeighbour);
        mNameNeighbour2.setText(mNeighbour.getName());
    }

    public void changeStatusFavorites(){
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mNeighbour.getFavorite()) {
                    removeOnFavorites();
                } else {
                    addOnFavorites();
                }
            }
        });
    }

    public void addOnFavorites(){
        //TODO : Notifier mon API Service
        mApiService.changeStatusNeighbour(mNeighbour);
        mFloatingActionButton.setImageResource(R.drawable.ic_star_yellow_24dp);
        toastMessage(NEIGHBOUR_ON_FAVORITES);
    }

    public void removeOnFavorites(){
        mApiService.changeStatusNeighbour(mNeighbour);
        mFloatingActionButton.setImageResource(R.drawable.ic_star_border_white_24dp);
        toastMessage(NEIGHBOUR_OUT_FAVORITES);
    }

    private void toastMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}