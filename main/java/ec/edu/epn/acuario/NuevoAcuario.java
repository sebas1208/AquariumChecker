package ec.edu.epn.acuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class NuevoAcuario extends AppCompatActivity {

    private Spinner cmbtiposAgua;
    private Spinner cmbtiposForma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_acuario);
        /*
        Necesito :

        Datos       (ArrayList)
        Adaptador   AguaAdapter
        View        List View lvTipoAgua
         */
        cmbtiposAgua = (Spinner) findViewById(R.id.cmbTipoAgua);
        cmbtiposForma = (Spinner) findViewById(R.id.cmbForma);

        String[] tiposAgua = {"Dulce","Salada"};
        String[] tiposForma = {"Rectangular", "Redondo"};

        ArrayAdapter <String> adaptadorTiposAgua = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tiposAgua);
        ArrayAdapter <String> adaptadorTiposFormas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,tiposForma);


        cmbtiposAgua.setAdapter(adaptadorTiposAgua);
        cmbtiposForma.setAdapter(adaptadorTiposFormas);
    }



}
