package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.Utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nalfa on 17/4/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DataBaseHelper.class.getSimpleName();

    public DataBaseHelper(Context context) {

        super(context, DataBaseContract.DB_NAME, null, DataBaseContract.DB_VERSION);    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DataBaseContract.PictureTable.TABLE_NAME + " (" +
                DataBaseContract.PictureTable.PICTURE_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                DataBaseContract.PictureTable.CUSTOMERID + " TEXT NOT NULL," + // ES PARA LOS USUARIOS
                DataBaseContract.PictureTable.PICTURE_LINK + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE" + DataBaseContract.FolderTable.TABLE_NAME+ "(" +
        DataBaseContract.FolderTable.TABLE_NAME + "TEXT NOT NULL,"+
        DataBaseContract.FolderTable.URI_PATH+"TEXT NOT NULL");
        //pueden crearse  n tablas !!!
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseContract.PictureTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DataBaseContract.FolderTable.TABLE_NAME);
    }
}
