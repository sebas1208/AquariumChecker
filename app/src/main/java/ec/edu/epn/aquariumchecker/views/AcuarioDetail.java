package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.vo.Acuario;

public class AcuarioDetail extends AppCompatActivity {

    private Acuario acuario;
    private Toolbar toolbar;
    private TextView hola;
    private ListView Opciones;
    String[]menuOpciones = {"Galerias", "Plantas", "Peces","Historiales","Recordatorios"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAcuario();
        initComponents();
    }

    private void getAcuario(){
        acuario = (Acuario)getIntent().getSerializableExtra("varAcuario");
        if(acuario == null){
            acuario = new Acuario();
        }
    }

    private void initComponents(){
        setContentView(R.layout.activity_acuario_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(acuario.getNombre());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acuario_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_edit:
                editAcuario();
                return true;
            case R.id.action_delete:
                deleteAcuario();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void editAcuario(){
        Intent i = new Intent(this, EditarAcuario.class);
        i.putExtra("varAcuario", acuario);
        startActivity(i);
    }

    private void deleteAcuario(){

    }

    public void abrirGalerias(View view){
        Intent i = new Intent(this,MisGalerias.class);
        startActivity(i);
    }

    public void abrirPeces(View view){
        Intent i = new Intent(this, MisPecesAcuarios.class);
        startActivity(i);
    }

    public void abrirPlantas(View view){
        Intent i = new Intent(this, MisPlantasAcuario.class);
        startActivity(i);
    }

    public void abrirRecordatorios (View view){
        Intent i = new Intent (this,ListRecordatorios.class);
        startActivity(i);
    }

    public void abrirHistoriales (View view){
        Intent i = new Intent (this,ListHistorial.class);
        startActivity(i);
    }

}
