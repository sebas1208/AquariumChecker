package ec.edu.epn.aquariumchecker.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;
import ec.edu.epn.aquariumchecker.vo.Galeria;

/**
 * Created by sebas on 7/6/2016.
 */
public class GaleriaService {

    private Context appContext;

    public GaleriaService(Context appContext) {
        this.appContext = appContext;
    }

    public GaleriaService() {
    }

    public long createGaleria(Galeria galeria){
        AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(appContext);
        SQLiteDatabase db = op.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        ContentValues valores = new ContentValues();
        valores.put(AquariumCheckerAppContract.TablaGaleria.COLUMNA_FECHA,dateFormat.format(galeria.getFecha()));
        valores.put(AquariumCheckerAppContract.TablaGaleria.COLUMNA_OBSERVACIONES,galeria.getObservaciones());
        valores.put(AquariumCheckerAppContract.TablaGaleria.COLUMNA_FOTOS,"");
        valores.put(AquariumCheckerAppContract.TablaGaleria.ACUARIO_ID,galeria.getIdAcuario());
        long id = db.insert(AquariumCheckerAppContract.TablaGaleria.NOMBRE_TABLA, null, valores);
        db.close();
        return id;
    }

    public List<Galeria> listGalerias(){
        AquariumCheckerAppOpenHelper oh = new AquariumCheckerAppOpenHelper(appContext);
        List<Galeria> l = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SQLiteDatabase db = oh.getReadableDatabase();
        String[] columnas = {AquariumCheckerAppContract.TablaGaleria.COLUMNA_FECHA,
                AquariumCheckerAppContract.TablaGaleria.COLUMNA_OBSERVACIONES,
                AquariumCheckerAppContract.TablaGaleria.COLUMNA_FOTOS,
                AquariumCheckerAppContract.TablaGaleria._ID,
                AquariumCheckerAppContract.TablaGaleria.ACUARIO_ID
        };


        Cursor cur = db.query(
                AquariumCheckerAppContract.TablaGaleria.NOMBRE_TABLA,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        while (cur.moveToNext()) {
            Date date = null;
            try {
                date = sdf.parse(cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaGaleria.COLUMNA_FECHA)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Galeria galeria = new Galeria(
                    date,
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaGaleria.COLUMNA_OBSERVACIONES)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaGaleria.COLUMNA_FOTOS)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaGaleria._ID)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaGaleria.ACUARIO_ID)));
            l.add(galeria);
        }
        return l;
    }

    public List<Galeria> listGaleriasPorAcuario(AcuarioVO acuario){
        AquariumCheckerAppOpenHelper oh = new AquariumCheckerAppOpenHelper(appContext);
        List<Galeria> l = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] selectionValues = {String.valueOf(acuario.getId())};

        SQLiteDatabase db = oh.getReadableDatabase();
        String[] columnas = {AquariumCheckerAppContract.TablaGaleria.COLUMNA_FECHA,
                AquariumCheckerAppContract.TablaGaleria.COLUMNA_OBSERVACIONES,
                AquariumCheckerAppContract.TablaGaleria.COLUMNA_FOTOS,
                AquariumCheckerAppContract.TablaGaleria.ACUARIO_ID,
                AquariumCheckerAppContract.TablaGaleria._ID,
        };


        Cursor cur = db.query(
                AquariumCheckerAppContract.TablaGaleria.NOMBRE_TABLA,
                columnas,
                AquariumCheckerAppContract.TablaGaleria.ACUARIO_ID + " = ?",
                selectionValues,
                null,
                null,
                null
        );

        while (cur.moveToNext()) {
            Date date = null;
            try {
                date = sdf.parse(cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaGaleria.COLUMNA_FECHA)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Galeria galeria = new Galeria(
                    date,
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaGaleria.COLUMNA_OBSERVACIONES)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaGaleria.COLUMNA_FOTOS)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaGaleria._ID)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaGaleria.ACUARIO_ID)));
            l.add(galeria);
        }
        return l;
    }

    public boolean removeGaleria(){

        return false;
    }

    public Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context appContext) {
        this.appContext = appContext;
    }


}
