package ec.edu.epn.aquariumchecker.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ec.edu.epn.aquariumchecker.adapters.GaleriaAdapter;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Galeria;

/**
 * Created by sebas on 7/6/2016.
 */
public class GaleriaService {

    private Context appContext;

    private List<Galeria> galerias;

    private GaleriaAdapter adapter;

    public GaleriaService(Context appContext) {
        this.appContext = appContext;
    }

    public GaleriaService() {
    }

    public void createGaleria(Galeria galeria){
        CrearAsyncTask task = new CrearAsyncTask();
        task.execute(galeria);
    }

    public void listGaleriasPorAcuario(Acuario acuario, List<Galeria> galerias, GaleriaAdapter adapter){
        ListarGaleriasByAcuarioAsyncTask task = new ListarGaleriasByAcuarioAsyncTask();
        task.execute(acuario);
        this.galerias = galerias;
        this.adapter = adapter;
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
        db.close();
        return l;
    }


    public boolean removeGaleria(Galeria galeria){
        AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(appContext);
        SQLiteDatabase db = op.getWritableDatabase();
        String[] id = {String.valueOf(galeria.getId())};

        db.delete(AquariumCheckerAppContract.TablaGaleria.NOMBRE_TABLA,
                AquariumCheckerAppContract.TablaGaleria._ID + " = ?",
                id);
        db.close();
        return true;
    }

    public class ListarGaleriasAsyncTask extends AsyncTask<Void, Void, List<Galeria>> {

        @Override
        protected List<Galeria> doInBackground(Void... params) {
            Log.v("buscar", "2");
            List<Galeria> galeriasList = new ArrayList<Galeria>();
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/galeria";
            Log.v("buscar","3");

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Galeria[] galeriaArray = restTemplate.getForObject(url, Galeria[].class);
            galeriasList = Arrays.asList(galeriaArray);
            Log.v("buscar","4 son" + galeriasList.size());
            return galeriasList;

        }

        @Override
        protected void onPostExecute(List<Galeria> galeriasList) {
            super.onPostExecute(galeriasList);
        }
    }

    public class ListarGaleriasByAcuarioAsyncTask extends AsyncTask<Acuario, Void, List<Galeria>> {

        @Override
        protected List<Galeria> doInBackground(Acuario... params) {
            List<Galeria> galeriasList;
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/galeria/acuario/" + params[0].getId();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Galeria[] galeriaArray = restTemplate.getForObject(url, Galeria[].class);
            galerias.addAll(Arrays.asList(galeriaArray));
            return galerias;

        }

        @Override
        protected void onPostExecute(List<Galeria> galeriasList) {
            super.onPostExecute(galeriasList);
            adapter.notifyDataSetChanged();
        }
    }

    public class CrearAsyncTask extends AsyncTask<Galeria, Void, Void> {
        @Override
        protected Void doInBackground(Galeria... params) {
            Galeria galeria = params [0];
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/galeria";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.postForObject(url, galeria, Galeria.class);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public class EliminarAsyncTask extends AsyncTask<Galeria, Void, Void> {
        @Override
        protected Void doInBackground(Galeria... params) {
            Log.v("buscar", "2");
            Galeria galeria = params[0];
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/galeria/{id}";
            Log.v("buscar", "3");

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.delete(url, galeria.getId());
            Log.v("Galeria Eliminar", "mensaje");
            return null;
        }

    }
}
