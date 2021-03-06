package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import java.util.Date;

import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.GiftDB;

/**
 * Created by jdani on 3/4/2017.
 */

public class Gift{
    String img,descp,nombre,folder;
    double lng;
    double lat;
    int precio;

    public Gift(String img, String descp, String nombre, String folder, int precio,double lng,double lat) {
        this.img = img;
        this.descp = descp;
        this.nombre = nombre;
        this.folder = folder;
        this.precio = precio;
        this.lng = lng;
        this.lat = lat;
    }

    public GiftDB DB(){
        return new GiftDB(
                new Date().getTime(),
                this.img,
                this.descp,
                this.nombre,
                this.folder,
                this.precio,
                this.lng,
                this.lat
        );
    }

    public String getDescp() {
        return descp;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public GiftDB toGiftDB(){
        return new GiftDB(null,img,descp,nombre,folder,precio,lng,lat);
    }
}