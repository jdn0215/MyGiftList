package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * agregar un botón que sirva para crear carpertas
 * menu contextual para eliminar o renombrar carpetas
 *L-> si ok toy en eso :v
 * */



public class MainActivity extends AppCompatActivity {
    Folder v_F_M_folders;
    ListView v_LV_M_table;

    String seleccionado;


    boolean colorFlag = true;
    String color1 = "#E0F2F7";
    String color2 = "#F2E0F7";
    int v_I_MA_paraeliminar=-1;
    static EditText texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();

        Button agregar=(Button)findViewById(R.id.btn_agregar);

        agregar.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View arg0) {
                CargarTexto(findViewById(R.id.btn_agregar));
            }

        });
        Button integrantes = (Button) findViewById(R.id.btnintegrantes);
        integrantes.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View arg0) {
                Intent intento = new Intent(getApplicationContext(), integrantes.class);
                startActivity(intento);
            }

        });


    }

    public void DandoClickALosItems() {
        ListView list = (ListView) findViewById(R.id.listview);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> paret, View viewClicked,
                                    int position, long id)
            { TextView textView = (TextView) viewClicked;
                String message = "Presidente # " + (1+position) + ", corresponde a: " + textView.getText().toString();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void CargarTexto(View view){
        // Uso:
        texto =  new EditText(view.getContext());
        AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext());
        builder1.setMessage("Digite el nuevo nombre para la categoría:");
        texto.setText("nombre");
        texto.selectAll();
        builder1.setView(texto);

        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        v_F_M_folders.add(texto.getText().toString());
                        agregarRow(v_F_M_folders.get(v_F_M_folders.size()-1));
                    }
                });

        builder1.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Mensaje("Categoría no agregada");
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
        this.v_LV_M_table  = (ListView) findViewById(R.id.listview);
    }
    void initTable(){
        for(int i=0;i<v_F_M_folders.size();i++){
            agregarRow(v_F_M_folders.get(i));
        }
    }
    /*
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
        //registerForContextMenu(c.get());
    }
    */
    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};


    private void agregarRow(String nombre){//solo parte visual

        addLinea();
    }

    public void addLinea(){
        String [] temp={};
        String [] nombres=v_F_M_folders.toArray(temp);
        ArrayAdapter<String> adaptador =new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, nombres);
        ListView milistview = (ListView) findViewById(R.id.listview);
        milistview.setAdapter(adaptador);

        v_LV_M_table.setAdapter(adaptador);
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Mensaje("");
        super.onCreateContextMenu(menu, v, menuInfo);
            menu.add(0, 1, 0, "Eliminar");
            menu.add(0, 2, 0, "Cancelar");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int opcionseleccionada = item.getItemId();
        switch (item.getItemId()) {
            case 1: {
            v_F_M_folders.remover(seleccionado);
            }; break;
            case 2: Mensaje("Cancelado"); break;
        }
        //this.v_F_M_folders.remover("la carpeta");
        return true;
    }




    public void DialogoSiNo(View view){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext());
        builder1.setMessage("Desea eliminar este elemento.");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {Mensaje("positivo"); } });
        builder1.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {Mensaje("negativo"); } });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    };
}





