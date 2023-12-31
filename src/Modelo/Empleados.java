package Modelo;

import java.sql.Timestamp;

public class Empleados {

    private int id_empleado; // identificador del empleado, llave primaria también
    private String nombre;
    private String rol;
    private Timestamp horario_inicio;
    private Timestamp horario_fin;
    private String dias_trabajo;

    public Empleados() {
    }

    public Empleados(String nombre, String rol, Timestamp horario_inicio, Timestamp horario_fin, String dias_trabajo) {
        this.nombre = nombre;
        this.rol = rol;
        this.horario_inicio = horario_inicio;
        this.horario_fin = horario_fin;
        this.dias_trabajo = dias_trabajo;
    }

    public Empleados(int id_empleado, String nombre, String rol, Timestamp horario_inicio, Timestamp horario_fin, String dias_trabajo) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.rol = rol;
        this.horario_inicio = horario_inicio;
        this.horario_fin = horario_fin;
        this.dias_trabajo = dias_trabajo;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Timestamp getHorario_inicio() {
        return horario_inicio;
    }

    public void setHorario_inicio(Timestamp horario_inicio) {
        this.horario_inicio = horario_inicio;
    }

    public Timestamp getHorario_fin() {
        return horario_fin;
    }

    public void setHorario_fin(Timestamp horario_fin) {
        this.horario_fin = horario_fin;
    }

    public String getDias_trabajo() {
        return dias_trabajo;
    }

    public void setDias_trabajo(String dias_trabajo) {
        this.dias_trabajo = dias_trabajo;
    }

}