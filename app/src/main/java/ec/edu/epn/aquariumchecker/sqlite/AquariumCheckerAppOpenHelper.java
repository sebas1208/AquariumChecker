package ec.edu.epn.aquariumchecker.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by natyd on 5/6/2016.
 */
public class AquariumCheckerAppOpenHelper extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String base_datos = "aquarium.db";

    public AquariumCheckerAppOpenHelper(Context context) {
        super(context, base_datos, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
        db.execSQL(AquariumCheckerAppContract.TablaAcuario.CREA_TABLAS_ACUARIO);
        db.execSQL(AquariumCheckerAppContract.TablaAcuario.CREA_TABLAS_TABLA_PECES);
        db.execSQL(AquariumCheckerAppContract.TablaAcuario.CREA_TABLAS_PLANTAS);
        db.execSQL(AquariumCheckerAppContract.TablaAcuario.CREA_TABLAS_GALERIA);
        db.execSQL(AquariumCheckerAppContract.TablaAcuario.CREA_TABLAS_FOTO);
        db.execSQL(AquariumCheckerAppContract.TablaAcuario.CREA_TABLAS_HISTORIAL);
        db.execSQL(AquariumCheckerAppContract.TablaAcuario.CREA_TABLAS_RECORDATORIO);

        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            //Elimina las tablas
            db.execSQL(AquariumCheckerAppContract.TablaAcuario.ELIMINA_TABLAS_ACUARIO);
            db.execSQL(AquariumCheckerAppContract.TablaAcuario.ELIMINA_TABLAS_PECES);
            db.execSQL(AquariumCheckerAppContract.TablaAcuario.ELIMINA_TABLAS_PLANTAS);
            db.execSQL(AquariumCheckerAppContract.TablaAcuario.ELIMINA_TABLAS_GALERIA);
            db.execSQL(AquariumCheckerAppContract.TablaAcuario.ELIMINA_TABLAS_FOTO);
            db.execSQL(AquariumCheckerAppContract.TablaAcuario.ELIMINA_TABLAS_HISTORIAL);
            db.execSQL(AquariumCheckerAppContract.TablaAcuario.ELIMINA_TABLAS_RECORDATORIO);

            //Crea las tablas nuevamente
            db.execSQL(AquariumCheckerAppContract.TablaAcuario.CREA_TABLAS_ACUARIO);
            db.execSQL(AquariumCheckerAppContract.TablaAcuario.CREA_TABLAS_TABLA_PECES);
            db.execSQL(AquariumCheckerAppContract.TablaAcuario.CREA_TABLAS_PLANTAS);
            db.execSQL(AquariumCheckerAppContract.TablaAcuario.CREA_TABLAS_GALERIA);
            db.execSQL(AquariumCheckerAppContract.TablaAcuario.CREA_TABLAS_FOTO);
            db.execSQL(AquariumCheckerAppContract.TablaAcuario.CREA_TABLAS_HISTORIAL);
            db.execSQL(AquariumCheckerAppContract.TablaAcuario.CREA_TABLAS_RECORDATORIO);
        }
    }


}


