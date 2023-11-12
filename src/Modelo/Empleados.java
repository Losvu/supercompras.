package Modelo;
import java.time.LocalTime;

public class Empleados {
    
    private int id_empleado; // identificador del empleado
    private String nombre;
    private String rol;
    private LocalTime horario_inicio;
    private LocalTime horario_fin;
    private String dias_trabajo;

    public Empleados() {
    }
    
    public Empleados(int id_empleado, String nombre, String rol, LocalTime horario_inicio, LocalTime horario_fin, String dias_trabajo) {
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

    public LocalTime getHorario_inicio() {
        return horario_inicio;
    }

    public void setHorario_inicio(LocalTime horario_inicio) {
        this.horario_inicio = horario_inicio;
    }

    public LocalTime getHorario_fin() {
        return horario_fin;
    }

    public void setHorario_fin(LocalTime horario_fin) {
        this.horario_fin = horario_fin;
    }

    public String getDias_trabajo() {
        return dias_trabajo;
    }

    public void setDias_trabajo(String dias_trabajo) {
        this.dias_trabajo = dias_trabajo;
    }
    
    
    
}


