package ec.edu.epn.aquariumchecker.services;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.views.MisAcuarios;
import ec.edu.epn.aquariumchecker.views.NuevoAcuario;
import ec.edu.epn.aquariumchecker.vo.Acuario;

/**
 * Created by sebastian on 06/06/16.
 */
public class AcuarioService {

    final String url = "http://acuariumrest-sebas1208.rhcloud.com/acuario";

    private RecyclerView.Adapter adapter;

    private Context appContext;

    public AcuarioService(Context appContext) {
        this.appContext = appContext;
    }

    public AcuarioService() {
    }

    public void listAcuarios(List<Acuario> acuarios, RecyclerView.Adapter adapter){
        ListarAcuariosAsyncTask task = new ListarAcuariosAsyncTask();
        task.execute(acuarios);
        this.adapter = adapter;
    }

    public void crearAcuario(Acuario acuario){
        CrearAcuarioAsyncTask task = new CrearAcuarioAsyncTask();
        task.execute(acuario);
    }

    public class CrearAcuarioAsyncTask extends AsyncTask<Acuario, Void, Void> {

        @Override
        protected Void doInBackground(Acuario... params) {
            Acuario acuario = params [0];

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(
                    new StringHttpMessageConverter());
            restTemplate.postForEntity(url, acuario, String.class);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public class ListarAcuariosAsyncTask extends AsyncTask<List<Acuario>, Void, List<Acuario>> {

        @Override
        protected List<Acuario> doInBackground(List<Acuario>... acuarios) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Acuario[] acuarioArray = restTemplate.getForObject(url, Acuario[].class);
            acuarios[0].addAll(Arrays.asList(acuarioArray));
            return acuarios[0];
        }

        @Override
        protected void onPostExecute(List<Acuario> acuarios) {
            super.onPostExecute(acuarios);
            adapter.notifyDataSetChanged();
        }
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
