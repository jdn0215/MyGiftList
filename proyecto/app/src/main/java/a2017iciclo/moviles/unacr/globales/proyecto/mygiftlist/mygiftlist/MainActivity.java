package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

import static a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.R.id.btn_agregar;

/**
 * agregar un botón que sirva para crear carpertas
 * menu contextual para eliminar o renombrar carpetas
 *L-> si ok toy en eso :v
 * */



public class MainActivity extends AppCompatActivity {
    Folder v_F_M_folders;
    TableCreator v_TC_M_creator;
    TableLayout v_TL_M_table;
    boolean colorFlag = true;
    String color1 = "#E0F2F7";
    String color2 = "#F2E0F7";
    static EditText texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();

        DemeTexto(findViewById(btn_agregar));



    }
    public void DemeTexto(View view){
        // Uso:
        texto =  new EditText(view.getContext());
        AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext());
        builder1.setMessage("Digite el nuevo nombre para el evento:");
        texto.setText("nombre nuevo para la carpeta");
        texto.selectAll();
        builder1.setView(texto);

        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        v_F_M_folders.add(texto.getText().toString());
                    }
                });

        builder1.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    };
    void init(){
        this.initAttributes();
        this.initTable();
    }
    void initAttributes(){
        this.v_F_M_folders = new Folder(super.getApplicationContext());
        this.v_TL_M_table  = (TableLayout) findViewById(R.id.carpetas);
        this.v_TC_M_creator=new TableCreator(this.v_TL_M_table,super.getApplicationContext(),3);
    }
    void initTable(){
        for(int i=0;i<v_F_M_folders.size();i++){
            Component comp=new Component(super.getApplicationContext(),R.drawable.carpeta,v_F_M_folders.get(i));
            if(colorFlag)
                comp.get().setBackgroundColor(Color.parseColor(color1));
            else comp.get().setBackgroundColor(Color.parseColor(color2));
            colorFlag = !colorFlag;
            addEvent(comp);
            v_TC_M_creator.addComponent(comp);
        }
    }
    void addEvent(Component c){
        final String name = c.getTitle();
        c.get().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intento = new Intent(getApplicationContext(), ImagenesVista.class);
                intento.putExtra(ImagenesVista.s_S_IV_argumentoNombre,name);
                startActivity(intento);
            }
        });
    }


}





