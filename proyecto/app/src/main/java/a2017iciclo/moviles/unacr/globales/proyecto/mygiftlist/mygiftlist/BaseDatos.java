package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.DaoMaster;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.DaoSession;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.GiftDB;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.GiftDBDao;

/**
 * Created by Luis on 30/4/2017.
 */

public class BaseDatos extends AppCompatActivity {
    GiftDBDao gift_dao;

    public BaseDatos(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        init();
    }

    final void init(){
        DaoMaster.DevOpenHelper masterHelper = new DaoMaster.DevOpenHelper(this, "GIFTS_DB",null); //create database db file if not exist
        SQLiteDatabase db = masterHelper.getWritableDatabase();  //get the created database db file
        DaoMaster master = new DaoMaster(db);//create masterDao
        DaoSession masterSession=master.newSession(); //Creates Session session
        gift_dao=masterSession.getGiftDBDao();
    }


    public void saveToSQL(GiftDB gift_object) {
        gift_dao.insert(gift_object);
    }

    public List<Gift> BuscarPorFolder(String namefolder){
        List<GiftDB> lista= gift_dao.queryBuilder().where(GiftDBDao.Properties.Folder.eq(namefolder)).list();
        List<Gift> filtrados =new ArrayList();
        for(GiftDB r:lista) {
            filtrados.add(r.toGiftNormal());
        }
        return filtrados;
    }

    public List<Gift> BuscarPorNombre(String nombre){
        List<GiftDB> lista= gift_dao.queryBuilder().where(GiftDBDao.Properties.Nombre.like(nombre)).list();
        List<Gift> filtrados =new ArrayList();
        for(GiftDB r:lista) {
            filtrados.add(r.toGiftNormal());
        }
        return filtrados;
    }

    public int sizeLista(){
     //   gift_dao.insert(new GiftDB(null,""+4,"descr","nomb","folder",4033,434.0,3434.0));
        return gift_dao.queryBuilder().list().size();
    }
}
