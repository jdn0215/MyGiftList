package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.GiftDB;

/**
 * falta generar las imagenes con la carpeta correspondiente
 * cambiar el fab por el icono de camara para que invoque a la camara
 *
 *
 *
 * */


public class ImagenesVista extends AppCompatActivity {
    static String s_S_IV_argumentoNombre="ImagenesVistaFonderSelected";
    String current;
    Gifts gifts;
    public static BaseDatos db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes_vista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent i = new Intent(ImagenesVista.super.getApplicationContext(),CreateGift.class);
                i.putExtra(ImagenesVista.s_S_IV_argumentoNombre,current);
                startActivity(i);
            }
        });
        db=new BaseDatos(this.getApplicationContext());
        //Mensaje(""+db.sizeDB());
        //aqui voy

    }
    void init(){
        current = super.getIntent().getStringExtra(ImagenesVista.s_S_IV_argumentoNombre);
        this.setBarra(current);
        this.cargarGifts();
    }




    void cargarGifts(){
        ListView tl = (ListView) super.findViewById(R.id.listview);
        //Context ct = super.getApplicationContext();
        this.gifts = new Gifts();

    }

    void setBarra(String msg){
        getSupportActionBar().setTitle(msg);
    }

    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};
}
