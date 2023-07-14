package com.example.practica03corte02;

public class AlumnoItem {
    private int id;
    private String textNombre;
    private String textMatricula;
    private String carrera;
    private Integer imageId;

    public AlumnoItem() { }
    public AlumnoItem(int id, String nombre, String matricula, String carrera, Integer imageId){
        this.id = id;
        this.textNombre = nombre;
        this.textMatricula = matricula;
        this.carrera = carrera;
        this.imageId = imageId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTextNombre() { return textNombre; }
    public void setTextNombre(String textNombre) { this.textNombre = textNombre; }
    public String getTextMatricula() { return textMatricula; }
    public void setTextMatricula(String textMatricula) { this.textMatricula = textMatricula; }
    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera;  }
    public Integer getImageId() { return imageId; }
    public void setImageId(Integer imageId) { this.imageId = imageId; }
}
