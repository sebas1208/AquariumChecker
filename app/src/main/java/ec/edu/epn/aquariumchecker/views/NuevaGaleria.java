package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.services.GaleriaService;
import ec.edu.epn.aquariumchecker.vo.*;
import ec.edu.epn.aquariumchecker.vo.Galeria;

public class NuevaGaleria extends AppCompatActivity {
    private ec.edu.epn.aquariumchecker.vo.Galeria nuevaGaleria = new Galeria();
    private EditText edtObservaciones;
    private AcuarioVO acuarioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        obtenerAcuarioSeleccionado();
    }

    private void initComponents(){
        setContentView(R.layout.activity_nueva_galeria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtObservaciones = (EditText)findViewById(R.id.observaciones_galeria);
    }

    private void obtenerAcuarioSeleccionado(){
        acuarioSeleccionado = (AcuarioVO)getIntent().getSerializableExtra("acuarioSeleccionado");
        if(acuarioSeleccionado == null){
            acuarioSeleccionado = new AcuarioVO();
        }
    }

    public void guardarGaleria(View v){
        nuevaGaleria.setFecha(new Date());
        nuevaGaleria.setFotos("");
        nuevaGaleria.setObservaciones(edtObservaciones.getText().toString());
        nuevaGaleria.setIdAcuario(acuarioSeleccionado.getId());

        GaleriaService service = new GaleriaService(getApplicationContext());
        service.createGaleria(nuevaGaleria);

        Intent i = new Intent(this, ec.edu.epn.aquariumchecker.views.Galeria.class);
        i.putExtra("acuarioSeleccionado",acuarioSeleccionado);
        startActivity(i);
    }

}
