package a00954584.comp3717.bcit.ca.jogging_application;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Miss_Terra on 2017-01-31.
 */

public class genMarkers {

    ArrayList<MarkerOptions> arrList = new ArrayList<MarkerOptions>();
    genMarkers(GoogleMap gMap, String markerName, int icon, List<LatLng> coords) {
        for (int x = 0; x < coords.size(); x++) {
            gMap.addMarker(new MarkerOptions()
                    .position(coords.get(x))
                    .title(markerName)
                    .icon(BitmapDescriptorFactory.fromResource(icon)));
        }
    }
}
