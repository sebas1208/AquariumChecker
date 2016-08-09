package ec.edu.epn.aquariumchecker.services;

import android.os.AsyncTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import ec.edu.epn.aquariumchecker.adapters.PecesAdapter;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Peces;

/**
 * Created by sebas on 7/6/2016.
 */
public class PecesService {
    private List<Peces> peces;
    private PecesAdapter adapter;

    public PecesService() {
    }

    public void createPez(Peces pez){
        CrearPezAsyncTask task = new CrearPezAsyncTask();
        task.execute(pez);

    }
    public void eliminarPeces(Acuario acuario){
        EliminarPecesAsyncTask task = new EliminarPecesAsyncTask();
        task.execute(acuario);
    }


    public void listaPecesPorAcuario(Acuario acuario, List<Peces> peces, PecesAdapter adapter){
        ListarPecesByAcuarioAsyncTask task = new ListarPecesByAcuarioAsyncTask();
        task.execute(acuario);
        this.peces = peces;
        this.adapter = adapter;
    }

    public class ListarPecesByAcuarioAsyncTask extends AsyncTask<Acuario, Void, List<Peces>> {

        @Override
        protected List<Peces> doInBackground(Acuario... params) {
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/pez/acuario/" + params[0].getId();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Peces[] pecesArray = restTemplate.getForObject(url, Peces[].class);
            peces.addAll(Arrays.asList(pecesArray));
            return peces;

        }

        @Override
        protected void onPostExecute(List<Peces> pecesList) {
            super.onPostExecute(pecesList);
            adapter.notifyDataSetChanged();
        }
    }

    public class CrearPezAsyncTask extends AsyncTask<Peces, Void, String> {
        @Override
        protected String doInBackground(Peces... params) {
            Peces pez = params[0];
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/pez";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(
                    new StringHttpMessageConverter());
            String id = restTemplate.postForObject(url, pez, String.class);
            return id;
        }

        @Override
        protected void onPostExecute(String id) {

        }
    }
        public class EliminarPecesAsyncTask extends AsyncTask<Acuario, Void, Peces> {
            @Override
            protected Peces doInBackground(Acuario... params) {
                final String url = "http://acuariumrest-sebas1208.rhcloud.com/peces/acuario" + params[0].getId();

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(
                        new MappingJackson2HttpMessageConverter());
                restTemplate.getMessageConverters().add(
                        new StringHttpMessageConverter());
                restTemplate.delete(url, params[0]);
                return null;
            }

            @Override
            protected void onPostExecute(Peces peces) {
                super.onPostExecute(peces);
        }

    }

}
