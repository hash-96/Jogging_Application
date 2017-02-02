package a00954584.comp3717.bcit.ca.jogging_application;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Created by Miss_Terra on 2017-01-31.
 */

public class MarkerList {

    ArrayList<MarkerOptions> arrList = new ArrayList<MarkerOptions>();
    GoogleMap gMap;

    MarkerList(Context context, GoogleMap gMap, int res, String markerName, BitmapDescriptor icon) {
        this.gMap = gMap;

        System.out.println("Running Yo");
        BufferedReader reader = null;
        try {

            System.out.println("Starting Yo");

            System.out.println("opened Yo");
            reader = new BufferedReader(
                        new InputStreamReader(context.getResources().openRawResource(res)));

            System.out.println("Dataset Open Yo");

            String mLine;
            int i = 0;
            while ((mLine = reader.readLine()) != null) {
                //process line
                String s[] = mLine.split(",");

                arrList.add(new MarkerOptions()
                        .position(new LatLng(
                                Double.parseDouble(s[0]), Double.parseDouble(s[1])))
                        .title("Tree")
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.tree)));
            }
        } catch (Exception e) {
            System.out.println("Error Loading File");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    System.out.println("Error Closing File");
                }
            }
        }
        for (int x = 0; x < arrList.size(); x++) {
            System.out.println();
            gMap.addMarker(arrList.get(x));
        }
    }
}
