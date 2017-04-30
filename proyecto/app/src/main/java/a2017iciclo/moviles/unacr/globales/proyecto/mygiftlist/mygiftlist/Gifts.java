package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.TableLayout;

import java.util.ArrayList;

import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.DaoMaster;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.DaoSession;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.GiftDB;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.GiftDBDao;

/**
 * Created by jdani on 3/4/2017.
 */

public class Gifts {

    ArrayList<Gift> gifts;

    public Gifts() {
        this.gifts = new ArrayList<Gift>();
    }

}

