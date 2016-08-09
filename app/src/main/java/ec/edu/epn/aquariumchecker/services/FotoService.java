package ec.edu.epn.aquariumchecker.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ec.edu.epn.aquariumchecker.adapters.FotosAdapter;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Foto;
import ec.edu.epn.aquariumchecker.vo.Galeria;

/**
 * Created by sebas on 7/6/2016.
 */
public class FotoService {

    private List<Foto> fotos;
    private FotosAdapter adapter;

    public FotoService() {
    }

    public void  createFoto(Foto foto){
        CrearFotoAsyncTask task = new CrearFotoAsyncTask();
        task.execute(foto);
    }

    public void listarFotosPorGaleria(Galeria galeria, List<Foto> fotos, FotosAdapter adapter){
        this.fotos = fotos;
        this.adapter = adapter;
        ListarFotoPorGaleriaAsyncTask task = new ListarFotoPorGaleriaAsyncTask();
        task.execute(galeria);
    }

    public class CrearFotoAsyncTask extends AsyncTask<Foto, Void, Void> {
        @Override
        protected Void doInBackground(Foto... params) {
            Foto foto = params [0];
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/foto";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(
                    new StringHttpMessageConverter());
            restTemplate.postForObject(url, foto, String.class);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public class ListarFotoPorGaleriaAsyncTask extends AsyncTask<Galeria, Void, List<Foto>> {

        @Override
        protected List<Foto> doInBackground(Galeria... params) {
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/foto/galeria/" + params[0].getId();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            try{
                Foto[] fotoArray = restTemplate.getForObject(url, Foto[].class);
                fotos.addAll(Arrays.asList(fotoArray));
            }
            catch (HttpClientErrorException e) {
                Log.v("Error", "Conexion de red");
            }
            return fotos;
        }

        @Override
        protected void onPostExecute(List<Foto> fotos) {
            adapter.notifyDataSetChanged();
        }
    }


    public boolean removeGaleria(){

        return false;
    }

}
