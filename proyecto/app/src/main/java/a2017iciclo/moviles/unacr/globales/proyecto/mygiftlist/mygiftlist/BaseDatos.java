package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.DaoMaster;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.DaoSession;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.GiftDB;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.GiftDBDao;

/**
 * Created by Luis on 30/4/2017.
 */

public class BaseDatos {
    Context contexto;
    GiftDBDao gift_dao;

    public BaseDatos(Context context){
        contexto=context;
        gift_dao=setupDB();
    }

    public GiftDBDao setupDB(){
        DaoMaster.DevOpenHelper masterHelper = new DaoMaster.DevOpenHelper(contexto, "GIFTS_DB", null); //create database db file if not exist
        SQLiteDatabase db = masterHelper.getWritableDatabase();  //get the created database db file
        DaoMaster master = new DaoMaster(db);//create masterDao
        DaoSession masterSession=master.newSession(); //Creates Session session
        return masterSession.getGiftDBDao();
    }
    public void saveToSQL(GiftDB gift_object) {
        gift_dao.insert(gift_object);
    }

    public List<GiftDB> BuscarPorFolder(String folder){
        List<GiftDB> lista= gift_dao.loadAll();
        List<GiftDB> filtrados=new ArrayList<GiftDB>();
        for(GiftDB gf:lista){
            if(gf.getFolder()==folder)
                filtrados.add(gf);
        }
        return filtrados;
    }

    public List<GiftDB> BuscarPorNombre(String nombre){
        List<GiftDB> lista= gift_dao.loadAll();
        List<GiftDB> filtrados=new ArrayList<GiftDB>();
        for(GiftDB gf:lista){
            if(gf.getNombre().startsWith(nombre))
                filtrados.add(gf);
        }
        return filtrados;
    }

}
