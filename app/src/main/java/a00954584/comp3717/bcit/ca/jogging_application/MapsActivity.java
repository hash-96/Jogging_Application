package a00954584.comp3717.bcit.ca.jogging_application;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.androidmapsextensions.ClusteringSettings;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static a00954584.comp3717.bcit.ca.jogging_application.R.id.map;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Burnaby and move the camera
        LatLng van = new LatLng(49.1780501,-122.9526167);


       // mMap.setClustering(new ClusteringSettings().enabled(true)
         //       .addMarkersDynamically(true).minMarkersCount(5));

        mMap.addMarker(new MarkerOptions().position(van).title("You are Here"));

        //setMinMaxZooms for Camera - Terra
        mMap.setMinZoomPreference(17.0f);
        mMap.setMaxZoomPreference(19.0f);

        mMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(van, 17.0f));

        //Populate Tree markers - Terra
        new MarkerList(getBaseContext(), mMap, R.raw.trees, "Tree", BitmapDescriptorFactory.fromResource(R.mipmap.tree));

    }
}
