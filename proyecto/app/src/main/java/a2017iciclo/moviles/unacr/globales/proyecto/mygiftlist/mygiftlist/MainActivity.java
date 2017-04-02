package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Folder folders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.print("llega aqui");
        setContentView(R.layout.activity_main);
        folders = new Folder(super.getApplicationContext());
        //linea de prueba
       ((TextView)super.findViewById(R.id.prueba)).setText(folders.get(0));
    }
}
