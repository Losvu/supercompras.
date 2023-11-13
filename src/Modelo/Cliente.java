package Modelo;

public class Cliente {
    private int id_cliente;
    private String nombre;
    private String direccion;
    private String numero_telefono;
    private String correo_electronico;

    public Cliente() {
    }

    public Cliente(int id_cliente, String nombre, String direccion, String numero_telefono, String correo_electronico) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numero_telefono = numero_telefono;
        this.correo_electronico = correo_electronico;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero_telefono() {
        return numero_telefono;
    }

    public void setNumero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    // Agregar el método getIdCliente()
    public int getIdCliente() {
        return id_cliente;
    }
}


