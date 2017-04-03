package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
/**
 * agregar un botón que sirva para crear carpertas
 * menu contextual para eliminar o renombrar carpetas
 *
 * */



public class MainActivity extends AppCompatActivity {
    Folder v_F_M_folders;
    TableCreator v_TC_M_creator;
    TableLayout v_TL_M_table;
    boolean colorFlag = true;
    String color1 = "#E0F2F7";
    String color2 = "#F2E0F7";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }
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





