package ec.edu.epn.aquariumchecker.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ec.edu.epn.aquariumchecker.adapters.GaleriaAdapter;
import ec.edu.epn.aquariumchecker.adapters.RecordatoriosAdapter;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Foto;
import ec.edu.epn.aquariumchecker.vo.Galeria;
import ec.edu.epn.aquariumchecker.vo.Recordatorio;

/**
 * Created by angel on 7/6/2016.
 */
public class RecordatorioService {
    private Recordatorio recordatorio;
    private List<Recordatorio> recordatorios;
    private RecordatoriosAdapter adapter;


    public RecordatorioService() {
    }

    public void createRecordatorio(Recordatorio recordatorio) {
        CrearRecordatorioAsyncTask task = new CrearRecordatorioAsyncTask();
        task.execute(recordatorio);

    }

    public void listRecordatoriosPorAcuario(Acuario acuario, List<Recordatorio> recordatorios, RecordatoriosAdapter adapter) {
        this.recordatorios = recordatorios;
        this.adapter = adapter;
        ListarRecordatoriosByAcuarioAsyncTask task = new ListarRecordatoriosByAcuarioAsyncTask();
        task.execute(acuario);
    }


    public void removeRecordatorio(Recordatorio recordatorio) {
        EliminarAsyncTask task = new EliminarAsyncTask();
        task.execute(recordatorio);
    }


    public class ListarRecordatoriosByAcuarioAsyncTask extends AsyncTask<Acuario, Void, List<Recordatorio>> {

        @Override
        protected List<Recordatorio> doInBackground(Acuario... params) {
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

    }

    public class EliminarAsyncTask extends AsyncTask<Recordatorio, Void, Void> {
        @Override
        protected Void doInBackground(Recordatorio... params) {
            Recordatorio recordatorio = params[0];
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/recordatorio/{id}";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.delete(url, recordatorio.getId());
            return null;
        }

    }
}
