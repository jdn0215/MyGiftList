package com.mgiftlist.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Luis on 29/4/2017.
 */

public class Generador {

    private static final String PROJECT_DIR = System.getProperty("user.dir");

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist.db");
        schema.enableKeepSectionsByDefault();

        agregarTablaGifts(schema);

        DaoGenerator db =new DaoGenerator();
        db.generateAll(schema,PROJECT_DIR+"\\app\\src\\main\\java");

    }

    private static void agregarTablaGifts(final Schema schema){
        Entity gift=addGift(schema);
    }

    private static Entity addGift(final Schema schema){
        Entity gift = schema.addEntity("GiftDB");
        gift.addIdProperty().primaryKey().autoincrement();
        gift.addStringProperty("img");
        gift.addStringProperty("descp");
        gift.addStringProperty("nombre");
        gift.addStringProperty("folder");
        gift.addIntProperty("precio");
        gift.addDoubleProperty("lng");
        gift.addDoubleProperty("lat");
        return gift;
    }
}
