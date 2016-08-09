package ec.edu.epn.aquariumchecker.services;

import android.os.AsyncTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ec.edu.epn.aquariumchecker.adapters.RecordatoriosAdapter;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Recordatorio;

/**
 * Created by angel on 7/6/2016.
 */
public class RecordatorioService {
    private List<Recordatorio> recordatorios;
    private RecordatoriosAdapter adapter;


    public RecordatorioService() {
    }

    public void createRecordatorio(Recordatorio recordatorio) {
        CrearRecordatorioAsyncTask task = new CrearRecordatorioAsyncTask();
        task.execute(recordatorio);

    }

    public void listRecordatoriosPorAcuario(Acuario acuario, List<Recordatorio> recordatorios, RecordatoriosAdapter adapter) {
        ListarRecordatoriosByAcuarioAsyncTask task = new ListarRecordatoriosByAcuarioAsyncTask();
        task.execute(acuario);
        this.recordatorios = recordatorios;
        this.adapter = adapter;
    }


    public boolean removeRecordatorio(Recordatorio recordatorio) {
        return true;
    }

    public class ListarRecordatoriosAsyncTask extends AsyncTask<Void, Void, List<Recordatorio>> {

        @Override
        protected List<Recordatorio> doInBackground(Void... params) {
            List<Recordatorio> galeriasList = new ArrayList<Recordatorio>();
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/recordatorio";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Recordatorio[] galeriaArray = restTemplate.getForObject(url, Recordatorio[].class);
            galeriasList = Arrays.asList(galeriaArray);
            return galeriasList;

        }

        @Override
        protected void onPostExecute(List<Recordatorio> galeriasList) {
            super.onPostExecute(galeriasList);
        }
    }

    public class ListarRecordatoriosByAcuarioAsyncTask extends AsyncTask<Acuario, Void, List<Recordatorio>> {

        @Override
        protected List<Recordatorio> doInBackground(Acuario... params) {
            List<Recordatorio> recordatoriosList;
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/recordatorio/acuario/" + params[0].getId();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Recordatorio[] recordatorioArray = restTemplate.getForObject(url, Recordatorio[].class);
            recordatorios.addAll(Arrays.asList(recordatorioArray));
            return recordatorios;

        }

        @Override
        protected void onPostExecute(List<Recordatorio> recordatoriosList) {
            super.onPostExecute(recordatoriosList);
            adapter.notifyDataSetChanged();
        }
    }

    public class CrearRecordatorioAsyncTask extends AsyncTask<Recordatorio, Void, String> {
        @Override
        protected String doInBackground(Recordatorio... params) {
            Recordatorio recordatorio = params[0];
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/recordatorio";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(
                    new StringHttpMessageConverter());
            String id = restTemplate.postForObject(url, recordatorio, String.class);
            return id;
        }

        @Override
        protected void onPostExecute(String id) {
        }
    }

    public class EliminarAsyncTask extends AsyncTask<Recordatorio, Void, Void> {
        @Override
        protected Void doInBackground(Recordatorio... params) {
            Recordatorio recordatorio = params[0];
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/recordatorio/"+ params[0].getId();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(
                    new StringHttpMessageConverter());
            restTemplate.delete(url, params[0]);
            return null;
        }

    }
}
