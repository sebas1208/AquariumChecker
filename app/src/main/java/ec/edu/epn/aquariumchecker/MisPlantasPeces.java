package ec.edu.epn.aquariumchecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Forma;

public class MisPlantasPeces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_plantas_peces);

        List<Acuario> acuarios = new ArrayList<>();
        acuarios.add(new Acuario("Acuario 1", "Agua Salada", new Forma("Rectangular",2.0),2.0));
        acuarios.add(new Acuario("Acuario 2", "Agua Dulce", new Forma("Rectangular",2.0),2.0));
        MisAcuariosAdapter adapter = new MisAcuariosAdapter(this,acuarios);
        ListView misAcuarios = (ListView)findViewById(R.id.mis_plantas_peces_acuarios_list);
        misAcuarios.setAdapter(adapter);

        misAcuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(parent.getContext(),AgregarPlantasPeces.class);
                startActivity(intent);
            }
        });
    }
}
