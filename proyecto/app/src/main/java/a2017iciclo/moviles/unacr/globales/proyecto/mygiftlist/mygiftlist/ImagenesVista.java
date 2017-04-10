package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
/**
 * falta generar las imagenes con la carpera correspondiente
 * cambiar el fab por el icono de camara para que invoque a la camara
 *
 *
 *
 * */


public class ImagenesVista extends AppCompatActivity {
    static String s_S_IV_argumentoNombre="ImagenesVistaFonderSelected";
    String current;
    Gifts gifts; //para gestionar la tabla con las imagenes y el titulo
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
    }
    void init(){
        current = super.getIntent().getStringExtra(ImagenesVista.s_S_IV_argumentoNombre);
        this.setBarra(current);
    }
    void setBarra(String msg){
        getSupportActionBar().setTitle(msg);
    }
}
