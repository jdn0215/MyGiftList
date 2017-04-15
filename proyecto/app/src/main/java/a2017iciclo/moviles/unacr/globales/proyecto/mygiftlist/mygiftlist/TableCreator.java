package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by jdani on 2/4/2017.
 */

public class TableCreator {
    TableLayout table;
    int maxSize;
    Context context;
    public TableCreator(TableLayout tl,Context c,int ms){
        this.table = tl;
        this.context = c;
        this.maxSize = ms;
    }
    TableRow getCurrentRow(){
        TableRow row = (TableRow)table.getChildAt( table.getChildCount() - 1);
        int  k = row.getChildCount();
        if( k >= this.maxSize){
            TableRow tr=new TableRow(this.context);
            table.addView(tr);
            row = (TableRow)table.getChildAt( table.getChildCount() - 1);
        }
        return row;
    }
    public void addComponent(Component comp){
        if(comp!=null){
            this.getCurrentRow().addView(comp.get());
        }
    }

}
