package a00954584.comp3717.bcit.ca.jogging_application;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Database.Bathroom;
import Database.Bench;
import Database.Fountain;
import Database.Tree;

/**
 * Created by Miss_Terra on 2017-02-12.
 */

public class DatabaseMgr {
    private Context context;

    public DatabaseMgr(Context c) {
        context = c;
    }

    public void init(InputStream trees, InputStream benches,
                     InputStream washrooms, InputStream fountains) {
        final DatabaseHelper helper;
        final long numBathrooms;
        final long numBenches;
        final long numFountains;
        final long numTrees;

        helper = DatabaseHelper.getInstance(context);
        helper.openDatabaseForWriting(context);

        numBathrooms = helper.getNumberOfBathrooms();
        numBenches = helper.getNumberOfBenches();
        numFountains = helper.getNumberOfFountains();
        numTrees = helper.getNumberOfTrees();

        if (numBathrooms == 0) {
            setBathrooms(helper, washrooms);
        }

        if (numBenches == 0) {
            setBenches(helper, benches);
        }

        if (numFountains == 0) {
            setFountains(helper, fountains);
        }

        if (numTrees == 0) {
            setTrees(helper, trees);
        }

        helper.close();
    }

    public void setBathrooms(DatabaseHelper helper, InputStream washrooms) {
        String mLine;
        BufferedReader reader = null;
        ArrayList<Bathroom> washroomList = new ArrayList<Bathroom>();

        try {
            reader = new BufferedReader(
                    new InputStreamReader(washrooms));
            while ((mLine = reader.readLine()) != null) {
                String s[] = mLine.split(",");
                helper.createBathroom(Double.parseDouble(s[0]), Double.parseDouble(s[1]));
            }

            reader.close();
        } catch (IOException ex) {
            //print error?
        }
    }

    public void setBenches(DatabaseHelper helper, InputStream benches) {
        String mLine;
        BufferedReader reader = null;
        ArrayList<Bench> benchList = new ArrayList<Bench>();

        try {
            reader = new BufferedReader(
                    new InputStreamReader(benches));
            while ((mLine = reader.readLine()) != null) {
                String s[] = mLine.split(",");
                helper.createBench(Double.parseDouble(s[0]), Double.parseDouble(s[1]));
            }

            reader.close();
        } catch (IOException ex) {
            //print error?
        }
    }

    public void setFountains(DatabaseHelper helper, InputStream fountains) {
        String mLine;
        BufferedReader reader = null;
        ArrayList<Fountain> fountainList = new ArrayList<Fountain>();

        try {
            reader = new BufferedReader(
                    new InputStreamReader(fountains));
            while ((mLine = reader.readLine()) != null) {
                String s[] = mLine.split(",");
                helper.createFountain(Double.parseDouble(s[0]), Double.parseDouble(s[1]));
            }

            reader.close();
        } catch (IOException ex) {
            //print error?
        }
    }

    public void setTrees(DatabaseHelper helper, InputStream trees) {
        String mLine;
        BufferedReader reader = null;
        ArrayList<Tree> treeList = new ArrayList<Tree>();

        try {
            reader = new BufferedReader(
                    new InputStreamReader(trees));
            while ((mLine = reader.readLine()) != null) {
                String s[] = mLine.split(",");
                helper.createTree(Double.parseDouble(s[0]), Double.parseDouble(s[1]));
            }

            reader.close();
        } catch (IOException ex) {
            //print error?
        }
    }

    public List<LatLng> getCoordsTree() {
        DatabaseHelper helper;
        List<Tree> trees;
        List<LatLng> treeCoords;

        helper = DatabaseHelper.getInstance(context);
        helper.openDatabaseForWriting(context);

        trees = helper.getTrees();
        treeCoords = new ArrayList<LatLng>();

        for (int i = 0; i < trees.size(); i++) {
            treeCoords.add(new LatLng(
                    trees.get(i).getLatitude(), trees.get(i).getLongitude()));
        }
        return treeCoords;
    }

    public List<LatLng> getCoordsBench() {
        DatabaseHelper helper;
        List<Bench> benches;
        List<LatLng> benchCoords;

        helper = DatabaseHelper.getInstance(context);
        helper.openDatabaseForWriting(context);

        benches = helper.getBenches();
        benchCoords = new ArrayList<LatLng>();

        for (int i = 0; i < benches.size(); i++) {
            benchCoords.add(new LatLng(
                    benches.get(i).getLatitude(), benches.get(i).getLongitude()));
        }
        return benchCoords;
    }

    public List<LatLng> getCoordsWashroom() {
        DatabaseHelper helper;
        List<Bathroom> bathrooms;
        List<LatLng> bathroomCoords;

        helper = DatabaseHelper.getInstance(context);
        helper.openDatabaseForWriting(context);

        bathrooms = helper.getBathrooms();
        bathroomCoords = new ArrayList<LatLng>();

        for (int i = 0; i < bathrooms.size(); i++) {
            bathroomCoords.add(new LatLng(
                    bathrooms.get(i).getLatitude(), bathrooms.get(i).getLongitude()));
        }
        return bathroomCoords;
    }

    public List<LatLng> getCoordsFountain() {
        DatabaseHelper helper;
        List<Fountain> fountains;
        List<LatLng> fountainCoords;

        helper = DatabaseHelper.getInstance(context);
        helper.openDatabaseForWriting(context);

        fountains = helper.getFountains();
        fountainCoords = new ArrayList<LatLng>();

        for (int i = 0; i < fountains.size(); i++) {
            fountainCoords.add(new LatLng(
                    fountains.get(i).getLatitude(), fountains.get(i).getLongitude()));
        }
        return fountainCoords;
    }
}
