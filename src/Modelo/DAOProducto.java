package Modelo;

import Conexion.database;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DAOProducto {

    //metodo insertar 
    public Producto Insertar(String nombre, String categoria, String precio, String marca, String stock) {
        String transaccion = "INSERT INTO Productos (nombre, categoria, precio_unitario, marca, stock) VALUES (?, ?, ?, ?, ?)";

        if (new database().Actualizar(transaccion, nombre, categoria, Double.parseDouble(precio), marca, stock) > 0) {
            return new Producto(nombre, categoria, Double.parseDouble(precio), marca, stock);
        }

        return null;
    }

    
    //metodo actualizar
    public int Actualizar(int id, String nombre, String categoria, double precio, String marca, String stock) {
        String transaccion = "UPDATE Productos SET nombre=?, categoria=?, precio_unitario=?, marca=?, stock=? WHERE id_producto=?";

        return new database().Actualizar(transaccion, nombre, categoria, Double.toString(precio), marca, stock, id);
    }

    // Método obtener productos
    public List<Producto> obtenerDatos() {
        String transaccion = "SELECT * FROM Productos";

        List<Map<String, Object>> registros = new database().Listar(transaccion);
        List<Producto> productos = new ArrayList<>();

        for (Map<String, Object> registro : registros) {
            Producto producto = new Producto(
                    (int) registro.get("id_producto"),
                    (String) registro.get("nombre"),
                    (String) registro.get("categoria"),
                    (double) registro.get("precio_unitario"),
                    (String) registro.get("marca"),
                    (String) registro.get("stock")
            );
            productos.add(producto);
        }

        return productos;
    }

    
    //metodo para actualizar el precio
public int ActualizarPrecio(int id, double nuevoPrecio) {
    String transaccion = "UPDATE Productos SET precio_unitario=? WHERE id_producto=?";
    return new database().Actualizar(transaccion, nuevoPrecio, id);
}


//metodo para buscar, como me cago este metodo de mierda ojala le de diarrea al que lo invento
public Producto obtenerProductoPorId(int id) {
    String transaccion = "SELECT * FROM Productos WHERE id_producto=?";
    List<Map<String, Object>> registros = new database().Listar(transaccion, id);

    if (!registros.isEmpty()) {
        Map<String, Object> registro = registros.get(0);
        return new Producto(
                (int) registro.get("id_producto"),
                (String) registro.get("nombre"),
                (String) registro.get("categoria"),
                ((Number) registro.get("precio_unitario")).doubleValue(),
                (String) registro.get("marca"),
                (String) registro.get("stock")
        );
    }

    return null;
}

    
    // Método eliminar
    public int Eliminar(int id) {
        String transaccion = "DELETE FROM Productos WHERE id_producto=?";

        return new database().Actualizar(transaccion, id);
    }
}