package a00954584.comp3717.bcit.ca.jogging_application;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Taryn on 3/19/2017.
 */
public class JoggingContentProvider
        extends android.content.ContentProvider
{
    private static final UriMatcher uriMatcher;
    private static final int BATHROOMS_URI = 1;
    public static final int BENCHES_URI = 2;
    public static final int FOUNTAINS_URI = 3;
    public static final int TREES_URI = 4;
    public static final Uri BATH_CONTENT_URI;
    public static final Uri BEN_CONTENT_URI;
    public static final Uri FOUNT_CONTENT_URI;
    public static final Uri TREE_CONTENT_URI;
    private DatabaseHelper helper;

    static
    {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("Database", "bathrooms", BATHROOMS_URI);
        uriMatcher.addURI("Database", "benches", BENCHES_URI);
        uriMatcher.addURI("Database", "fountains", FOUNTAINS_URI);
        uriMatcher.addURI("Database", "trees", TREES_URI);
    }

    static
    {
        BATH_CONTENT_URI = Uri.parse("content://Database/bathrooms");
        BEN_CONTENT_URI = Uri.parse("content://Database/benches");
        FOUNT_CONTENT_URI = Uri.parse("content://Database/fountains");
        TREE_CONTENT_URI = Uri.parse("content://Database/trees");
    }

    @Override
    public boolean onCreate()
    {
        helper = DatabaseHelper.getInstance(getContext());

        return true;
    }

    @Override
    public Cursor query(final Uri uri,
                        final String[] projection,
                        final String selection,
                        final String[] selectionArgs,
                        final String sortOrder)
    {
        final Cursor cursor;

        switch (uriMatcher.match(uri))
        {
            case BATHROOMS_URI:
            {
                final SQLiteDatabase db;

                helper.openDatabaseForReading(getContext());
                cursor = helper.getBathroomCursor();
                helper.close();
                break;
            }
            case BENCHES_URI:
            {
                final SQLiteDatabase db;

                helper.openDatabaseForReading(getContext());
                cursor = helper.getBenchCursor();
                helper.close();
                break;
            }
            case FOUNTAINS_URI:
            {
                final SQLiteDatabase db;

                helper.openDatabaseForReading(getContext());
                cursor = helper.getFountainCursor();
                helper.close();
                break;
            }
            case TREES_URI:
            {
                final SQLiteDatabase db;

                helper.openDatabaseForReading(getContext());
                cursor = helper.getTreeCursor();
                helper.close();
                break;
            }
            default:
            {
                throw new IllegalArgumentException("Unsupported URI: " + uri);
            }
        }

        return (cursor);
    }

    @Override
    public String getType(final Uri uri)
    {
        final String type;

        switch(uriMatcher.match(uri))
        {
            case BATHROOMS_URI: {
                type = "vnd.android.cursor.dir/vnd.Database.bathrooms";
                break;
            }
            case BENCHES_URI: {
                type = "vnd.android.cursor.dir/vnd.Database.benches";
                break;
            }
            case FOUNTAINS_URI: {
                type = "vnd.android.cursor.dir/vnd.Database.fountains";
                break;
            }
            case TREES_URI: {
                type = "vnd.android.cursor.dir/vnd.Database.trees";
                break;
            }
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        return (type);
    }

    @Override
    public int delete(final Uri uri,
                      final String selection,
                      final String[] selectionArgs)
    {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(final Uri uri,
                      final ContentValues values)
    {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(final Uri uri,
                      final ContentValues values,
                      final String selection,
                      final String[]      selectionArgs)
    {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
