package ec.edu.epn.aquariumchecker.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ec.edu.epn.aquariumchecker.R;

public class EditarAcuario extends AppCompatActivity {

    private Spinner cmbtiposAguaEdit;
    private Spinner cmbtiposFormaEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acuario_activity_editar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cmbtiposAguaEdit = (Spinner) findViewById(R.id.cmbTipoAguaEdit);
        cmbtiposFormaEdit = (Spinner) findViewById(R.id.cmbFormaEdit);

        String[] tiposAgua = {"Dulce","Salada"};
        String[] tiposForma = {"Rectangular", "Redondo"};

        ArrayAdapter<String> adaptadorTiposAgua = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tiposAgua);
        ArrayAdapter <String> adaptadorTiposFormas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,tiposForma);


        cmbtiposAguaEdit.setAdapter(adaptadorTiposAgua);
        cmbtiposFormaEdit.setAdapter(adaptadorTiposFormas);
    }

}
