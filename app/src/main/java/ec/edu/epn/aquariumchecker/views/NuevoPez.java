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
import ec.edu.epn.aquariumchecker.services.PecesService;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Peces;

public class NuevoPez extends AppCompatActivity {

    private Acuario acuario;
    private Peces pez = new Peces();
    private String pathActual;
    private EditText edtNombrePez;
    private EditText edtDescripcionPez;
    private EditText edtCantidadPez;
    private ImageView fotoPez;

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

        edtNombrePez = (EditText) findViewById(R.id.txtNombrePez);
        edtDescripcionPez = (EditText) findViewById(R.id.txtDescripcionPez);
        edtCantidadPez = (EditText) findViewById(R.id.txtCantidadPez);
        fotoPez = (ImageView) findViewById(R.id.foto_pez);
    }

    private void obtenerAcuarioSeleccionado() {
        acuario = (Acuario) getIntent().getSerializableExtra("varAcuario");
    }

    public void guardarPez(View v){
        pez.setNombre(edtNombrePez.getText().toString());
        pez.setDescripcion(edtDescripcionPez.getText().toString());
        pez.setCantidad(Integer.valueOf(edtCantidadPez.getText().toString()));
        pez.setIdAcuario(acuario.getId());
        pez.setFoto(pathActual);

        PecesService pecesService = new PecesService();
        pecesService.createPez(pez);
        Intent i = new Intent(this, MisPeces.class);
        i.putExtra("varAcuario",acuario);
        startActivity(i);
    }

    public void tomarFotoPez(View view){
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

            int targetW = 200;
            int targetH = 200;

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
            fotoPez.setImageBitmap(bitmap);
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

    public void cancelar(View view){
        Intent i = new Intent(this, MisPeces.class);
        i.putExtra("varAcuario",acuario);
        startActivity(i);
    }

}
