package ec.edu.epn.aquariumchecker.services;

import android.os.AsyncTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import ec.edu.epn.aquariumchecker.adapters.PlantasAdapter;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Planta;

/**
 * Created by natyd on 7/6/2016.
 */
public class PlantasService {
    private List<Planta> plantas;
    private PlantasAdapter adapter;

    public PlantasService() {
    }

    public void createPlanta(Planta planta){
        CrearPlantaAsyncTask task = new CrearPlantaAsyncTask();
        task.execute(planta);
    }

    public void eliminarPlanta(Acuario acuario){
        EliminarPlantaAsyncTask task = new EliminarPlantaAsyncTask();
        task.execute(acuario);
    }

    public void listaPlantasPorAcuario(Acuario acuario, List<Planta> plantas, PlantasAdapter adapter){
        ListarPlantasByAcuarioAsyncTask task = new ListarPlantasByAcuarioAsyncTask();
        task.execute(acuario);
        this.plantas = plantas;
        this.adapter = adapter;
    }



    public class ListarPlantasByAcuarioAsyncTask extends AsyncTask<Acuario, Void, List<Planta>> {

        @Override
        protected List<Planta> doInBackground(Acuario... params) {
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/plantas/acuario/" + params[0].getId();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Planta[] plantasArray = restTemplate.getForObject(url, Planta[].class);
            plantas.addAll(Arrays.asList(plantasArray));
            return plantas;

        }

        @Override
        protected void onPostExecute(List<Planta> plantasList) {
            super.onPostExecute(plantasList);
            adapter.notifyDataSetChanged();
        }
    }

    public class CrearPlantaAsyncTask extends AsyncTask<Planta, Void, String> {
        @Override
        protected String doInBackground(Planta... params) {
            Planta planta = params[0];
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/plantas";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(
                    new StringHttpMessageConverter());
            String id = restTemplate.postForObject(url, planta, String.class);
            return id;
        }

        @Override
        protected void onPostExecute(String id) {

        }
    }
    public class EliminarPlantaAsyncTask extends AsyncTask<Acuario, Void, Planta> {
        @Override
        protected Planta doInBackground(Acuario... params) {
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/plantas/acuario" + params[0].getId();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(
                    new StringHttpMessageConverter());
            restTemplate.delete(url, params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Planta planta) {
            super.onPostExecute(planta);
        }

    }

}

