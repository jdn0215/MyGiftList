package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class CreateGift extends AppCompatActivity {
    String current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_gift);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

    }
    void init(){
        current = super.getIntent().getStringExtra(ImagenesVista.s_S_IV_argumentoNombre);
        this.setBarra(current);
    }

    void setBarra(String msg){
        getSupportActionBar().setTitle(msg);
    }

}
