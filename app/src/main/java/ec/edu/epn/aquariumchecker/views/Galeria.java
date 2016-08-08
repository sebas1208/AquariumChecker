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
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.GaleriaAdapter;
import ec.edu.epn.aquariumchecker.services.GaleriaService;
import ec.edu.epn.aquariumchecker.vo.Acuario;

public class Galeria extends AppCompatActivity {
    private List<ec.edu.epn.aquariumchecker.vo.Galeria> galeriasList = new ArrayList<>();
    private Acuario acuarioSeleccionado;
    private ListView galerias;
    private GaleriaAdapter adapter;
    static final int NUEVA_GALERIA_REQUEST = 1;
    static final int MOSTRAR_GALERIA_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        obtenerAcuarioSeleccionado();
        obtenerGaleriasPorAcuario();
    }

    @Override
    protected void onRestart() {
        super.onRestart();initComponents();
        obtenerAcuarioSeleccionado();
        obtenerGaleriasPorAcuario();
    }

    private void initComponents(){
        setContentView(R.layout.galeria_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        galerias = (ListView) findViewById(R.id.galeria_list);
    }

    private void obtenerAcuarioSeleccionado(){
        acuarioSeleccionado = (Acuario)getIntent().getSerializableExtra("acuarioSeleccionado");
        if(acuarioSeleccionado == null){
            acuarioSeleccionado = new Acuario();
        }
    }

    private void obtenerGaleriasPorAcuario(){
        ListarGalerias listarGalerias = new ListarGalerias();
        listarGalerias.execute();
    }

    public void abrirNuevaGaleria(View view){
        Intent i = new Intent(this,NuevaGaleria.class);
        i.putExtra("acuarioSeleccionado",acuarioSeleccionado);
        startActivityForResult(i,NUEVA_GALERIA_REQUEST);
    }

    public void abrirFotos(View view){
        int position = galerias.getPositionForView((LinearLayout)view.getParent());
        Intent i = new Intent(this, Fotos.class);
        i.putExtra("galeriaSeleccionada",galeriasList.get(position));
        startActivityForResult(i, MOSTRAR_GALERIA_REQUEST);
    }

    public void eliminarGaleria(View view){
        int position = galerias.getPositionForView((LinearLayout)view.getParent());
        GaleriaService galeriaService = new GaleriaService(getApplicationContext());
        galeriaService.removeGaleria(galeriasList.get(position));

        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"Se elimino la Galeria",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NUEVA_GALERIA_REQUEST) {
            if (resultCode == RESULT_OK) {
                obtenerAcuarioSeleccionado();
                obtenerGaleriasPorAcuario();
            }
        }
    }

    public class ListarGalerias extends AsyncTask<Void, Void, List<Galeria>> {

        @Override
        protected List<Galeria> doInBackground(Void... params) {
            Log.v("buscar", "2");
            List<Galeria> galeriasList = new ArrayList<Galeria>();
            final String url = "http://acuariumrest-sebas1208.rhcloud.com/galeria";
            Log.v("buscar","3");

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Galeria[] galeriaArray = restTemplate.getForObject(url, Galeria[].class);
            galeriasList = Arrays.asList(galeriaArray);
            Log.v("buscar","4 son" + galeriasList.size());
            return galeriasList;

        }

        @Override
        protected void onPostExecute(List<Galeria> galeriasList) {
            super.onPostExecute(galeriasList);

            GaleriaAdapter adapter = new GaleriaAdapter(getApplicationContext(), galeriasList);
            galerias.setAdapter(adapter);
            galerias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Acuario acuario = (Acuario) parent.getItemAtPosition(position);
                    /*Toast.makeText(MainLibro.this,"Libro: "+l,Toast.LENGTH_SHORT).show();*/

                    Intent i = new Intent(Galeria.this, Fotos.class);
                    i.putExtra("galeriaSeleccionada", acuario);
                    startActivity(i);
                    startActivityForResult(i, MOSTRAR_GALERIA_REQUEST);
                }
            });
        }
    }

}
