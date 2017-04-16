package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.content.Context;

/**
 * Created by jdani on 3/4/2017.
 */

public class Gift extends Component{
    class Position{
        public double lat,lng;
        Position(double a,double b){lat = a; lng = b;}
    };
    String img,descp,nombre,folder;
    Position pt;
    int precio;
    public Gift(Context cont, double arg0, double arg1, String arg2, String arg3, String arg4, int arg5,String folder){
        super(cont,arg2,arg4);
        this.pt    = new Position(arg0,arg1);
        this.img   = arg2;
        this.descp = arg3;
        this.nombre= arg4;
        this.precio= arg5;
        this.folder = folder;
    }
    public Position getPos    (){ return pt    ;}
    public String   getImgPath(){ return img   ;}
    public String   getDescp  (){ return descp ;}
    public String   getNombre (){ return nombre;}
    public int      getPrecio (){ return precio;}
}