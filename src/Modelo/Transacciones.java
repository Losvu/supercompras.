package Modelo;

import java.time.LocalDateTime;

public class Transacciones {

    private int idTransaccion;
    private LocalDateTime fechaHora;
    private String tipo;
    private double total;
    private String metodoPago;
    private Cliente cliente;
    private Proveedor proveedor;
    private Producto producto;

    public Transacciones() {
    }

 public Transacciones(int idTransaccion, LocalDateTime fechaHora, String tipo, double total, String metodoPago, Cliente cliente, Proveedor proveedor, Producto producto) {
    this.idTransaccion = idTransaccion;
    this.fechaHora = fechaHora;
    this.tipo = tipo;
    this.total = total;
    this.metodoPago = metodoPago;
    this.cliente = cliente;
    this.proveedor = proveedor;
    this.producto = producto;
}

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

// Constructor clientes y getters y setters
class Cliente {
    private int idCliente;
    private String nombre;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre) {
        this.idCliente = idCliente;
        this.nombre = nombre;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

// Constructor productos y getters y setters
class Producto {
    private int idProducto;
    private String nombre;

    public Producto() {
    }

    public Producto(int idProducto, String nombre) {
        this.idProducto = idProducto;
        this.nombre = nombre;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

// Constructor proveedores y getters y setters
class Proveedor {
    private int idProveedor;
    private String nombre;

    public Proveedor() {
    }

    public Proveedor(int idProveedor, String nombre) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
}