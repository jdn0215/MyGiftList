package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Matrix;
import java.io.File;
import java.util.Date;



public class CreateGift extends AppCompatActivity {
    String current;
    static String staticCurrent="";
    EditText name;
    EditText descp;
    EditText precio;
    GPS gps;
    static String idName = "idCamaraNewGift";
    static String path="";
    private static final int CAMERA_CAPTURE_IMAGE = 10; //Tam modificable
    private static final int MEDIA_TYPE_IMAGE = 1;
    //Directorio
    private static final String IMAGE_DIRECTORY_NAME = "Hello_Camara";
    private Uri fileUri; //Para ubicar el archivo

    private ImageView MiImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_gift);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        gps = new GPS(this);
    }
    void init(){
        current = super.getIntent().getStringExtra(ImagenesVista.s_S_IV_argumentoNombre);
        CreateGift.staticCurrent=current;
        this.setBarra(current);
        this.initAttributes();
        this.initEvents();
        /*if(!isDiviceSupportCamara()){
            Toast.makeText(getApplicationContext(),
                    "Lo Setimos, dispositivo no posee camara ",
                    Toast.LENGTH_LONG).show();
        }*/
        MiImageView = (ImageView) findViewById(R.id.imagenPrevia);
        super.findViewById(R.id.camara).setVisibility(View.VISIBLE);
    }

    void initAttributes(){
        this.name = (EditText)super.findViewById(R.id.nombre);
        this.descp = (EditText)super.findViewById(R.id.descp);
        this.precio = (EditText)super.findViewById(R.id.precio);
    }

    void initEvents(){
        initEvent(super.findViewById(R.id.camara));
        initEvent(super.findViewById(R.id.save));
    }
    void initEvent(View v){
        v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switch (v.getId()){
                    case R.id.camara: toCamara(); break;
                    //case R.id.save  : save();     break;
                }
            }
        });
    }

    /**
     *
     * APORTE DE NAZA
     *
     */

    /**
     *
     *
     *
     *
     *
     *
     * */

    void toCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
        startActivityForResult(intent,CAMERA_CAPTURE_IMAGE);
    }

    public Bitmap rotateImage(Bitmap bitmap, int rotation) {
        Matrix matrix = new Matrix();
        matrix.postRotate(rotation);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public Uri getOutputMediaFileUri(int tipo){
        return Uri.fromFile(getOutputMediaFile(tipo));
    }

    private static File getOutputMediaFile(int tipo){
        File directorio = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);
        if(!directorio.exists()){
            if(!directorio.mkdir()){
                Log.d(IMAGE_DIRECTORY_NAME,"Fallo la creacion"+
                        IMAGE_DIRECTORY_NAME+"directory");
                return null;
            }
        }
        //Aquí se crea el nombre del archivo

        File mediaFile;
        if(tipo == MEDIA_TYPE_IMAGE){
            CreateGift.path = directorio.getPath()+File.separator +"IMG_"+ createName()+".jpg";
            mediaFile = new File(path);
        }else {
            return null;
        }
        return mediaFile;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_CAPTURE_IMAGE){
            if(resultCode == RESULT_OK){
                previewCapturedImage();
            }else if(requestCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),
                        "EL usuario Cancelo la captura", Toast.LENGTH_SHORT)
                        .show();
                CreateGift.path="";
            }else{
                Toast.makeText(getApplicationContext(),
                        "Lo sentimos, Fallo la captura", Toast.LENGTH_SHORT)
                        .show();
                CreateGift.path="";
            }
        }
    }

    private void previewCapturedImage() {
        try {
            MiImageView.setVisibility(View.VISIBLE);

            BitmapFactory.Options options = new BitmapFactory.Options();

            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);
            MiImageView.setImageBitmap(bitmap);
            super.findViewById(R.id.camara).setVisibility(View.INVISIBLE);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("file_uri",fileUri);
    }


    private boolean isDiviceSupportCamara(){
        if(getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        fileUri = savedInstanceState.getParcelable("file_uri");
    }


    static String createName(){
        return (CreateGift.staticCurrent.replace(" ","")) + Long.toString(new Date().getTime());
    }

    /*
    void save(){
        String result = this.validar();
        if(!result.equals("ok")){
            this.mensaje(result);
        }
        else save(create());//aqui es donde se guarda
    }*/




    void save(Gift _new){
        if(_new!=null){
            /**
             *
             * naza,
             * Luis:ohh shet :VV falta todo el codigo
             *
             * */
        }
    }





    String validar(){
        if(name.getText().toString().equals(""))
            return "Ingrese un nombre";
        if(descp.getText().toString().equals(""))
            return "Ingrese una descropcion";
        try{
            if(precio.getText().toString().equals(""))
                return "Ingrese un precio";
            Integer.parseInt(precio.getText().toString());
        }catch(Exception e){
            return "Precio invalido";
        }
        if(CreateGift.path.equals(""))
            return "Tome una fotografía por favor";
        return "ok";
    }

    /*
    Gift create(){
        return new Gift(
                CreateGift.path,
                descp.getText().toString(),
                name.getText().toString(),
                current,
                Integer.parseInt(precio.getText().toString()));
    }*/


    void setBarra(String msg){
        getSupportActionBar().setTitle(msg);
    }
    public void mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    };
}
