package Modelo;

import java.time.LocalDateTime;

public class Transacciones {

    private int idTransaccion;
    private LocalDateTime fechaHora;
    private String tipo;
    private double total;
    private String metodoPago;
    private Cliente cliente;
    private Proveedores proveedor;
    private Producto producto;

    public Transacciones() {
    }

    public Transacciones(int idTransaccion, LocalDateTime fechaHora, String tipo, double total, String metodoPago,
                         Cliente cliente, Proveedores proveedor, Producto producto) {
        this.idTransaccion = idTransaccion;
        this.fechaHora = fechaHora;
        this.tipo = tipo;
        this.total = total;
        this.metodoPago = metodoPago;
        this.cliente = cliente;
        this.proveedor = proveedor;
        this.producto = producto;
    }

    
    
    
    
     public int getClienteId() {
        return cliente.getId_cliente();
    }

    public int getProveedorId() {
        return proveedor.getId_proveedor();
    }

    public int getProductoId() {
        return producto.getId_producto();
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
    
    
    
    
    
    // Métodos getter y setter para los atributos adicionales

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // Resto de los métodos getter y setter

    @Override
    public String toString() {
        return "Transacciones{" +
                "idTransaccion=" + idTransaccion +
                ", fechaHora=" + fechaHora +
                ", tipo='" + tipo + '\'' +
                ", total=" + total +
                ", metodoPago='" + metodoPago + '\'' +
                ", cliente=" + cliente +
                ", proveedor=" + proveedor +
                ", producto=" + producto +
                '}';
    }
}