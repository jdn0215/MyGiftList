package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;


public class ImagenesVista2 extends BaseDatos{
    public static String folderactual;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes_vista2);
        folderactual=super.getIntent().getStringExtra("folderactual");
        super.sizeLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_imagenes_vista2, menu);
        return true;
    }

    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};
}
