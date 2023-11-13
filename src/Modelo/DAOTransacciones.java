package Modelo;

import Conexion.database;
import Modelo.Transacciones;
import Modelo.Cliente;
import Modelo.Proveedores; // Cambié "Proveedores" a "Proveedor"
import Modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DAOTransacciones {
    private Connection connection;

    public DAOTransacciones(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar una nueva transacción
    public Transacciones insertarTransaccion(Transacciones transaccion) {
        String query = "INSERT INTO Transacciones (fecha_hora, tipo, total, metodo_pago, cliente_id, proveedor_id, producto_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, transaccion.getFechaHora());
            preparedStatement.setString(2, transaccion.getTipo());
            preparedStatement.setDouble(3, transaccion.getTotal());
            preparedStatement.setString(4, transaccion.getMetodoPago());

            preparedStatement.setInt(5, transaccion.getCliente().getIdCliente());
            preparedStatement.setInt(6, transaccion.getProveedor().getIdProveedor());
            preparedStatement.setInt(7, transaccion.getProducto().getIdProducto());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idTransaccion = generatedKeys.getInt(1);
                    return new Transacciones(
                            idTransaccion,
                            transaccion.getFechaHora(),
                            transaccion.getTipo(),
                            transaccion.getTotal(),
                            transaccion.getMetodoPago(),
                            transaccion.getCliente(),
                            transaccion.getProveedor(),
                            transaccion.getProducto()
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Método para obtener transacciones
   // Método para obtener transacciones
public List<Transacciones> obtenerDatos() {
    String transaccion = "SELECT * FROM Transacciones";
    List<Map> registros = new database().Listar(transaccion);
    List<Transacciones> transacciones = new ArrayList<>();

    for (Map registro : registros) {
        // Crear objetos Cliente, Proveedor y Producto con solo el ID y asignarlos a transaccionObj
        Cliente cliente = new Cliente();
        cliente.setId_cliente((int) registro.get("cliente_id"));

        Proveedores proveedores = new Proveedores(); // Cambié "Proveedores" a "Proveedor"
        proveedores.setId_proveedor((int) registro.get("proveedor_id"));

        Producto producto = new Producto();
        producto.setId_producto((int) registro.get("producto_id"));

        transacciones.add(new Transacciones(
                (int) registro.get("id_transaccion"),
                ((java.sql.Timestamp) registro.get("fecha_hora")).toLocalDateTime(),
                (String) registro.get("tipo"),
                (double) registro.get("total"),
                (String) registro.get("metodo_pago"),
                cliente,
                proveedores,
                producto
        ));
    }

    return transacciones;
}

    // Otros métodos (obtenerTransaccionPorId, actualizarTransaccion, eliminarTransaccion)...
}
