package ec.edu.epn.aquariumchecker.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;
import ec.edu.epn.aquariumchecker.vo.Historiales;

/**
 * Created by angel on 6/7/2016.
 */
public class HistorialService {

        private Historiales historial;
        private Context appContext;

        public HistorialService(Context appContext) {
        this.appContext = appContext;
    }

        public HistorialService() {
    }

    public long crearHistorial(Historiales nuevoHistorial){
        AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(appContext);
        SQLiteDatabase db = op.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(AquariumCheckerAppContract.TablaHistorial.COLUMNA_FECHA,nuevoHistorial.getFecha());
        valores.put(AquariumCheckerAppContract.TablaHistorial.COLUMNA_PH,nuevoHistorial.getPh());
        valores.put(AquariumCheckerAppContract.TablaHistorial.COLUMNA_KH,nuevoHistorial.getKh());
        valores.put(AquariumCheckerAppContract.TablaHistorial.COLUMNA_GH,nuevoHistorial.getGh());
        valores.put(AquariumCheckerAppContract.TablaHistorial.COLUMNA_CO2,nuevoHistorial.getCo2());
        valores.put(AquariumCheckerAppContract.TablaHistorial.COLUMNA_ILUMINACION,nuevoHistorial.getIluminacion());
        valores.put(AquariumCheckerAppContract.TablaHistorial.COLUMNA_OBSERVACIONES,nuevoHistorial.getObservaciones());
        valores.put(AquariumCheckerAppContract.TablaHistorial.ACUARIO_ID,nuevoHistorial.getAcuario());


        long idinsert = db.insert(AquariumCheckerAppContract.TablaHistorial.NOMBRE_TABLA, null, valores);
        db.close();
        return idinsert;
    }

    public List<Historiales> listHistoriales(AcuarioVO acuario){
        AquariumCheckerAppOpenHelper oh = new AquariumCheckerAppOpenHelper(appContext);
        List<Historiales> l = new ArrayList<>();
        String[] selectionValues = {String.valueOf(acuario.getId())};

        SQLiteDatabase db = oh.getReadableDatabase();
        String[] columnas = {AquariumCheckerAppContract.TablaHistorial.COLUMNA_FECHA,
                AquariumCheckerAppContract.TablaHistorial.COLUMNA_PH,
                AquariumCheckerAppContract.TablaHistorial.COLUMNA_KH,
                AquariumCheckerAppContract.TablaHistorial.COLUMNA_GH,
                AquariumCheckerAppContract.TablaHistorial.COLUMNA_CO2,
                AquariumCheckerAppContract.TablaHistorial.COLUMNA_ILUMINACION,
                AquariumCheckerAppContract.TablaHistorial.COLUMNA_OBSERVACIONES,
                AquariumCheckerAppContract.TablaHistorial.ACUARIO_ID,
                AquariumCheckerAppContract.TablaHistorial._ID,

        };


        Cursor cur = db.query(
                AquariumCheckerAppContract.TablaHistorial.NOMBRE_TABLA,
                columnas,
                AquariumCheckerAppContract.TablaHistorial.ACUARIO_ID + " = ?",
                selectionValues,
                null,
                null,
                null
        );

        while (cur.moveToNext()) {
            Historiales historial = new Historiales(cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaHistorial.COLUMNA_FECHA)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaHistorial.COLUMNA_PH)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaHistorial.COLUMNA_KH)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaHistorial.COLUMNA_GH)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaHistorial.COLUMNA_CO2)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaHistorial.COLUMNA_ILUMINACION)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaHistorial.COLUMNA_OBSERVACIONES)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaHistorial._ID)));
            l.add(historial);
        }
        db.close();
        return l;
    }

    public void editHistorial(Historiales historialEditar){
       /* AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(appContext);
        SQLiteDatabase db = op.getWritableDatabase();
        String[] id = {historialEditar.getAcuario()};

        ContentValues valores = new ContentValues();
        valores.put(AquariumCheckerAppContract.TablaHistorial.COLUMNA_FECHA,historialEditar.getFecha());
        valores.put(AquariumCheckerAppContract.TablaHistorial.COLUMNA_PH,historialEditar.getPh());
        valores.put(AquariumCheckerAppContract.TablaHistorial.COLUMNA_KH,historialEditar.getKh());
        valores.put(AquariumCheckerAppContract.TablaHistorial.COLUMNA_GH,historialEditar.getGh());
        valores.put(AquariumCheckerAppContract.TablaHistorial.COLUMNA_CO2,historialEditar.getCo2());
        valores.put(AquariumCheckerAppContract.TablaHistorial.COLUMNA_ILUMINACION,historialEditar.getIluminacion());
        valores.put(AquariumCheckerAppContract.TablaHistorial.COLUMNA_OBSERVACIONES,historialEditar.getObservaciones());
        db.update(AquariumCheckerAppContract.TablaHistorial.NOMBRE_TABLA,valores,
                AquariumCheckerAppContract.TablaHistorial._ID + " = ?",id);
        db.close();*/
    }

    public Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context appContext) {
        this.appContext = appContext;
    }

}
