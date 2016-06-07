package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.services.AcuarioService;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;

public class MisAcuarios extends AppCompatActivity {

    ListView misAcuarios;
    List<AcuarioVO> acuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        getAcuarioList();
    }

    private void initComponents(){
        setContentView(R.layout.acuarios_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MisAcuariosAdapter adapter = new MisAcuariosAdapter(this, acuarios);
        misAcuarios = (ListView) findViewById(R.id.mis_acuarios_list);
        misAcuarios.setAdapter(adapter);
    }

    private void getAcuarioList(){
        AcuarioService service = new AcuarioService(getApplicationContext());
        acuarios.addAll(service.listAcuarios());
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

}
