package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.services.PlantasService;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Planta;

public class NuevoPlanta extends AppCompatActivity {

    private Acuario acuarioSeleccionado;
    private Planta planta = new Planta();
    private String pathActual;
    private EditText edtNombrePlanta;
    private EditText edtDescripcionPlanta;
    private EditText edtCantidadPlanta;
    private ImageView fotoPlanta;

    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        obtenerAcuarioSeleccionado();
    }

    private void initComponents(){
        setContentView(R.layout.activity_nuevo_pez);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtNombrePlanta = (EditText) findViewById(R.id.txtNombrePlanta);
        edtDescripcionPlanta = (EditText) findViewById(R.id.txtDescripcionPlanta);
        edtCantidadPlanta = (EditText) findViewById(R.id.txtCantidadPlanta);
        fotoPlanta = (ImageView) findViewById(R.id.foto_planta);
    }

    private void obtenerAcuarioSeleccionado() {
        acuarioSeleccionado = (Acuario) getIntent().getSerializableExtra("acuarioSeleccionado");
        if (acuarioSeleccionado == null) {
            acuarioSeleccionado = new Acuario();
        }
    }

    public void guardarPlanta(View v){
        planta.setNombre(edtNombrePlanta.getText().toString());
        planta.setDescripcion(edtDescripcionPlanta.getText().toString());
        planta.setCantidad(Integer.valueOf(edtCantidadPlanta.getText().toString()));
        planta.setAcuarioId(acuarioSeleccionado.getId());
        planta.setFotoURL(pathActual);

        PlantasService plantasService = new PlantasService();
        plantasService.createPlanta(planta);

        Intent i = new Intent(this, MisPlantas.class);
        startActivity(i);
    }

    public void tomarFotoPlanta(View view){
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

            int targetW = 150;
            int targetH = 150;

            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(pathActual, bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            Bitmap bitmap = BitmapFactory.decodeFile(pathActual, bmOptions);
            fotoPlanta.setImageBitmap(bitmap);
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
