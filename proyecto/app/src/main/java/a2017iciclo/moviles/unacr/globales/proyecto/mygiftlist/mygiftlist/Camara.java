package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

/**
 * Created by jdani on 10/4/2017.
 */

public class Camara extends AppCompatActivity {
    //Request
    private static final int CAMERA_CAPTURE_IMAGE = 10; //Tam modificable
    private static final int MEDIA_TYPE_IMAGE = 1;
    //Directorio
    private static final String IMAGE_DIRECTORY_NAME = "Hello_Camara";
    private Uri fileUri; //Para ubicar el archivo

    private ImageView MiImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.Camara);
        Button MiButton = (Button) findViewById(R.id.btnCamara);
        MiImageView = (ImageView) findViewById(R.id.vistaPrevia);
        //Aquí el onclick

        MiButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {

                capturaImagen();
            }
        });
        //Tengo que acomodarlo por que ahí se ve feo
        if(!isDiviceSupportCamara()){
            Toast.makeText(getApplicationContext(),
                    "Lo Setimos, dispositivo no posee camara ",
                    Toast.LENGTH_LONG).show();
        }//finish();

    }//Fin del Oncreate.

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("file_uri",fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    //Chekear que el dispositivo tiene camara
    private boolean isDiviceSupportCamara(){
        if(getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)){
            return true;
        }else{
            return false;
        }
    }
    private void capturaImagen(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);

        startActivityForResult(intent,CAMERA_CAPTURE_IMAGE);
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
            }else{
                Toast.makeText(getApplicationContext(),
                        "Lo sentimos, Fallo la captura", Toast.LENGTH_SHORT)
                        .show();
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
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    //Retornando una imagen...
    public Uri getOutputMediaFileUri(int tipo){
        return Uri.fromFile(getOutputMediaFile(tipo));
    }
    private static File getOutputMediaFile(int tipo){

        File directorio = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);
        if(!directorio.exists()){
            if(!directorio.mkdir()){
                Log.d(IMAGE_DIRECTORY_NAME,"Fallo la creacion"+
                        IMAGE_DIRECTORY_NAME+"directory");
                return null;
            }
        }
        //Aquí se crea el nombre del archivo
        String horadeCaptura = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if(tipo == MEDIA_TYPE_IMAGE){
            mediaFile = new File(directorio.getPath()+File.separator
                    +"IMG_"+ horadeCaptura+".jpg");
        }else {
            return null;
        }
        return mediaFile;
    }

}//Fin del Activity.
