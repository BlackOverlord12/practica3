package com.example.practica03corte02;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.practica03corte02.Modelo.AlumnosDb;

public class AlumnoAlta extends AppCompatActivity {

    private static final int REQUEST_SELECT_IMAGE = 1;
    private Button btnGuardar, btnRegresar, btnEliminar;
    private Alumno alumno;
    private EditText txtNombre, txtMatricula, txtGrado;
    private ImageView imgAlumno;
    private TextView lblImagen;
    private String carrera = "Ing. Tec. Información";
    private int posicion;

    private AlumnosDb alumnosDba;
    private String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno_alta);

        btnGuardar = (Button) findViewById(R.id.btnSalir);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);
        btnEliminar = (Button) findViewById(R.id.btnBorrar);
        txtMatricula = (EditText) findViewById(R.id.txtMatricula);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtGrado = (EditText) findViewById(R.id.txtGrado);
        imgAlumno = (ImageView) findViewById(R.id.imgAlumno);
        lblImagen= (TextView) findViewById(R.id.lblFoto);
        btnEliminar.setEnabled(false);
        Bundle bundle = getIntent().getExtras();
        alumno = (Alumno) bundle.getSerializable("alumno");
        posicion = bundle.getInt("posicion", posicion);
        if (posicion >= 0) {
            alumno.setId(Aplicacion.alumnos.get(posicion).getId());
            txtMatricula.setText(alumno.getMatricula());
            txtNombre.setText(alumno.getNombre());
            txtGrado.setText(alumno.getCarrera());
            imgAlumno.setImageURI(Uri.parse(alumno.getImg()));
            lblImagen.setText(alumno.getImg().toString());
            btnEliminar.setEnabled(true);
        }

        imgAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alumno == null) {
                    alumno = new Alumno();
                    alumno.setCarrera(txtGrado.getText().toString());
                    alumno.setMatricula(txtMatricula.getText().toString());
                    alumno.setNombre(txtNombre.getText().toString());

                    if (validar()) {
                        AlumnosDb alumnosDb = new AlumnosDb(getApplicationContext());

                        if (selectedImagePath != null) {
                            alumno.setImg(selectedImagePath);
                            lblImagen.setText(selectedImagePath.toString());
                        }
                        Aplicacion aplicacion = null;
                        Toast.makeText(getApplicationContext(), "Creación del Alumno Exitoso", Toast.LENGTH_SHORT).show();

                        Aplicacion.alumnos.add(alumno);
                        alumnosDb.insertAlumno(alumno);
                        aplicacion.Creacion();
                        setResult(Activity.RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Faltó capturar datos", Toast.LENGTH_SHORT).show();
                        txtMatricula.requestFocus();
                    }
                }

                if (posicion >= 0) {
                    alumno.setMatricula(txtMatricula.getText().toString());
                    alumno.setNombre(txtNombre.getText().toString());
                    alumno.setCarrera(txtGrado.getText().toString());
                    if (selectedImagePath != null) {
                        alumno.setImg(selectedImagePath);
                        lblImagen.setText(selectedImagePath.toString());
                    }

                    Aplicacion.alumnos.get(posicion).setMatricula(alumno.getMatricula());
                    Aplicacion.alumnos.get(posicion).setNombre(alumno.getNombre());
                    Aplicacion.alumnos.get(posicion).setCarrera(alumno.getCarrera());
                    Aplicacion.alumnos.get(posicion).setImg(alumno.getImg());
                    AlumnosDb alumnosDba = new AlumnosDb(getApplicationContext());
                    alumnosDba.updateALumno(alumno);
                    Toast.makeText(getApplicationContext(), "Modificación del Alumno Exitosa", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aplicacion aplicacion = null;
                AlumnosDb alumnosDba = new AlumnosDb(getApplicationContext());
                alumno.setId(Aplicacion.alumnos.get(posicion).getId());
                int id = alumno.getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(AlumnoAlta.this);
                builder.setTitle("Confirmar eliminación");
                builder.setMessage("¿Estás seguro de que deseas eliminar este alumno?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Aplicacion.alumnos.remove(posicion);
                        alumnosDba.deleteAlumnos(alumno.getId());
                        Toast.makeText(getApplicationContext(), "Alumno eliminado", Toast.LENGTH_SHORT).show();

                        aplicacion.Creacion();
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // No se realiza ninguna acción
                    }
                });
                builder.show();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    private boolean validar() {
        boolean exito = true;
        Log.d("nombre", "validar: " + txtNombre.getText());
        if (txtNombre.getText().toString().equals("")) exito = false;
        if (txtMatricula.getText().toString().equals("")) exito = false;
        if (txtGrado.getText().toString().equals("")) exito = false;
        return exito;
    }

    private void openGallery() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_SELECT_IMAGE);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_SELECT_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_SELECT_IMAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            selectedImagePath = selectedImageUri.toString();

            imgAlumno.setImageURI(selectedImageUri);
        }
    }
}
