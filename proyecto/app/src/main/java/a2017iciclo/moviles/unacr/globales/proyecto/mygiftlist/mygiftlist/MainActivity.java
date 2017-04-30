package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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


import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.DaoMaster;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.DaoSession;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.GiftDB;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.GiftDBDao;

import static android.R.attr.name;

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
    private GiftDBDao gift_dao;//->data access object
    private GiftDB gift_temporal;

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

        DandoClickALosItems();

        ListView categorias=(ListView)findViewById(R.id.listview);
        registerForContextMenu(categorias);

        //probando la base de datos
        gift_dao=setupDB();
        
    }
    //iniciar base de datos
    public GiftDBDao setupDB(){
        DaoMaster.DevOpenHelper masterHelper = new DaoMaster.DevOpenHelper(this, "GIFTS_DB", null); //create database db file if not exist
        SQLiteDatabase db = masterHelper.getWritableDatabase();  //get the created database db file
        DaoMaster master = new DaoMaster(db);//create masterDao
        DaoSession masterSession=master.newSession(); //Creates Session session
        return masterSession.getGiftDBDao();
    }
    public void saveToSQL(GiftDB gift_object) {
        gift_dao.insert(gift_object);
    }



    public void DandoClickALosItems() {
        ListView list = (ListView) findViewById(R.id.listview);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,int position, long id)
            { TextView textView = (TextView) viewClicked;
                Intent intento = new Intent(getApplicationContext(), ImagenesVista.class);
                intento.putExtra(ImagenesVista.s_S_IV_argumentoNombre,v_F_M_folders.get(position));
                startActivity(intento);
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
                        agregarRow();
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
        agregarRow();
    }



    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};


    private void agregarRow(){//solo parte visual
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

        super.onCreateContextMenu(menu, v, menuInfo);
            menu.add(0,1,0,"Eliminar");
            menu.add(0,2,0,"Mostrar detalles");
            menu.add(0,3,0,"Marcar como favorito");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1: {
                //v_F_M_folders.remover(""+opcionseleccionada);
                recreate();
                break;

            }
            case 2: Mensaje("Detalles pros de esta ");
            break;
            case 3: Mensaje("Marcado :D y será bien pichuo despues XD");
            break;
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





