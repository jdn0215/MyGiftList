package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jdani on 2/4/2017.
 */

///para manejar de forma rápida las carpetas de imagenes de la app
final public class Folder extends ArrayList<String> {

    static String s_S_F_preferenceName="mgl_folders";
    static String s_S_F_size=Folder.s_S_F_preferenceName+"_size";
    static String s_S_F_index = Folder.s_S_F_preferenceName+"_";

    SharedPreferences v_Sp_F_Preferences;
    SharedPreferences.Editor v_sp_F_Editor;
    Context v_c_F_Contexto;

    public Folder(Context contexto){
        super();
        this.v_c_F_Contexto=contexto;
        this.init();
    }
    void init(){
        this.v_Sp_F_Preferences = this.v_c_F_Contexto.getSharedPreferences(Folder.s_S_F_preferenceName,Context.MODE_PRIVATE);
        this.v_sp_F_Editor = this.v_Sp_F_Preferences.edit();
        this.load();
    }
    /*void load(){//el viejo
        int size = this.v_Sp_F_Preferences.getInt(Folder.s_S_F_size,0);
        if(size == 0){
            //si el shared no tiene nada, lo agrega y a la vez actualiza el shared
            this.add("My Gift List");
            //pero porque? :/ de que sirve tener una carpeta si estara vacio
        }else{
            //va de indice en indece tomando cada folder
            for(int i = 0;i < size; i++){
                String folder = this.v_Sp_F_Preferences.getString(Folder.s_S_F_index+i,"ERROR!");
                super.add(folder);
            }
        }
    }*/
    void load(){
        for(int i = 0;i <v_Sp_F_Preferences.getAll().size(); i++){
            String folder="";
            this.v_Sp_F_Preferences.getString(""+i,folder);
            super.add(folder);
        }
    }


    @Override
    public boolean add(String nuevaCarpeta){
        addFolder(nuevaCarpeta);
        return super.add(nuevaCarpeta);

    }
    boolean addFolder(String name){
        this.v_sp_F_Editor.putString(""+v_Sp_F_Preferences.getAll().size(),name);
        return this.v_sp_F_Editor.commit();
    }

    public boolean remover(String key){
        int index = super.indexOf(key);
        if(index == -1)
            return false;
        this.v_sp_F_Editor.remove(Folder.s_S_F_index+index);
        super.remove(index);
        return true;
    }
    public void Mensaje(String msg){
        Toast.makeText(v_c_F_Contexto, msg, Toast.LENGTH_SHORT).show();
    }
}
