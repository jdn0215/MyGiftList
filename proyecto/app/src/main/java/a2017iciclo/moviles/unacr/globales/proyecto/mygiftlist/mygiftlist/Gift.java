package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.content.Context;

/**
 * Created by jdani on 3/4/2017.
 */

public class Gift{
    String img,descp,nombre,folder;
    int precio;

    public Gift(String img, String descp, String nombre, String folder, int precio) {
        this.img = img;
        this.descp = descp;
        this.nombre = nombre;
        this.folder = folder;
        this.precio = precio;
    }

    public String   getImgPath(){ return img   ;}
    public String   getDescp  (){ return descp ;}
    public String   getNombre (){ return nombre;}
    public int      getPrecio (){ return precio;}
}