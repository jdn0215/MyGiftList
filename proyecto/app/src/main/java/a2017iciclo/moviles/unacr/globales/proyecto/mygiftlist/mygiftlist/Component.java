package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
    final void init(int idImage,String TxtTitle){
        wrapper = new FrameLayout(context);
        imagen = new ImageView(context);
        title = new TextView(context);
        imagen.setImageResource(idImage);
        title.setText(TxtTitle);
        wrapper.addView(this.imagen);
        wrapper.addView(this.title);
    }
    final public View get(){
        return this.wrapper;
    }
}
