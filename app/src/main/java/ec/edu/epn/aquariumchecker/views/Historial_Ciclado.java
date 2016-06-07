package ec.edu.epn.aquariumchecker.views;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import ec.edu.epn.aquariumchecker.R;


public class Historial_Ciclado extends AppCompatActivity implements View.OnClickListener {

    private Spinner cmbCo2;
    private Button btnCalendario;
    private int anio,mes,dia,hora,minuto;
    private Spinner cmbIluminacion;
    private TextView txtFecha,txtHora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_ciclado);
        btnCalendario = (Button) findViewById(R.id.btnCalendario);
        txtFecha = (TextView) findViewById(R.id.txtFechaView);

        cmbCo2 = (Spinner)findViewById(R.id.cmbCo2);
        cmbIluminacion = (Spinner)findViewById(R.id.cmbIluminacion);

        //combo de co2
        String[]co2= {"Si","No"};
        ArrayAdapter<String> adaptadorCo2 =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        co2);
        cmbCo2.setAdapter(adaptadorCo2);
        btnCalendario.setOnClickListener(this);


        //combo de iluminacion
        String[]iluminacion= {"Alta","Media","Baja"};
        ArrayAdapter<String> adaptadorIluminacion =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        iluminacion);

        cmbIluminacion.setAdapter(adaptadorIluminacion);
    }

    public void onClick(View v) {

        if (v == btnCalendario) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            anio = c.get(Calendar.YEAR);
            mes = c.get(Calendar.MONTH);
            dia = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            txtFecha.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, anio, mes, dia);
            datePickerDialog.show();
        }
    }

    }
