package ec.edu.epn.aquariumchecker.services;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ec.edu.epn.aquariumchecker.adapters.HistorialAdapter;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Historiales;
import ec.edu.epn.aquariumchecker.vo.Galeria;

/**
 * Created by angel on 6/7/2016.
 */

public class HistorialService {

    private List<Historiales> historiales;
    private Historiales historial;
    private HistorialAdapter adapter;


    public HistorialService() {
    }

    public void createHistorial(Historiales historial) {
        CrearhistorialAsyncTask task = new CrearhistorialAsyncTask();
        task.execute(historial);
        //this.fotos = fotos;
    }

    public void listHistorialesPorAcuario(Acuario acuario, List<Historiales> historiales, HistorialAdapter adapter) {
        ListarHistorialesByAcuarioAsyncTask task = new ListarHistorialesByAcuarioAsyncTask();
        task.execute(acuario);
        this.historiales = historiales;
        this.adapter = adapter;
    }


    public boolean removeHistorial(Historiales historial) {
        return true;
    }

    public class ListarHistorialesAsyncTask extends AsyncTask<Void, Void, List<Historiales>> {

        @Override
        protected List<Historiales> doInBackground(Void... params) {
            List<Historiales> historialesList = new ArrayList<Historiales>();
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/historial";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Historiales[] historialArray = restTemplate.getForObject(url, Historiales[].class);
            historialesList = Arrays.asList(historialArray);
            return historialesList;

        }

        @Override
        protected void onPostExecute(List<Historiales> historialesList) {
            super.onPostExecute(historialesList);
        }
    }

    public class ListarHistorialesByAcuarioAsyncTask extends AsyncTask<Acuario, Void, List<Historiales>> {

        @Override
        protected List<Historiales> doInBackground(Acuario... params) {
            List<Historiales> historialesList;
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/historial/acuario/" + params[0].getId();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Historiales[] historialArray = restTemplate.getForObject(url, Historiales[].class);
            historiales.addAll(Arrays.asList(historialArray));
            return historiales;

        }

        @Override
        protected void onPostExecute(List<Historiales> historialesList) {
            super.onPostExecute(historialesList);
            adapter.notifyDataSetChanged();
        }
    }

    public class CrearhistorialAsyncTask extends AsyncTask<Historiales, Void, String> {
        @Override
        protected String doInBackground(Historiales... params) {
            Historiales historial = params[0];
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/historial";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(
                    new StringHttpMessageConverter());
            String id = restTemplate.postForObject(url, historial, String.class);
            return id;
        }

        @Override
        protected void onPostExecute(String id) {
           /* FotoService service = new FotoService();
            for (Foto foto : fotos) {
                foto.setIdGaleria(Integer.valueOf(id));
                service.createFoto(foto);
            }*/
        }
    }

    public class EliminarAsyncTask extends AsyncTask<Historiales, Void, Void> {
        @Override
        protected Void doInBackground(Historiales... params) {
            Log.v("buscar", "2");
            Historiales historial = params[0];
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/historial/{id}";
            Log.v("buscar", "3");

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.delete(url, historial.getId());
            Log.v("Historial Eliminar", "mensaje");
            return null;
        }

    }
}
