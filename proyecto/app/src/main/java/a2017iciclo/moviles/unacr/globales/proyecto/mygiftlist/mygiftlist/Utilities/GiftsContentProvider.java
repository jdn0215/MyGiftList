package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.Utilities;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by nalfa on 17/4/2017.
 */

public class GiftsContentProvider extends ContentProvider {

    private DataBaseHelper dh;
    private static final int PICTURES = 1;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{
        uriMatcher.addURI(DataBaseContract.AUTHORITY, DataBaseContract.PictureTable.URI_PATH, PICTURES);
    }

    @Override
    public boolean onCreate() {
        dh = new DataBaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
      //ESTE METODO NO SE QUE PIENSAN HACER !!!
        Cursor c = null;
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setDistinct(true);
        switch (uriMatcher.match(uri)) {
            case PICTURES:
                qb.setTables(DataBaseContract.PictureTable.TABLE_NAME);
                if (sortOrder == null)
                    sortOrder = DataBaseContract.PictureTable.CUSTOMERID + " ASC";
                c = qb.query(dh.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (values != null) {
            switch (uriMatcher.match(uri)) {
                case PICTURES:
                    return doInsert(uri, DataBaseContract.PictureTable.TABLE_NAME, DataBaseContract.PictureTable.CONTENT_URI, values);
                   // n cantidad de inserts
                default:
                    throw new IllegalArgumentException("Unknown URI " + uri);
            }

        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dh.getWritableDatabase();
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case PICTURES:
                count = db.delete(DataBaseContract.PictureTable.TABLE_NAME, selection, selectionArgs);
                break;
        }
        if (getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dh.getWritableDatabase();
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case PICTURES:
                count = db.update(DataBaseContract.PictureTable.TABLE_NAME, values, selection, selectionArgs);
                break;
        }
        if (getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;

    }

    private Uri doInsert(Uri uri, String tableName, Uri contentUri, ContentValues contentValues) {
        SQLiteDatabase db = dh.getWritableDatabase();
        long rowId = db.insert(tableName, null, contentValues);
        if (rowId > 0) {
            Uri insertedUri = ContentUris.withAppendedId(contentUri, rowId);
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(insertedUri, null);
            }
            return insertedUri;
        }
        throw new SQLException("Failed to insert row - " + uri);
    }
}

