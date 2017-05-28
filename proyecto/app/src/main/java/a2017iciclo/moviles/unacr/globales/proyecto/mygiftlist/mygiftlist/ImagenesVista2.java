package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
            // Find the car to work with.
            Gift currentgift = gifts.get(position);

            TextView txtView =(TextView)giftcargado.findViewById(R.id.nombre);
            txtView.setText(currentgift.nombre);

            ImageView imgGift = (ImageView)giftcargado.findViewById(R.id.fondo);
            setPic(imgGift, currentgift.getImg());

            return giftcargado;
        }
    }
    private void setPic(ImageView v,String path) {
        // Get the dimensions of the View
        int targetW = v.getWidth();
        int targetH = v.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        //bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);
        v.setImageBitmap(bitmap);
    }
}
