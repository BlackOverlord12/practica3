package com.example.practica03corte02;

import android.app.Application;
import android.util.Log;

import com.example.practica03corte02.Modelo.AlumnosDb;

import java.util.ArrayList;

public class Aplicacion extends Application {

    public static ArrayList<Alumno> alumnos;
    private MiAdaptador adaptador;

    private AlumnosDb alumnosDb;



    public  ArrayList<Alumno> getAlumnos() {

        return alumnos;
    }

    public MiAdaptador getAdaptor() {

        return adaptador;
    }


    @Override
    public void onCreate() {
        super.onCreate();




        Creacion();
    }


    public void Creacion()
    {
        alumnosDb= new AlumnosDb(getApplicationContext());
        // alumnos =Alumno.llenarAlumnos();
        alumnos = alumnosDb.allAlumnos();
        alumnosDb.openDataBase();

        adaptador=new MiAdaptador(alumnos,this);
        Log.d("", "onCreate: tamaño array list" + alumnos.size());
    }
}
