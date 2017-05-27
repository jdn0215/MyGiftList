package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GiftView extends BaseDatos implements  OnMapReadyCallback{
    GoogleMap mMap;
    LatLng point;
    Gift current;
    int zoom=12;
    TextView contenido;
    @Override
     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_view);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        eventos();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        this.initGift();
        mMap.addMarker(new MarkerOptions().position(this.point).title("¡AQUÍ!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(this.point,this.zoom));
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }

    final void eventos(){

    }
    final void initGift() {
        long id = super.getIntent().getLongExtra("giftId", 0);
        this.current = super.getGift(id);
        this.contenido = (TextView)super.findViewById(R.id.contenido);
        if(current == null){
            Mensaje("UPS!");
            this.point = new LatLng(0.0,0.0);
            this.contenido.setText("Parece que la información de tu gift ha sido eliminada\nRecuerda no eliminar, ni modificar ningún archivo dentro de la carpeta de MyGiftList");
        }else{
            TextView txttitulo = (TextView)findViewById(R.id.titulo);
            txttitulo.setText(current.getNombre());
            ImageView imgfondo=(ImageView)findViewById(R.id.imagen);
            imgfondo.setImageResource(Integer.valueOf(current.getImg()));
            this.point = new LatLng(current.getLat(),current.getLng());
            this.contenido.setText(preparar());
        }
    }
    public void MensajeOK(String msg){
        View v1 = getWindow().getDecorView().getRootView();
        AlertDialog.Builder builder1 = new AlertDialog.Builder( v1.getContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {} });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void Mensaje(String msg){
        getSupportActionBar().setTitle(msg);
    }
    String preparar(){
        String out="\nCategoría:%s\n\nDescripción:%s\n\nPrecio: %d";
        out = String.format(out,current.getFolder(),current.getDescp(),current.getPrecio());
        return out;
    }
}
