package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by jdani on 14/5/2017.
 */

public class GPS {
    Location location;
    LocationManager locationManager;
    LocationListener locationListener;
    AppCompatActivity activity;
    ObtenerWebService hiloconexion;
    public GPS(AppCompatActivity activity){
        this.activity = activity;
        init();
    }
    final void init(){
        verificarLocation();
        this.location = initLocation();
        if(this.location == null)return;

    }
    final void verificarLocation(){
        locationManager = (LocationManager)this.activity.getSystemService(LOCATION_SERVICE);
        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            AlertNoGps();
        }
    }



    final Location initLocation(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return null;
            } else {
                return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        } else {
            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
    }
    private void AlertNoGps() {
        AlertDialog alert = null;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setMessage("El sistema GPS esta desactivado, ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        activity.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }
    public Location getLocation(){
        return this.location;
    }
};













