package Modelo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DAOTransacciones {
    private Connection connection;

    public DAOTransacciones(Connection connection) {
        this.connection = connection;
    }

    public Transacciones insertarTransaccion(Transacciones transaccion) {
        String query = "INSERT INTO Transacciones (fecha_hora, tipo, total, metodo_pago, id_cliente, id_proveedor, id_producto) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, Timestamp.valueOf(transaccion.getFechaHora()));
            preparedStatement.setString(2, transaccion.getTipo());
            preparedStatement.setDouble(3, transaccion.getTotal());
            preparedStatement.setString(4, transaccion.getMetodoPago());
            preparedStatement.setInt(5, transaccion.getClienteId());
            preparedStatement.setInt(6, transaccion.getProveedorId());
            preparedStatement.setInt(7, transaccion.getProductoId());

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Otros métodos...
public List<Transacciones> obtenerTransacciones() {
    String query = "SELECT * FROM Transacciones";
    List<Transacciones> transacciones = new ArrayList<>();

    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)) {

        while (resultSet.next()) {
            int idTransaccion = resultSet.getInt("idTransaccion");
            LocalDateTime fechaHora = resultSet.getTimestamp("fecha_hora").toLocalDateTime();
            String tipo = resultSet.getString("tipo");
            double total = resultSet.getDouble("total");
            String metodoPago = resultSet.getString("metodo_pago");

            Transacciones transaccion = new Transacciones(idTransaccion, fechaHora, tipo, total, metodoPago);
            transacciones.add(transaccion);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return transacciones;
}

    public List<String> obtenerTiposDeTransacciones() {
        String query = "SELECT DISTINCT tipo FROM Transacciones";
        List<String> tipos = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                tipos.add(resultSet.getString("tipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipos;
    }

    public List<String> obtenerMetodosDePago() {
        String query = "SELECT DISTINCT metodo_pago FROM Transacciones";
        List<String> metodosPago = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                metodosPago.add(resultSet.getString("metodo_pago"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return metodosPago;
    }
}