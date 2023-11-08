package Modelo;
import java.sql.Date;

public class Producto {
    
    private int id_producto; //identificador del producto
    private String nombre;
    private String categoria;
    private double precio_unitario;
    private String marca;
    private int cantidadStock; // Cantidad en stock, añadirlo en la base de datos
    
    
    //contructor 1
    public Producto(int id_producto, String nombre, String categoria, double precio_unitario, String marca) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio_unitario = precio_unitario;
        this.marca = marca;
        this.cantidadStock = cantidadStock;
    }

    //constructor 2
    public Producto(String nombre, String categoria, double precio_unitario, String marca) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio_unitario = precio_unitario;
        this.marca = marca;
        this.cantidadStock = cantidadStock;
    }

    //getter and setter, metodos de acceso para los atributos
    
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }
    
    
     // Métodos relacionados con la gestión de inventarios
    public void agregarStock(int cantidad) {
        this.cantidadStock += cantidad;
    }
    
    public void reducirStock(int cantidad) {
        if (cantidadStock >= cantidad) {
            this.cantidadStock -= cantidad;
        } else {
            System.out.println("No hay suficiente stock disponible.");
        }
    }

    public Object getIdProducto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getPrecioUnitario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getRelacionTablaId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

