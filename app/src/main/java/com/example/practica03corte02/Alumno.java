package com.example.practica03corte02;

import java.io.Serializable;
import java.util.ArrayList;

public class Alumno implements Serializable {
    private int id;
    private String carrera;
    private String nombre;
    private String img;
    private String matricula;

    public Alumno() {
        this.carrera = "";
        this.nombre = "";
        this.img = "";
        this.matricula = "";
    }

    public Alumno(String carrera, String nombre, String img, String matricula) {
        this.carrera = carrera;
        this.nombre = nombre;
        this.img = img;
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImg() {
        return img;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public static ArrayList<Alumno> llenarAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String carrera = "Ing. Tec. Información";

        alumnos.add(new Alumno(carrera, "MORUA ZAMUDIO ESTEFANO", "img2019030344", "2019030344"));
        alumnos.add(new Alumno(carrera, "CARRANZA JAUREGUI CARLOS ALBERTO", "img2020030174", "2020030174"));
        alumnos.add(new Alumno(carrera, "CASTRO LOPEZ MARCO ANTONIO ALARID", "img2020030176", "2020030176"));
        alumnos.add(new Alumno(carrera, "DURAN VALDEZ JOSHUA DANIEL", "img2020030181", "2020030181"));
        alumnos.add(new Alumno(carrera, "GALINDO HERNANDEZ ERNESTO DAVID", "img2020030184", "2020030184"));
        alumnos.add(new Alumno(carrera, "CONTRERAS CEPEDA MAXIMILIANO", "img2020030189", "2020030189"));
        alumnos.add(new Alumno(carrera, "GOMEZ RUELAS IVÁN ENRIQUE", "img2020030199", "2020030199"));
        alumnos.add(new Alumno(carrera, "CRUZ QUINTERO JESUS EDUARDO", "img2020030212", "2020030212"));
        alumnos.add(new Alumno(carrera, "VELARDE OVALLE DAVID ANTONIO", "img2020030241", "2020030241"));
        alumnos.add(new Alumno(carrera, "LAMAS ARMENTA GUSTAVO ADOLFO", "img2020030243", "2020030243"));
        alumnos.add(new Alumno(carrera, "RIVAS LUGO JUAN CARLOS", "img2020030249", "2020030249"));
        alumnos.add(new Alumno(carrera, "SALAS MENDOZA ALEJO", "img2020030264", "2020030264"));
        alumnos.add(new Alumno(carrera, "SERRANO TORRES CARLOS JAIR", "img2020030268", "2020030268"));
        alumnos.add(new Alumno(carrera, "TIRADO ROMERO JESUS TADEO", "img2020030292", "2020030292"));
        alumnos.add(new Alumno(carrera, "CARRILLO GARCIA JAIR", "img2020030304", "2020030304"));
        alumnos.add(new Alumno(carrera, "ARIAS ZATARAIN DIEGO", "img2020030306", "2020030306"));
        alumnos.add(new Alumno(carrera, "VALDEZ MARTINEZ PAOLA EMIRET", "img2020030313", "2020030313"));
        alumnos.add(new Alumno(carrera, "IBARRA FLORES SALMA YARETH", "img2020030315", "2020030315"));
        alumnos.add(new Alumno(carrera, "LIZARRAGA MALDONADO JUAN ANTONIO", "img2020030322", "2020030322"));
        alumnos.add(new Alumno(carrera, "VIERA ROMERO ANGEL ZINEDINE ANASTACIO", "img2020030325", "2020030325"));
        alumnos.add(new Alumno(carrera, "TEJEDA PEINADO BLAS ALBERTO", "img2020030327", "2020030327"));
        alumnos.add(new Alumno(carrera, "VIERA ROMERO ANGEL RONALDO ANASTACIO", "img2020030329", "2020030329"));
        alumnos.add(new Alumno(carrera, "ELIZALDE VARGAS XIOMARA YAMILETH", "img2020030332", "2020030332"));
        alumnos.add(new Alumno(carrera, "SALCIDO SARABIA JESUS ANTONIO", "img2020030333", "2020030333"));
        alumnos.add(new Alumno(carrera, "RODRIGUEZ SANCHEZ YENNIFER CAROLINA", "img2020030389", "2020030389"));
        alumnos.add(new Alumno(carrera, "FLORES PRADO MANUEL ALEXIS", "img2020030766", "2020030766"));
        alumnos.add(new Alumno(carrera, "AGUIRRE TOSTADO VICTOR MOISES", "img2020030771", "2020030771"));
        alumnos.add(new Alumno(carrera, "DOMINGUEZ SARABIA HALACH UINIC", "img2020030777", "2020030777"));
        alumnos.add(new Alumno(carrera, "MACIEL NUÑEZ ENZO ALEJANDRO", "img2020030799", "2020030799"));
        alumnos.add(new Alumno(carrera, "BARRON VARGAS JOSE ALBERTO", "img2020030808", "2020030808"));
        alumnos.add(new Alumno(carrera, "MARTIN IBARRA GIANCARLO", "img2020030819", "2020030819"));
        alumnos.add(new Alumno(carrera, "SANCHEZ OCEGUEDA LUIS ANGEL", "img2020030865", "2020030865"));

        return alumnos;
    }
}
