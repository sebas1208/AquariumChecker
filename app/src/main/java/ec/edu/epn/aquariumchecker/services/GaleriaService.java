package ec.edu.epn.aquariumchecker.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ec.edu.epn.aquariumchecker.adapters.GaleriaAdapter;
import ec.edu.epn.aquariumchecker.adapters.GaleriaRvAdapter;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Foto;
import ec.edu.epn.aquariumchecker.vo.Galeria;

/**
 * Created by sebas on 7/6/2016.
 */
public class GaleriaService {

    private Context context;
    private List<Galeria> galerias;
    private GaleriaRvAdapter adapter;
    private List<Foto> fotos;

    public GaleriaService(Context context) {
        this.context = context;
    }

    public GaleriaService() {
    }

    public void createGaleria(Galeria galeria, List<Foto> fotos) {
        this.fotos = fotos;
        CrearGaleriaAsyncTask task = new CrearGaleriaAsyncTask();
        task.execute(galeria);
    }

    public void listGaleriasPorAcuario(Acuario acuario, List<Galeria> galerias, GaleriaRvAdapter adapter) {
        this.galerias = galerias;
        this.adapter = adapter;
        ListarGaleriasByAcuarioAsyncTask task = new ListarGaleriasByAcuarioAsyncTask();
        task.execute(acuario);
    }


    public void removeGaleria(Galeria galeria) {
        EliminarGaleriaAsyncTask task = new EliminarGaleriaAsyncTask();
        task.execute(galeria);
    }

    public class ListarGaleriasAsyncTask extends AsyncTask<Void, Void, List<Galeria>> {

        @Override
        protected List<Galeria> doInBackground(Void... params) {
            List<Galeria> galeriasList = new ArrayList<Galeria>();
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/galeria";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Galeria[] galeriaArray = restTemplate.getForObject(url, Galeria[].class);
            galeriasList = Arrays.asList(galeriaArray);
            return galeriasList;

        }

        @Override
        protected void onPostExecute(List<Galeria> galeriasList) {
            super.onPostExecute(galeriasList);
        }
    }

    public class ListarGaleriasByAcuarioAsyncTask extends AsyncTask<Acuario, Void, List<Galeria>> {
        private Exception e;

        @Override
        protected List<Galeria> doInBackground(Acuario... params) {

            final String url = "http://acuariumrest-sebas1208.rhcloud.com/galeria/acuario/" + params[0].getId();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            try {
                Galeria[] galeriaArray = restTemplate.getForObject(url, Galeria[].class);
                galerias.addAll(Arrays.asList(galeriaArray));
            }catch (HttpClientErrorException e){
                this.e = e;
            }
            return galerias;

        }

        @Override
        protected void onPostExecute(List<Galeria> galeriasList) {
            if(e == null) {
                adapter.notifyDataSetChanged();
            }else{
                Toast.makeText(context,"Error en la red, intentalo de nuevo",Toast.LENGTH_SHORT).show();
            }

        }
    }

    public class CrearGaleriaAsyncTask extends AsyncTask<Galeria, Void, String> {
        @Override
        protected String doInBackground(Galeria... params) {
            Galeria galeria = params[0];
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/galeria";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(
                    new StringHttpMessageConverter());
            String id = restTemplate.postForObject(url, galeria, String.class);
            return id;
        }

        @Override
        protected void onPostExecute(String id) {
            FotoService service = new FotoService();
            for (Foto foto : fotos) {
                foto.setIdGaleria(Integer.valueOf(id));
                service.createFoto(foto);
            }
        }
    }

    public class EliminarGaleriaAsyncTask extends AsyncTask<Galeria, Void, Void> {
        @Override
        protected Void doInBackground(Galeria... params) {
            Galeria galeria = params[0];
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/galeria/{id}";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.delete(url, galeria.getId());
            return null;
        }

    }
}
