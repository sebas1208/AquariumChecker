package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.FotosAdapter;
import ec.edu.epn.aquariumchecker.services.FotoService;
import ec.edu.epn.aquariumchecker.services.GaleriaService;
import ec.edu.epn.aquariumchecker.vo.*;
import ec.edu.epn.aquariumchecker.vo.Galeria;

public class NuevaGaleria extends AppCompatActivity {
    private EditText edtObservaciones;
    private String pathActual;
    private ListView lvwFotos;
    private List<Foto> fotosList = new ArrayList<>();
    private FotosAdapter adapter;

    private Acuario acuarioSeleccionado;
    private ec.edu.epn.aquariumchecker.vo.Galeria nuevaGaleria = new Galeria();
    static final int REQUEST_IMAGE_CAPTURE = 1;

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
        lvwFotos = (ListView) findViewById(R.id.fotos_galeria_list);
        adapter = new FotosAdapter(this,fotosList);
        lvwFotos.setAdapter(adapter);
    }

    private void obtenerAcuarioSeleccionado(){
        acuarioSeleccionado = (Acuario)getIntent().getSerializableExtra("acuarioSeleccionado");
        if(acuarioSeleccionado == null){
            acuarioSeleccionado = new Acuario();
        }
    }

    public void guardarGaleria(View v){
        nuevaGaleria.setFecha(new Date());
        nuevaGaleria.setFotos("");
        nuevaGaleria.setObservaciones(edtObservaciones.getText().toString());
        nuevaGaleria.setIdAcuario(acuarioSeleccionado.getId());

        GaleriaService galeriaService = new GaleriaService(getApplicationContext());
        FotoService fotoService = new FotoService(getApplicationContext());
        //long idGaleria = galeriaService.createGaleria(nuevaGaleria);

        for (Foto foto: fotosList){
//            foto.setIdGaleria((int)idGaleria);
//            fotoService.createFoto(foto);
        }

        Intent i = new Intent(this, ec.edu.epn.aquariumchecker.views.Galeria.class);
        i.putExtra("acuarioSeleccionado",acuarioSeleccionado);
        startActivity(i);
    }

    public void tomarFoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Foto foto = new Foto();
            foto.setDescripcion(pathActual.substring(1,3));
            foto.setPath(pathActual);
            fotosList.add(foto);
            adapter.notifyDataSetChanged();
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        pathActual = image.getAbsolutePath();
        Log.v("Path", pathActual);
        return image;
    }

}
