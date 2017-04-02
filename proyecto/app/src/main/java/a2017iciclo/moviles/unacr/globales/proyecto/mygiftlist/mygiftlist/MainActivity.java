package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import static a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.R.drawable.carpeta;

public class MainActivity extends AppCompatActivity {
    Folder v_F_M_folders;
    TableCreator v_TC_M_creator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v_F_M_folders = new Folder(super.getApplicationContext());
        TableLayout v_F_M_tb= (TableLayout) findViewById(R.id.carpetas);
        v_TC_M_creator=new TableCreator(v_F_M_tb,super.getApplicationContext(),3);
        for(int i=0;i<v_F_M_folders.size();i++){
            Component comp=new Component(super.getApplicationContext(),v_F_M_folders.get(i),"carpeta.png");
            v_TC_M_creator.addComponent(comp);
        }
    }
}
