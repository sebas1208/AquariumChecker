package ec.edu.epn.acuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ec.edu.epn.acuario.adaptador.AcuarioAdapter;
import ec.edu.epn.acuario.vo.AcuarioVO;

public class Resumen extends AppCompatActivity {

    private ListView lvAcuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        //Obteniendo listview
        lvAcuarios = (ListView) findViewById(R.id.lvAcuarios);

        ArrayList <AcuarioVO> acuarios = new ArrayList<AcuarioVO>();

        AcuarioVO acuario1 = new AcuarioVO("Sr. Benavides","Dulce",3000);
        acuarios.add(acuario1);

        AcuarioVO acuario2 = new AcuarioVO("Sr. Moreno","Salada",2000);
        acuarios.add(acuario2);

        AcuarioVO acuario3 = new AcuarioVO("Sr. Corrales","Dulce",6000);
        acuarios.add(acuario3);

        AcuarioAdapter adaptadorAcuario = new AcuarioAdapter(this,acuarios);

        lvAcuarios.setAdapter(adaptadorAcuario);



    }


}
