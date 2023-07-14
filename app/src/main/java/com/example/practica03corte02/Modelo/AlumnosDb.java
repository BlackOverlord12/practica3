package com.example.practica03corte02.Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.practica03corte02.Alumno;

import java.util.ArrayList;

public class AlumnosDb implements Persistencia, Proyeccion {

        private Context context;
        private AlumnoDbHelper helper;
        private SQLiteDatabase db;

        public AlumnosDb(Context context, AlumnoDbHelper helper){
            this.context = context;
            this.helper = helper;
        }

        public AlumnosDb(Context context){
            this.context = context;
            this.helper = new AlumnoDbHelper(this.context);
        }


        @Override
        public void openDataBase() {

            db = helper.getWritableDatabase();
        }

        @Override
        public void closeDataBase() {

            helper.close();
        }

        @Override
        public long insertAlumno(Alumno alumno) {

            ContentValues values = new ContentValues();
            values.put(DefineTabla.Alumnos.COLUMN_TABLE_MATRICULA, alumno.getMatricula());
            values.put(DefineTabla.Alumnos.COLUMN_TABLE_NOMBRE, alumno.getNombre());
            values.put(DefineTabla.Alumnos.COLUMN_TABLE_CARRERA, alumno.getGrado());
            values.put(DefineTabla.Alumnos.COLUMN_TABLE_FOTO, alumno.getImg());
            this.openDataBase();
            long num = db.insert(DefineTabla.Alumnos.TABLE_NAME, null,values);
            this.closeDataBase();
            Log.d("agregar", "insertAlumno" + num);

            return num;
        }


    @Override
    public long updateALumno(Alumno alumno) {
        ContentValues values = new ContentValues();
        values.put(DefineTabla.Alumnos.COLUMN_TABLE_MATRICULA, alumno.getMatricula());
        values.put(DefineTabla.Alumnos.COLUMN_TABLE_NOMBRE, alumno.getNombre());
        values.put(DefineTabla.Alumnos.COLUMN_TABLE_CARRERA, alumno.getGrado());
        values.put(DefineTabla.Alumnos.COLUMN_TABLE_FOTO, alumno.getImg());
        this.openDataBase();
        long num = db.update(
                DefineTabla.Alumnos.TABLE_NAME, values,
                DefineTabla.Alumnos.COLUMN_TABLE_ID + " =?" , new String[] {String.valueOf(alumno.getId())});
        ;
        this.closeDataBase();
        return num;

    }

        @Override
        public void deleteAlumnos(int id) {
            this.openDataBase();
            db.delete(
                    DefineTabla.Alumnos.TABLE_NAME,
                    DefineTabla.Alumnos.COLUMN_TABLE_ID + "=?",
                    new String[]{String.valueOf(id)});
            this.closeDataBase();

        }

        @Override
        public Alumno getAlumno(String matricula) {

            db = helper. getWritableDatabase();

            Cursor cursor = db.query(
                    DefineTabla.Alumnos.TABLE_NAME,
                    DefineTabla.Alumnos.REGISTRO,
                    DefineTabla.Alumnos.COLUMN_TABLE_ID +" = 2",
                    new String[] {matricula},
                    null, null, null);
            cursor.moveToFirst();
            Alumno alumno = readAlumno(cursor);

            return alumno;

        }

        @Override
        public ArrayList<Alumno> allAlumnos() {
            db = helper. getWritableDatabase();

            Cursor cursor = db.query(
                    DefineTabla.Alumnos.TABLE_NAME,
                    DefineTabla.Alumnos.REGISTRO,
                    null, null, null,null,null);
            ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                Alumno alumno = readAlumno(cursor);
                alumnos.add(alumno);
                cursor.moveToNext();
            }
            cursor.close();
            return alumnos;
        }

        @Override
        public Alumno readAlumno(Cursor cursor) {
            Alumno alumno = new Alumno();

            alumno.setId(cursor.getInt(0));
            alumno.setMatricula(cursor.getString(1));
            alumno.setNombre(cursor.getString(2));
            alumno.setGrado(cursor.getString(3));
            alumno.setImg(cursor.getInt(4));

            return alumno;
        }
    }

