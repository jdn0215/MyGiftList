package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by jdani on 2/4/2017.
 */

///para manejar de forma rápida las carpetas de imagenes de la app
final public class Folder extends ArrayList<String> {

    static String s_S_preferenceName="mgl_folders";
    static String s_S_size=Folder.s_S_preferenceName+"_size";
    static String s_S_index = Folder.s_S_preferenceName+"_";


    SharedPreferences v_Sp_Preferences;
    SharedPreferences.Editor v_sp_Editor;
    Context v_c_Contexto;
    public Folder(Context contexto){
        super();
        this.v_c_Contexto=contexto;
        this.init();
    }
    void init(){
        this.v_Sp_Preferences = this.v_c_Contexto.getSharedPreferences(Folder.s_S_preferenceName,Context.MODE_PRIVATE);
        this.v_sp_Editor = this.v_Sp_Preferences.edit();
        this.load();
    }
    void load(){
        int size = this.v_Sp_Preferences.getInt(Folder.s_S_size,0);
        if(size == 0){
            //si el shared no tiene nada, lo agrega y a la vez actualiza el shared
            this.add("My Gift List");
        }else{
            //va de indice en indece tomando cada folder
            for(int i = 0;i < size; i++){
                String folder = this.v_Sp_Preferences.getString(Folder.s_S_index+i,"ERROR!");
                super.add(folder);
            }
        }
    }


    @Override
    public boolean add(String nuevaCarpeta){
        if(super.indexOf(nuevaCarpeta)!= -1)
            return false;
        this.addFolder(nuevaCarpeta);
        return addFolder(nuevaCarpeta) && super.add(nuevaCarpeta);
    }
    boolean addFolder(String name){
        this.v_sp_Editor.putString(Folder.s_S_index+super.size(),name);
        return this.v_sp_Editor.commit();
    }

    public boolean remover(String key){
        int index = super.indexOf(key);
        if(index == -1)
            return false;
        this.v_sp_Editor.remove(Folder.s_S_index+index);
        super.remove(index);
        return true;
    }

}