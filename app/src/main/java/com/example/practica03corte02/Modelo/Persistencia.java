package com.example.practica03corte02.Modelo;

import com.example.practica03corte02.Alumno;

public interface Persistencia {


    public void openDataBase();
    public void closeDataBase();
    public long updateALumno (Alumno alumno);

    long insertAlumno(Alumno alumno);

    public void deleteAlumnos (int id);

}
