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

/**
 * Created by sebastian on 06/06/16.
 */
public class AcuarioService {
    private Acuario acuario;
    private Context appContext;

    public AcuarioService(Context appContext) {
        this.appContext = appContext;
    }

    public AcuarioService() {
    }

    public void crearAcuario(Acuario nuevoAcuario){
        AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(appContext);
        SQLiteDatabase db = op.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_NOMBRE,nuevoAcuario.getNombre());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_TIPOAGUA,nuevoAcuario.getTipo_agua());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_FORMA,nuevoAcuario.getForma());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_ALTO,nuevoAcuario.getAlto());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_ANCHO,nuevoAcuario.getAncho());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_MEDIDAS,nuevoAcuario.getProfundidad_rectangular());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_DIAMETRO,nuevoAcuario.getDiametro());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_REDONDO,nuevoAcuario.getProfundidad_cilindrica());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_VOLUMEN,nuevoAcuario.getVolumen());
        db.insert(AquariumCheckerAppContract.TablaAcuario.NOMBRE_TABLA, null, valores);
        db.close();
    }

    public List<Acuario> listAcuarios(){
        AquariumCheckerAppOpenHelper oh = new AquariumCheckerAppOpenHelper(appContext);
        List<Acuario> l = new ArrayList<>();

        SQLiteDatabase db = oh.getReadableDatabase();
        String[] columnas = {AquariumCheckerAppContract.TablaAcuario.COLUMNA_NOMBRE,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_TIPOAGUA,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_FORMA,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_ALTO,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_ANCHO,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_MEDIDAS,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_DIAMETRO,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_REDONDO,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_VOLUMEN,
                AquariumCheckerAppContract.TablaAcuario._ID
        };


        Cursor cur = db.query(
                AquariumCheckerAppContract.TablaAcuario.NOMBRE_TABLA,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        while (cur.moveToNext()) {
            Acuario acuario = new Acuario(cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_NOMBRE)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_TIPOAGUA)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_FORMA)),
                    cur.getDouble(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_ALTO)),
                    cur.getDouble(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_ANCHO)),
                    cur.getDouble(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_MEDIDAS)),
                    cur.getDouble(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_DIAMETRO)),
                    cur.getDouble(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_REDONDO)),
                    cur.getDouble(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_VOLUMEN)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario._ID)));
            l.add(acuario);
        }
        db.close();
        return l;
    }

    public void editarAcuario(Acuario acuarioEditar){
        AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(appContext);
        SQLiteDatabase db = op.getWritableDatabase();
        String[] id = {Integer.toString(acuarioEditar.getId())};

        ContentValues valores = new ContentValues();
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_NOMBRE,acuarioEditar.getNombre());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_TIPOAGUA,acuarioEditar.getTipo_agua());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_FORMA,acuarioEditar.getForma());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_ALTO,acuarioEditar.getAlto());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_ANCHO,acuarioEditar.getAncho());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_MEDIDAS,acuarioEditar.getProfundidad_rectangular());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_DIAMETRO,acuarioEditar.getDiametro());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_REDONDO,acuarioEditar.getProfundidad_cilindrica());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_VOLUMEN,acuarioEditar.getVolumen());
        db.update(AquariumCheckerAppContract.TablaAcuario.NOMBRE_TABLA,valores,
                AquariumCheckerAppContract.TablaAcuario._ID + " = ?",id);
        db.close();
    }

    public Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context appContext) {
        this.appContext = appContext;
    }

}
