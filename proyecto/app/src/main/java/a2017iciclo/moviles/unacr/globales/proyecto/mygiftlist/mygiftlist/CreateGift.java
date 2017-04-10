package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class CreateGift extends AppCompatActivity {
    String current;
    EditText name;
    EditText descp;
    EditText precio;
    static String idName = "idCamaraNewGift";
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
        this.initAttributes();
        this.initEvents();
    }

    void initAttributes(){
        this.name = (EditText)super.findViewById(R.id.nombre);
        this.descp = (EditText)super.findViewById(R.id.descp);
        this.precio = (EditText)super.findViewById(R.id.precio);
    }

    void initEvents(){
        initEvent(super.findViewById(R.id.camara));
        initEvent(super.findViewById(R.id.save));
        initEvent(super.findViewById(R.id.cancel));
    }
    void initEvent(View v){
        v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switch (v.getId()){
                    case R.id.camara: toCamara(); break;
                    case R.id.save  : save();     break;
                    case R.id.cancel: cancel();   break;
                }
            }
        });
    }

    void toCamara(){
        if(this.name.getText().toString().equals("")){
            mensaje("Ingrese un nombre antes de tomar una foto");
        }else{
           /*
           descomentar cuando la actividad de camara este lista
           Intent i = new Intent(getApplicationContext(),Camara.class);
            i.putExtra(CreateGift.idName,createName());
            startActivity(i);*/
           mensaje(createName());
        }
    }

    String createName(){
        return (this.current.replace(" ","")) + Long.toString(new Date().getTime());
    }

    void save(){
        String result = this.validar();
        if(!result.equals("ok")){
            this.mensaje(result);
        }
        else save(create());
    }
    void save(Gift _new){

    }

    String validar(){
        return "TODO";
    }
    Gift create(){
        return null;
    }
    void cancel(){
        super.onBackPressed();
    }

    void setBarra(String msg){
        getSupportActionBar().setTitle(msg);
    }
    public void mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    };
}
