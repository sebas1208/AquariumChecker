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
import ec.edu.epn.aquariumchecker.vo.Foto;
import ec.edu.epn.aquariumchecker.vo.Galeria;

/**
 * Created by sebas on 7/6/2016.
 */
public class FotoService {
    private Context appContext;

    public FotoService(Context appContext) {
        this.appContext = appContext;
    }

    public FotoService() {
    }

    public void createFoto(Foto foto){
        AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(appContext);
        SQLiteDatabase db = op.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        ContentValues valores = new ContentValues();
        valores.put(AquariumCheckerAppContract.TablaFoto.COLUMNA_PATH,foto.getPath());
        valores.put(AquariumCheckerAppContract.TablaFoto.COLUMNA_DESCRIPCION,foto.getDescripcion());
        valores.put(AquariumCheckerAppContract.TablaFoto.GALERIA_ID,foto.getIdGaleria());
        db.insert(AquariumCheckerAppContract.TablaFoto.NOMBRE_TABLA, null, valores);
        db.close();
    }

    public List<Foto> listFotos(){
        AquariumCheckerAppOpenHelper oh = new AquariumCheckerAppOpenHelper(appContext);
        List<Foto> l = new ArrayList<>();

        SQLiteDatabase db = oh.getReadableDatabase();
        String[] columnas = {AquariumCheckerAppContract.TablaFoto.COLUMNA_PATH,
                AquariumCheckerAppContract.TablaFoto.COLUMNA_DESCRIPCION,
                AquariumCheckerAppContract.TablaFoto.GALERIA_ID,
                AquariumCheckerAppContract.TablaFoto._ID,
        };


        Cursor cur = db.query(
                AquariumCheckerAppContract.TablaFoto.NOMBRE_TABLA,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        while (cur.moveToNext()) {
            Foto foto = new Foto(
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaFoto.COLUMNA_DESCRIPCION)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaFoto.COLUMNA_PATH)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaFoto._ID)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaFoto.GALERIA_ID))
            );
            l.add(foto);
        }
        return l;
    }

    public List<Foto> listaFotosPorGaleria(Galeria galeria){
        AquariumCheckerAppOpenHelper oh = new AquariumCheckerAppOpenHelper(appContext);
        List<Foto> l = new ArrayList<>();
        String[] selectionValues = {String.valueOf(galeria.getId())};

        SQLiteDatabase db = oh.getReadableDatabase();
        String[] columnas = {AquariumCheckerAppContract.TablaFoto.COLUMNA_PATH,
                AquariumCheckerAppContract.TablaFoto.COLUMNA_DESCRIPCION,
                AquariumCheckerAppContract.TablaFoto.GALERIA_ID,
                AquariumCheckerAppContract.TablaFoto._ID,
        };


        Cursor cur = db.query(
                AquariumCheckerAppContract.TablaFoto.NOMBRE_TABLA,
                columnas,
                AquariumCheckerAppContract.TablaFoto.GALERIA_ID + " = ?",
                selectionValues,
                null,
                null,
                null
        );

        while (cur.moveToNext()) {
            Foto foto = new Foto(
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaFoto.COLUMNA_DESCRIPCION)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaFoto.COLUMNA_PATH)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaFoto._ID)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaFoto.GALERIA_ID))
                   );
            l.add(foto);
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
