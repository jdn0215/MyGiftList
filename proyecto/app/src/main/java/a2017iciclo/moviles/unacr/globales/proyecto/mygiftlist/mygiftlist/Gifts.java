package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.content.Context;
import android.widget.ListView;
import android.widget.TableLayout;

import java.util.ArrayList;

/**
 * Created by jdani on 3/4/2017.
 */

public class Gifts extends TableCreator{

    ArrayList<Gift>gifts;
    public  Gifts(ListView tl, Context context, int maxSize){
        super(tl,context);
        this.gifts = new ArrayList<>();
    }
    @Override
    public void addComponent(Component comp){
        if(comp instanceof Gift){
            this.gifts.add((Gift)comp);
            super.addComponent(comp);
        }
    }
}
