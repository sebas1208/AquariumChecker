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
import ec.edu.epn.aquariumchecker.vo.Recordatorio;

/**
 * Created by angel on 6/7/2016.
 */
public class RecordatorioService{
        private Recordatorio recordatorio;
        private Context appContext;
        private AcuarioVO acuario;

    public RecordatorioService(Context appContext) {
            this.appContext = appContext;
        }

        public RecordatorioService() {
        }

        public void crearRecordatorio(Recordatorio nuevoRecordatorio){
            AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(appContext);
            SQLiteDatabase db = op.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(AquariumCheckerAppContract.TablaRecordatorio.COLUMNA_FECHA,nuevoRecordatorio.getFecha());
            valores.put(AquariumCheckerAppContract.TablaRecordatorio.COLUMNA_HORA,nuevoRecordatorio.getHora());
            valores.put(AquariumCheckerAppContract.TablaRecordatorio.COLUMNA_TIPORECORDATORIO,nuevoRecordatorio.getTipoCambio());
            db.insert(AquariumCheckerAppContract.TablaRecordatorio.NOMBRE_TABLA, null, valores);
            db.close();
        }

        public List<Recordatorio> listRecordatorios(){
            AquariumCheckerAppOpenHelper oh = new AquariumCheckerAppOpenHelper(appContext);
            List<Recordatorio> l = new ArrayList<>();
            String[] id = {Integer.toString(acuario.getId())};

            SQLiteDatabase db = oh.getReadableDatabase();
            String[] columnas = {AquariumCheckerAppContract.TablaRecordatorio.COLUMNA_FECHA,
                    AquariumCheckerAppContract.TablaRecordatorio.COLUMNA_HORA,
                    AquariumCheckerAppContract.TablaRecordatorio.COLUMNA_TIPORECORDATORIO,
                    AquariumCheckerAppContract.TablaRecordatorio.ACUARIO_ID,

            };


            Cursor cur = db.query(
                    AquariumCheckerAppContract.TablaRecordatorio.NOMBRE_TABLA,
                    columnas,
                    AquariumCheckerAppContract.TablaRecordatorio.ACUARIO_ID + " = ?",id,
                    null,
                    null,
                    null
            );

            while (cur.moveToNext()) {
                Recordatorio recordatorio = new Recordatorio(cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaRecordatorio.COLUMNA_FECHA)),
                        cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaRecordatorio.COLUMNA_HORA)),
                        cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaRecordatorio.COLUMNA_TIPORECORDATORIO)),
                        cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaRecordatorio.ACUARIO_ID)));
                l.add(recordatorio);
            }
            return l;
        }

        public void editarRecordatorio(Recordatorio editarRecordatorio){
            AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(appContext);
            SQLiteDatabase db = op.getWritableDatabase();
            String[] id = {Integer.toString(acuario.getId())};

            ContentValues valores = new ContentValues();
            valores.put(AquariumCheckerAppContract.TablaRecordatorio.COLUMNA_FECHA,editarRecordatorio.getFecha());
            valores.put(AquariumCheckerAppContract.TablaRecordatorio.COLUMNA_HORA,editarRecordatorio.getHora());
            valores.put(AquariumCheckerAppContract.TablaRecordatorio.COLUMNA_TIPORECORDATORIO,editarRecordatorio.getTipoCambio());

            db.update(AquariumCheckerAppContract.TablaRecordatorio.NOMBRE_TABLA,valores,
                    AquariumCheckerAppContract.TablaRecordatorio.ACUARIO_ID + " = ?",id);
            db.close();
        }

        public Context getAppContext() {
            return appContext;
        }

        public void setAppContext(Context appContext) {
            this.appContext = appContext;
        }
}
