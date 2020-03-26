package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.fab_detail_activity_favorite)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.button_arrow_backward)
    ImageView mArrowBackward;
    @BindView(R.id.image_view_activity_detail_photo_neighbour)
    ImageView mAvatarNeighbour;
    @BindView(R.id.textView_detail_activity_name_neighbour_title)
    TextView mNameNeighbour;
    @BindView(R.id.textView_detail_activity_name_neighbour_card)
    TextView mNameNeighbour2;
    @BindView(R.id.textView_adress_neighbour_detail_activity_card_view)
    TextView mAdressNeighbour;
    @BindView(R.id.textView_phone_neighbour_detail_activity_card_view)
    TextView mPhoneNeighbour;
    @BindView(R.id.textView_url_neighbour_detail_activity_card_view)
    TextView mURLNeighbour;

    private Neighbour mNeighbour;
    private NeighbourApiService mApiService;

    public static final String EXTRA_NEIGHBOUR = "Neighbour";

    private static final String NEIGHBOUR_IN_FAVORITES = "Vous avez un nouveau voisin favori";
    private static final String NEIGHBOUR_OUT_FAVORITES = "Oh, ce voisin n'est plus dans vos favoris";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);
        ButterKnife.bind(this);

        configureNeighbourDetail();
        changeStatusFavoritesListener();
        returnHome();
    }

    /**
     * Getting all elements for details of neighbour. This method was created for cleaning the method onCreate.
     */
    private void configureNeighbourDetail(){
        getApiService();
        getNeighbourParcelable();
        getDetailsNeighbour();
    }

    /**
     * When user click on star, neighbour's status changes.
     */

    public void changeStatusFavoritesListener(){
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mNeighbour.isFavorite()) {
                    removeInFavorites();
                } else {
                    addInFavorites();
                }
            }
        });
    }

    /**
     * When user click on this arrow, he returns to the previous activity.
     */
    private void returnHome(){
        mArrowBackward.setOnClickListener(view -> {
            this.finish();
        });
    }

    /**
     * Specific method to add or remove in favorite
     */

    public void addInFavorites(){
        mApiService.changeStatusNeighbour(mNeighbour);
        setFloatingActionButton();
        toastMessage(NEIGHBOUR_IN_FAVORITES);
    }

    public void removeInFavorites(){
        mApiService.changeStatusNeighbour(mNeighbour);
        setFloatingActionButton();
        toastMessage(NEIGHBOUR_OUT_FAVORITES);
    }

    /**
     * Method to get neighbour's elements.
     */
    private NeighbourApiService getApiService(){
        mApiService = DI.getNeighbourApiService();
        return mApiService;
    }

    private void getNeighbourParcelable(){
        mNeighbour = getIntent().getParcelableExtra(EXTRA_NEIGHBOUR);
    }

    private void getDetailsNeighbour(){
        mNameNeighbour.setText(mNeighbour.getName());
        Glide.with(mAvatarNeighbour.getContext())
                .load(mNeighbour.getAvatarUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(mAvatarNeighbour);
        mNameNeighbour2.setText(mNeighbour.getName());
        setFloatingActionButton();
    }

    /**
     * Other tools
     */
    private void setFloatingActionButton(){
        if(mNeighbour.isFavorite()) {
            mFloatingActionButton.setImageResource(R.drawable.ic_star_yellow_24dp);
        } else {
            mFloatingActionButton.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }

    private void toastMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}