package Modelo3;
import java.sql.Date;

public class Proveedores {
    private int id_proveedor ;
    private String nombres;
    private String Apellidos;
    private String direccion;
    private String telefono;
    private String correo;
    
    public Proveedores(String nombres, String apellidos, String direccion, String telefono, String correo, String id){
        
        this.id_proveedor = id_proveedor;
        this.nombres = nombres;
        this.Apellidos = Apellidos;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
       
    }
    
    public Proveedores(String nombres, String Apellidos, String direccion, String telefono, String correo){
        this.nombres = nombres;
        this.Apellidos = Apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
    
}
