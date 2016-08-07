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
import ec.edu.epn.aquariumchecker.vo.Planta;

/**
 * Created by natyd on 7/6/2016.
 */
public class PlantasService {

    private Context appContext;

    public PlantasService(Context appContext) {
        this.appContext = appContext;
    }

    public PlantasService() {
    }

    public void createPlanta(Planta planta){
        AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(appContext);
        SQLiteDatabase db = op.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(AquariumCheckerAppContract.TablaPlantas.COLUMNA_NOMBRE,planta.getNombre());
        valores.put(AquariumCheckerAppContract.TablaPlantas.COLUMNA_CANTIDAD,planta.getCantidad());
        valores.put(AquariumCheckerAppContract.TablaPlantas.COLUMNA_DESCRIPCION,planta.getDescripcion());
        valores.put(AquariumCheckerAppContract.TablaPlantas.COLUMNA_FOTO,planta.getFotoURL());
        valores.put(AquariumCheckerAppContract.TablaPlantas.ACUARIO_ID,planta.getAcuarioId());
        db.insert(AquariumCheckerAppContract.TablaPlantas.NOMBRE_TABLA, null, valores);
        db.close();
    }

    public List<Planta> listaPlantasPorAcuario(Acuario acuario){
        AquariumCheckerAppOpenHelper oh = new AquariumCheckerAppOpenHelper(appContext);
        List<Planta> plantas = new ArrayList<>();
        SQLiteDatabase db = oh.getReadableDatabase();

        String[] id = {Integer.toString(acuario.getId())};

        String[] columnas = {AquariumCheckerAppContract.TablaPlantas.COLUMNA_NOMBRE,
                AquariumCheckerAppContract.TablaPlantas.COLUMNA_DESCRIPCION,
                AquariumCheckerAppContract.TablaPlantas.COLUMNA_CANTIDAD,
                AquariumCheckerAppContract.TablaPlantas.COLUMNA_FOTO,
                AquariumCheckerAppContract.TablaPlantas._ID,
                AquariumCheckerAppContract.TablaPlantas.ACUARIO_ID
        };

        Cursor cur = db.query(
                AquariumCheckerAppContract.TablaPlantas.NOMBRE_TABLA,
                columnas,
                AquariumCheckerAppContract.TablaPlantas.ACUARIO_ID + " = ?", id,
                null,
                null,
                null
        );

        while (cur.moveToNext()) {
            Planta planta = new Planta(cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaPlantas.COLUMNA_NOMBRE)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaPlantas.COLUMNA_DESCRIPCION)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaPlantas.COLUMNA_CANTIDAD)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaPlantas.COLUMNA_FOTO)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaPlantas._ID)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaPlantas.ACUARIO_ID)));
            plantas.add(planta);
        }

        return plantas;
    }

    public List<Planta> listaPlantas(){

        AquariumCheckerAppOpenHelper oh = new AquariumCheckerAppOpenHelper(appContext);
        List<Planta> plantas = new ArrayList<>();
        SQLiteDatabase db = oh.getReadableDatabase();

        String[] columnas = {AquariumCheckerAppContract.TablaPlantas.COLUMNA_NOMBRE,
                AquariumCheckerAppContract.TablaPlantas.COLUMNA_DESCRIPCION,
                AquariumCheckerAppContract.TablaPlantas.COLUMNA_CANTIDAD,
                AquariumCheckerAppContract.TablaPlantas.COLUMNA_FOTO,
                AquariumCheckerAppContract.TablaPlantas._ID,
                AquariumCheckerAppContract.TablaPlantas.ACUARIO_ID
        };

        Cursor cur = db.query(
                AquariumCheckerAppContract.TablaPlantas.NOMBRE_TABLA,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        while (cur.moveToNext()) {
            Planta planta = new Planta(cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaPlantas.COLUMNA_NOMBRE)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaPlantas.COLUMNA_DESCRIPCION)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaPlantas.COLUMNA_CANTIDAD)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaPlantas.COLUMNA_FOTO)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaPlantas._ID)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaPlantas.ACUARIO_ID)));
            plantas.add(planta);
        }

        return plantas;
    }

    public boolean removePlantas(Planta planta){
        AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(appContext);
        SQLiteDatabase db = op.getWritableDatabase();
        String[] id = { Integer.toString(planta.getId())};

        db.delete(AquariumCheckerAppContract.TablaPlantas.NOMBRE_TABLA,
                AquariumCheckerAppContract.TablaPlantas._ID + " = ?",
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

