package a00954584.comp3717.bcit.ca.jogging_application;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

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
 * Created by Taryn on 3/19/2017.
 */
public class DatabaseHelper
{
    private final String DB_NAME ="jogging_app.db";
    private final static String TAG = DatabaseHelper.class.getName();
    private static DatabaseHelper instance;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private BathroomDao bathroomDao;
    private BenchDao benchDao;
    private FountainDao fountainDao;
    private TreeDao treeDao;
    private DaoMaster.DevOpenHelper helper;

    private DatabaseHelper(final Context context)
    {
        openDatabaseForWriting(context);
    }

    public synchronized static DatabaseHelper getInstance(final Context context)
    {
        if(instance == null)
        {
            instance = new DatabaseHelper(context);
        }

        return (instance);
    }

    public static DatabaseHelper getInstance()
    {
        if(instance == null)
        {
            throw new Error();
        }

        return (instance);
    }

    private void openDatabase()
    {
        daoMaster  = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        bathroomDao = daoSession.getBathroomDao();
        benchDao = daoSession.getBenchDao();
        fountainDao = daoSession.getFountainDao();
        treeDao = daoSession.getTreeDao();
    }

    public void openDatabaseForWriting(final Context context)
    {
        helper = new DaoMaster.DevOpenHelper(context,
                DB_NAME,
                null);
        db = helper.getWritableDatabase();
        openDatabase();
    }

    public void openDatabaseForReading(final Context context)
    {
        final DaoMaster.DevOpenHelper helper;

        helper = new DaoMaster.DevOpenHelper(context,
                DB_NAME,
                null);
        db = helper.getReadableDatabase();
        openDatabase();
    }

    public void close()
    {
        helper.close();
    }

    public Bathroom createBathroom(final double latitude, final double longitude)
    {
        final Bathroom bathroom;

        bathroom = new Bathroom(null, latitude,
                longitude);
        bathroomDao.insert(bathroom);

        return (bathroom);
    }

    public Bench createBench(final double latitude, final double longitude)
    {
        final Bench bench;

        bench = new Bench(null, latitude,
                longitude);
        benchDao.insert(bench);

        return (bench);
    }

    public Fountain createFountain(final double latitude, final double longitude)
    {
        final Fountain fountain;

        fountain = new Fountain(null, latitude,
                longitude);
        fountainDao.insert(fountain);

        return (fountain);
    }

    public Tree createTree(final double latitude, final double longitude)
    {
        final Tree tree;

        tree = new Tree(null, latitude,
                longitude);
        treeDao.insert(tree);

        return (tree);
    }


    public Bathroom getBathroomFromCursor(final Cursor cursor)
    {
        final Bathroom bathroom;

        bathroom = bathroomDao.readEntity(cursor,
                0);

        return (bathroom);
    }

    public Bench getBenchFromCursor(final Cursor cursor)
    {
        final Bench bench;

        bench = benchDao.readEntity(cursor,
                0);

        return (bench);
    }

    public Fountain getFountainFromCursor(final Cursor cursor)
    {
        final Fountain fountain;

        fountain = fountainDao.readEntity(cursor,
                0);

        return (fountain);
    }

    public Tree getTreeFromCursor(final Cursor cursor)
    {
        final Tree tree;

        tree = treeDao.readEntity(cursor,
                0);

        return (tree);
    }

    public Bathroom getBathroomByObjectId(final long id)
    {
        final List<Bathroom> bathrooms;
        final Bathroom bathroom;

        bathrooms = bathroomDao.queryBuilder().where(BathroomDao.Properties.Id.eq(id)).limit(1).list();

        if(bathrooms.isEmpty())
        {
            bathroom = null;
        }
        else
        {
            bathroom = bathrooms.get(0);
        }

        return (bathroom);
    }

    public Bench getBenchByObjectId(final long id)
    {
        final List<Bench> benches;
        final Bench bench;

        benches = benchDao.queryBuilder().where(BenchDao.Properties.Id.eq(id)).limit(1).list();

        if(benches.isEmpty())
        {
            bench = null;
        }
        else
        {
            bench = benches.get(0);
        }

        return (bench);
    }

    public Fountain getFountainByObjectId(final long id)
    {
        final List<Fountain> fountains;
        final Fountain fountain;

        fountains = fountainDao.queryBuilder().where(FountainDao.Properties.Id.eq(id)).limit(1).list();

        if(fountains.isEmpty())
        {
            fountain = null;
        }
        else
        {
            fountain = fountains.get(0);
        }

        return (fountain);
    }

    public Tree getTreeByObjectId(final long id)
    {
        final List<Tree> trees;
        final Tree tree;

        trees = treeDao.queryBuilder().where(TreeDao.Properties.Id.eq(id)).limit(1).list();

        if(trees.isEmpty())
        {
            tree = null;
        }
        else
        {
            tree = trees.get(0);
        }

        return (tree);
    }

    public List<Bathroom> getBathrooms()
    {
        return (bathroomDao.loadAll());
    }

    public List<Bench> getBenches()
    {
        return (benchDao.loadAll());
    }

    public List<Fountain> getFountains()
    {
        return (fountainDao.loadAll());
    }

    public List<Tree> getTrees()
    {
        return (treeDao.loadAll());
    }

    public Cursor getBathroomCursor()
    {
        final Cursor cursor;

        String orderBy = BathroomDao.Properties.Id.columnName + " ASC";
        cursor = db.query(bathroomDao.getTablename(),
                bathroomDao.getAllColumns(),
                null,
                null,
                null,
                null,
                orderBy);

        return (cursor);
    }

    public Cursor getBenchCursor()
    {
        final Cursor cursor;

        String orderBy = BenchDao.Properties.Id.columnName + " ASC";
        cursor = db.query(benchDao.getTablename(),
                benchDao.getAllColumns(),
                null,
                null,
                null,
                null,
                orderBy);

        return (cursor);
    }

    public Cursor getFountainCursor()
    {
        final Cursor cursor;

        String orderBy = FountainDao.Properties.Id.columnName + " ASC";
        cursor = db.query(fountainDao.getTablename(),
                fountainDao.getAllColumns(),
                null,
                null,
                null,
                null,
                orderBy);

        return (cursor);
    }

    public Cursor getTreeCursor()
    {
        final Cursor cursor;

        String orderBy = TreeDao.Properties.Id.columnName + " ASC";
        cursor = db.query(treeDao.getTablename(),
                treeDao.getAllColumns(),
                null,
                null,
                null,
                null,
                orderBy);

        return (cursor);
    }

    public static void upgrade(final Database db,
                               final int      oldVersion,
                               final int      newVersion)
    {
    }

    public long getNumberOfBathrooms()
    {
        return (bathroomDao.count());
    }

    public long getNumberOfBenches()
    {
        return (benchDao.count());
    }

    public long getNumberOfFountains()
    {
        return (fountainDao.count());
    }

    public long getNumberOfTrees()
    {
        return (treeDao.count());
    }
}