package ec.edu.epn.aquariumchecker.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;

/**
 * Created by sebastian on 06/06/16.
 */
public class AcuarioService {
    private AcuarioVO acuario;
    private Context appContext;

    public AcuarioService(Context appContext) {
        this.appContext = appContext;
    }

    public AcuarioService() {
    }

    public void crearAcuario(AcuarioVO nuevoAcuario){
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
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_REDONDO,nuevoAcuario.getProfundidad_redondo());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_VOLUMEN,nuevoAcuario.getVolumen());
        db.insert(AquariumCheckerAppContract.TablaAcuario.NOMBRE_TABLA, null, valores);
        db.close();
    }

    public void editarAcuario(AcuarioVO acuarioEditar){
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
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_REDONDO,acuarioEditar.getProfundidad_redondo());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_VOLUMEN,acuarioEditar.getVolumen());
        db.update(AquariumCheckerAppContract.TablaAcuario.NOMBRE_TABLA,valores,
                AquariumCheckerAppContract.TablaAcuario._ID + " = ?",id);
        db.close();
    }

    public List<AcuarioVO> listAcuarios(){
        return new ArrayList<>();
    }

    public Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context appContext) {
        this.appContext = appContext;
    }

}
