package ec.edu.epn.aquariumchecker.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Peces;

/**
 * Created by sebas on 7/6/2016.
 */
public class PecesService {

    private Context appContext;

    public PecesService(Context appContext) {
        this.appContext = appContext;
    }

    public PecesService() {
    }

    public void createPez(Peces pez){
        AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(appContext);
        SQLiteDatabase db = op.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(AquariumCheckerAppContract.TablaPeces.COLUMNA_NOMBRE,pez.getNombre());
        valores.put(AquariumCheckerAppContract.TablaPeces.COLUMNA_CANTIDAD,pez.getCantidad());
        valores.put(AquariumCheckerAppContract.TablaPeces.COLUMNA_DESCRIPCION,pez.getDescripcion());
        valores.put(AquariumCheckerAppContract.TablaPeces.COLUMNA_FOTO,pez.getFotoURL());
        valores.put(AquariumCheckerAppContract.TablaPeces.ACUARIO_ID,pez.getAcuario_id());
        db.insert(AquariumCheckerAppContract.TablaPeces.NOMBRE_TABLA, null, valores);
        db.close();
    }

    public List<Peces> listaPecesPorAcuario(Acuario acuario){
        AquariumCheckerAppOpenHelper oh = new AquariumCheckerAppOpenHelper(appContext);
        List<Peces> peces = new ArrayList<>();
        SQLiteDatabase db = oh.getReadableDatabase();

        String[] id = {Integer.toString(acuario.getId())};

        String[] columnas = {AquariumCheckerAppContract.TablaPeces.COLUMNA_NOMBRE,
                AquariumCheckerAppContract.TablaPeces.COLUMNA_DESCRIPCION,
                AquariumCheckerAppContract.TablaPeces.COLUMNA_CANTIDAD,
                AquariumCheckerAppContract.TablaPeces.COLUMNA_FOTO,
                AquariumCheckerAppContract.TablaPeces._ID,
                AquariumCheckerAppContract.TablaPeces.ACUARIO_ID
        };

        Cursor cur = db.query(
                AquariumCheckerAppContract.TablaPeces.NOMBRE_TABLA,
                columnas,
                AquariumCheckerAppContract.TablaPeces.ACUARIO_ID + " = ?", id,
                null,
                null,
                null
        );

        while (cur.moveToNext()) {
            Peces pez = new Peces(cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaPeces.COLUMNA_NOMBRE)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaPeces.COLUMNA_DESCRIPCION)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaPeces.COLUMNA_CANTIDAD)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaPeces.COLUMNA_FOTO)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaPeces._ID)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaPeces.ACUARIO_ID)));
            peces.add(pez);
        }

        return peces;
    }

    public List<Peces> listaPeces(){

        AquariumCheckerAppOpenHelper oh = new AquariumCheckerAppOpenHelper(appContext);
        List<Peces> peces = new ArrayList<>();
        SQLiteDatabase db = oh.getReadableDatabase();

        String[] columnas = {AquariumCheckerAppContract.TablaPeces.COLUMNA_NOMBRE,
                AquariumCheckerAppContract.TablaPeces.COLUMNA_DESCRIPCION,
                AquariumCheckerAppContract.TablaPeces.COLUMNA_CANTIDAD,
                AquariumCheckerAppContract.TablaPeces.COLUMNA_FOTO,
                AquariumCheckerAppContract.TablaPeces._ID,
                AquariumCheckerAppContract.TablaPeces.ACUARIO_ID
        };

        Cursor cur = db.query(
                AquariumCheckerAppContract.TablaPeces.NOMBRE_TABLA,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        while (cur.moveToNext()) {
            Peces pez = new Peces(cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaPeces.COLUMNA_NOMBRE)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaPeces.COLUMNA_DESCRIPCION)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaPeces.COLUMNA_CANTIDAD)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaPeces.COLUMNA_FOTO)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaPeces._ID)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaPeces.ACUARIO_ID)));
            peces.add(pez);
        }

        return peces;
    }

    public boolean removePeces(Peces peces){
        AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(appContext);
        SQLiteDatabase db = op.getWritableDatabase();
        String[] id = { Integer.toString(peces.getId())};

        db.delete(AquariumCheckerAppContract.TablaPeces.NOMBRE_TABLA,
                AquariumCheckerAppContract.TablaPeces._ID + " = ?",
                id);
        db.close();
        return true ;
    }

    public Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context appContext) {
        this.appContext = appContext;
    }

}
