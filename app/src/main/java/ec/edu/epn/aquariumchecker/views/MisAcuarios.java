package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.services.AcuarioService;
import ec.edu.epn.aquariumchecker.vo.Acuario;

public class MisAcuarios extends AppCompatActivity {

    private ListView misAcuarios;
    private List<Acuario> acuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        //getAcuarioList();
        ListarAcuarios listarAcuarios = new ListarAcuarios();
        listarAcuarios.execute();
    }

    private void initComponents(){
        setContentView(R.layout.acuarios_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        misAcuarios = (ListView) findViewById(R.id.mis_acuarios_list);
    }

    private void getAcuarioList(){
        AcuarioService service = new AcuarioService(getApplicationContext());
        //acuarios.addAll(service.listAcuarios());
    }

    public void abrirNuevoAcuario(View view) {
        Intent i = new Intent(this, NuevoAcuario.class);
        startActivity(i);
    }

    public void abrirAcuario(View v) {
        int position = misAcuarios.getPositionForView((LinearLayout)v.getParent());
        Intent i = new Intent(this, EditarAcuario.class);
        i.putExtra("acuarioEditar",acuarios.get(position));
        startActivity(i);
    }

    public class ListarAcuarios extends AsyncTask <Void, Void, List<Acuario>>{

        @Override
        protected List<Acuario> doInBackground(Void... params) {
            Log.v("buscar", "2");
            List<Acuario> acuarios = new ArrayList<Acuario>();
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/acuario";
            Log.v("buscar","3");

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Acuario[] acuarioArray = restTemplate.getForObject(url, Acuario[].class);
            acuarios = Arrays.asList(acuarioArray);
            Log.v("buscar","4 son" + acuarios.size());
            return acuarios;

        }

        @Override
        protected void onPostExecute(List<Acuario> acuarios) {
            super.onPostExecute(acuarios);

            MisAcuariosAdapter adapter = new MisAcuariosAdapter(getApplicationContext(), acuarios);
            misAcuarios.setAdapter(adapter);
            misAcuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Acuario acuario = (Acuario) parent.getItemAtPosition(position);
                    /*Toast.makeText(MainLibro.this,"Libro: "+l,Toast.LENGTH_SHORT).show();*/

                    Intent i = new Intent(MisAcuarios.this, EditarAcuario.class);
                    i.putExtra("varAcuario", acuario);
                    startActivity(i);
                }
            });
        }
    }

}
