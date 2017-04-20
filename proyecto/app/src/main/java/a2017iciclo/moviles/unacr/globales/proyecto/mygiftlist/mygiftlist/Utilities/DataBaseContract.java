package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.Utilities;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by nalfa on 17/4/2017.
 */

public class DataBaseContract {
    public static final String AUTHORITY = "a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.Utilities";
    public static final String DB_NAME = "gifts.db";
    public static final int DB_VERSION = 1;

    public interface PictureColums {
        String PICTURE_ID = BaseColumns._ID;
        String CUSTOMERID = "customerId";
        String PICTURE_LINK = "picture_link";
    }

    public static final class PictureTable implements PictureColums {
        public static final String TABLE_NAME = "Pictures";
        public static final String URI_PATH = "Pictures";
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + URI_PATH);
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + URI_PATH;

        private PictureTable() {
        }
    }
    public interface FolderColummn {
        String Name = BaseColumns._ID;

    }

    public static final class FolderTable implements FolderColummn {
        public static final String TABLE_NAME = "Folder";
        public static final String URI_PATH = "Folder";
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + URI_PATH);
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + URI_PATH;

        private FolderTable() {
        }
    }
}
