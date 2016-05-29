package ec.edu.epn.acuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class EditarAcuario extends AppCompatActivity {

    private Spinner cmbtiposAguaEdit;
    private Spinner cmbtiposFormaEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_acuario);

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
