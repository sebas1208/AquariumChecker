package ec.edu.epn.aquariumchecker.services;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import ec.edu.epn.aquariumchecker.vo.Acuario;

/**
 * Created by sebastian on 06/06/16.
 */
public class AcuarioService {

    final String url = "http://acuariumrest-sebas1208.rhcloud.com/acuario";

    private RecyclerView.Adapter adapter;

    public AcuarioService() {
    }

    public void listAcuarios(List<Acuario> acuarios, RecyclerView.Adapter adapter){
        ListarAcuariosAsyncTask task = new ListarAcuariosAsyncTask();
        this.adapter = adapter;
        task.execute(acuarios);
    }

    public void crearAcuario(Acuario acuario){
        CrearAcuarioAsyncTask task = new CrearAcuarioAsyncTask();
        task.execute(acuario);
    }

    public void editarAcuario(Acuario acuario){
        EditarAcuarioAsyncTask task = new EditarAcuarioAsyncTask();
        task.execute(acuario);
    }

    public void eliminarAcuario(Acuario acuario){
        ElimanarAcuarioAsyncTask task = new ElimanarAcuarioAsyncTask();
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

    public class EditarAcuarioAsyncTask extends AsyncTask<Acuario, Void, Acuario> {
        @Override
        protected Acuario doInBackground(Acuario... params) {
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/acuario/" + params[0].getId();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(
                    new StringHttpMessageConverter());
            restTemplate.put(url, params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Acuario acuario) {
            super.onPostExecute(acuario);
        }
    }

    public class ElimanarAcuarioAsyncTask extends AsyncTask<Acuario, Void, Acuario> {
        @Override
        protected Acuario doInBackground(Acuario... params) {
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/historial/" + params[0].getId();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(
                    new StringHttpMessageConverter());
            restTemplate.delete(url, params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Acuario acuario) {
            super.onPostExecute(acuario);
        }
    }
}
