package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jdani on 2/4/2017.
 */
//componente de la tablas que se mostraran
public class Component {
    FrameLayout wrapper;
    ImageView imagen;
    TextView title;
    Context context;
    public Component(Context context,int idImage,String TxtTitle){
        this.context=context;
        init(idImage,TxtTitle);
    }
    public Component(Context context,String path,String TxtTitle){
        this.context=context;
        init(path,TxtTitle);
    }
    void init(int idImage,String TxtTitle){
        wrapper = new FrameLayout(context);
        imagen = new ImageView(context);
        title = new TextView(context);
        title.setTextColor(Color.BLACK);
        title.setTextSize(15);
        imagen.setImageResource(idImage);
        title.setText(TxtTitle);
        wrapper.addView(this.imagen);
        wrapper.addView(this.title);
    }
    void init(String path,String TxtTitle){
        wrapper = new FrameLayout(context);
        imagen = new ImageView(context);
        title = new TextView(context);
        title.setTextColor(Color.BLACK);
        title.setTextSize(15);
        imagen.setImageBitmap(this.getImageBitmap(path));
        title.setText(TxtTitle);
        wrapper.addView(this.imagen);
        wrapper.addView(this.title);
    }
    final public String getTitle(){
        return this.title.getText().toString();
    }
    final public View get(){
        return this.wrapper;
    }
    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
           System.err.print("error al cargar imagen "+url);
        }
        return bm;
    }
}
