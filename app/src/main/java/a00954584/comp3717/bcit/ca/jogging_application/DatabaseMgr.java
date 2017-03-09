package a00954584.comp3717.bcit.ca.jogging_application;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.gms.maps.model.LatLng;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Database.Bathroom;
import Database.BathroomDao;
import Database.Bench;
import Database.BenchDao;
import Database.DaoMaster;
import Database.DaoSession;
import Database.Fountain;
import Database.FountainDao;
import Database.Tree;
import Database.TreeDao;

/**
 * Created by Miss_Terra on 2017-02-12.
 */

public class DatabaseMgr {
    static DaoSession daoSession;
    static DaoMaster daoMaster;

    public static void init(Context context) {
        DaoMaster.DevOpenHelper masterHelper = new DaoMaster.DevOpenHelper(context, "Database", null); //create database db file if not exist
        SQLiteDatabase db = masterHelper.getWritableDatabase();  //get the created database db file
        daoMaster = new DaoMaster(db);//create masterDao
        daoSession = daoMaster.newSession(); //Creates Session session
        //clear evertything
        daoSession.getTreeDao().deleteAll();
        daoSession.getBenchDao().deleteAll();
        daoSession.getFountainDao().deleteAll();
        daoSession.getBathroomDao().deleteAll();
    }

    public static DaoSession getSession() {
        return daoSession;
    }
    public static TreeDao getTreeDao() {
        return daoSession.getTreeDao();
    }
    public static BenchDao getBenchDao() {
        return daoSession.getBenchDao();
    }
    public static FountainDao getFountainDao() {
        return daoSession.getFountainDao();
    }
    public static BathroomDao getBathroomDao() {
        return daoSession.getBathroomDao();
    }
    public static List<LatLng> getCoordsTree() {
        List<Tree> trees = getTreeDao().queryBuilder().orderAsc(TreeDao.Properties.Id).build().list();
        List<LatLng> treeCoords = new ArrayList<LatLng>();
        for (int i = 0; i < trees.size(); i++) {
            treeCoords.add(new LatLng(
                    trees.get(i).getLatitude(), trees.get(i).getLongitude()));
        }
        return treeCoords;
    }
    public static List<LatLng> getCoordsBench() {
        List<Bench> benches = getBenchDao().queryBuilder().orderAsc(BenchDao.Properties.Id).build().list();
        List<LatLng> benchCoords = new ArrayList<LatLng>();
        for (int i = 0; i < benches.size(); i++) {
            benchCoords.add(new LatLng(
                    benches.get(i).getLatitude(), benches.get(i).getLongitude()));
        }
        return benchCoords;
    }
    public static List<LatLng> getCoordsWashroom() {
        List<Bathroom> washrooms = getBathroomDao().queryBuilder().orderAsc(BathroomDao.Properties.Id).build().list();
        List<LatLng> bathroomCoords = new ArrayList<LatLng>();
        for (int i = 0; i < washrooms.size(); i++) {
            bathroomCoords.add(new LatLng(
                    washrooms.get(i).getLatitude(), washrooms.get(i).getLongitude()));
        }
        return bathroomCoords;
    }
    public static List<LatLng> getCoordsFountain() {
        List<Fountain> fountains = getFountainDao().queryBuilder().orderAsc(FountainDao.Properties.Id).build().list();
        List<LatLng> fountainCoords = new ArrayList<LatLng>();
        for (int i = 0; i < fountains.size(); i++) {
            fountainCoords.add(new LatLng(
                    fountains.get(i).getLatitude(), fountains.get(i).getLongitude()));
        }
        return fountainCoords;
    }

    public static void fillDatabase(InputStream trees, InputStream benches, InputStream washrooms, InputStream fountains) {
        BufferedReader reader = null;
        try {
            String mLine;
            List<Tree> treeList = new ArrayList<Tree>();
            List<Bench> benchList = new ArrayList<Bench>();
            List<Bathroom> washroomList = new ArrayList<Bathroom>();
            List<Fountain> fountainList = new ArrayList<Fountain>();


            reader = new BufferedReader(
                    new InputStreamReader(trees));
            while ((mLine = reader.readLine()) != null) {
                String s[] = mLine.split(",");
                treeList.add(new Tree(null, Double.parseDouble(s[0]), Double.parseDouble(s[1])));
            }
            getTreeDao().insertInTx(treeList);

            reader = new BufferedReader(
                    new InputStreamReader(benches));
            while ((mLine = reader.readLine()) != null) {
                String s[] = mLine.split(",");
                benchList.add(new Bench(null, Double.parseDouble(s[0]), Double.parseDouble(s[1])));
            }
            getBenchDao().insertInTx(benchList);

            reader = new BufferedReader(
                    new InputStreamReader(washrooms));
            while ((mLine = reader.readLine()) != null) {
                String s[] = mLine.split(",");
                washroomList.add(new Bathroom(null, Double.parseDouble(s[0]), Double.parseDouble(s[1])));
            }
            getBathroomDao().insertInTx(washroomList);

            reader = new BufferedReader(
                    new InputStreamReader(fountains));
            while ((mLine = reader.readLine()) != null) {
                String s[] = mLine.split(",");
                fountainList.add(new Fountain(null, Double.parseDouble(s[0]), Double.parseDouble(s[1])));
            }
            getFountainDao().insertInTx(fountainList);
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

    }
}
