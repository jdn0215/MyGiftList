package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.DaoMaster;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.DaoSession;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.GiftDB;
import a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db.GiftDBDao;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import static android.R.id.message;
import static android.R.string.no;
import static android.media.CamcorderProfile.get;


public class ImagenesVista2 extends BaseDatos{
    public static String folderactual;
    public List<Gift> gifts=new ArrayList<Gift>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes_vista2);
        folderactual = super.getIntent().getStringExtra("folderactual");
        setTitle(folderactual);
        //estas lineas son para probar esta actividad y el giftview
        //super.gift_dao.deleteAll();
        //super.saveToSQL(new GiftDB((long)super.sizeLista(),""+R.drawable.vader,"figurilla muy repro de vader :D\n PD:\"no dejar cerca de basura rebelde\"","Darth Vader",folderactual,999991,-83.753428,9.748917));

        cargarGifts();
        populateListView();
        registerClickCallback();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_imagenes_vista2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.create_new:
                Intent i = new Intent(ImagenesVista2.super.getApplicationContext(), CreateGift.class);
                i.putExtra("folderactual", folderactual);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void cargarGifts(){

        gifts=super.BuscarPorFolder(folderactual);

    }



    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.imageneslist);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {
                Gift clickedCar = gifts.get(position);
                Intent i = new Intent(getApplicationContext(), GiftView.class);
                i.putExtra("giftId", position);
                startActivity(i);
            }
        });
    }
    private void populateListView() {
        ArrayAdapter<Gift> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.imageneslist);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Gift> {
        public MyListAdapter() {
            super(ImagenesVista2.this, R.layout.giftcargado, gifts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup
                parent) {
            // Make sure we have a view to work with (may have been given null)
            View giftcargado = convertView;
            if (giftcargado == null) {
                giftcargado = getLayoutInflater().inflate(R.layout.giftcargado,
                        parent, false);
            }

            Gift currentgift = gifts.get(position);

            TextView txtView =(TextView)giftcargado.findViewById(R.id.nombre);
            txtView.setText(currentgift.getNombre());

            ImageView imgGift = (ImageView)giftcargado.findViewById(R.id.fondogift);

            Bitmap bmImg = BitmapFactory.decodeFile(currentgift.getImg());

            imgGift.setImageBitmap(bmImg);

            return giftcargado;
        }
    }

    /*
    public Bitmap getImage(Uri uri) {
        Bitmap result = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        InputStream is = null;
        try {
            Bitmap b = Bitmap.createBitmap(100, 100,Bitmap.Config.ARGB_8888);
            is = super.getApplicationContext().getContentResolver().openInputStream(uri);
            result = BitmapFactory.decodeStream(is, null, options);
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }*/

}
