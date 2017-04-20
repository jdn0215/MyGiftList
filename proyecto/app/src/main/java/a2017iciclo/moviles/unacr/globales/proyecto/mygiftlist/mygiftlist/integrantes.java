package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class integrantes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrantes);

        TextView naza = (TextView) findViewById(R.id.naza);
        ImageView inaza = (ImageView) findViewById(R.id.inaza);

        TextView dani = (TextView) findViewById(R.id.dani);
        ImageView idani = (ImageView) findViewById(R.id.idani);

        TextView luis = (TextView) findViewById(R.id.luis);
        ImageView iluis = (ImageView) findViewById(R.id.iluis);

        TextView cris = (TextView) findViewById(R.id.cris);
        ImageView icris = (ImageView) findViewById(R.id.icris);
    }
}
