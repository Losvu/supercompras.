package Modelo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Date;

import Modelo.Cliente;
 import java.sql.Connection;
import Modelo.Producto; 
import Modelo.Proveedores; 
import Modelo.Transacciones;


public class DAOTransacciones {
    private Connection connection;

    public DAOTransacciones() {
    }
    
    public DAOTransacciones(Connection connection) {
        this.connection = connection;
    }
    
    //metodo para insertar ciones Insertar(Cliente cliente
public Transacciones insertar(Cliente cliente, LocalDateTime fecha, double monto, String descripcion) {
    String query = "INSERT INTO Transacciones (cliente_id, fecha_hora, total, metodo_pago, tipo) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        preparedStatement.setInt(1, cliente.getId_cliente());
       preparedStatement.setObject(2, java.sql.Timestamp.valueOf(fecha));
        preparedStatement.setDouble(3, monto);
        preparedStatement.setString(4, descripcion);
        preparedStatement.setString(5, "Venta");

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idTransaccion = generatedKeys.getInt(1);
             return new Transacciones(idTransaccion, fecha.toLocalDate().atStartOfDay(), "Venta", monto, "Pago en efectivo", cliente.getId_cliente(), 0);

            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
    
//metodo actualizar
public int actualizar(int idTransaccion, Map<String, Object> cambios) {
    try {
        StringBuilder query = new StringBuilder("UPDATE Transacciones SET ");
        for (Map.Entry<String, Object> entry : cambios.entrySet()) {
            query.append(entry.getKey()).append("=?, ");
        }
        query.delete(query.length() - 2, query.length()); // Elimina la última coma
        query.append(" WHERE id_transaccion=?");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int parameterIndex = 1;
            for (Object value : cambios.values()) {
                if (value instanceof LocalDateTime) {
                    preparedStatement.setObject(parameterIndex++, Timestamp.valueOf((LocalDateTime) value));
                } else {
                    preparedStatement.setObject(parameterIndex++, value);
                }
            }
            preparedStatement.setInt(parameterIndex, idTransaccion);

            return preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return 0; // Devuelve 0 para indicar un error
    }
}

// Método obtenerDatos
public List<Transacciones> obtenerDatos() {
    String query = "SELECT * FROM Transacciones";

    List<Transacciones> transacciones = new ArrayList<>();

    try (Statement statement = connection.createStatement()) {
        try (ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int idTransaccion = resultSet.getInt("id_transaccion");
                LocalDateTime fechaHora = resultSet.getTimestamp("fecha_hora").toLocalDateTime();
                String tipo = resultSet.getString("tipo");
                double total = resultSet.getDouble("total");
                String metodoPago = resultSet.getString("metodo_pago");
                int clienteId = resultSet.getInt("cliente_id");
                int proveedorId = resultSet.getInt("proveedor_id");

                // Utiliza LocalDateTime directamente en la creación de Transacciones
                Transacciones transaccion = new Transacciones(idTransaccion, fechaHora, tipo, total, metodoPago, clienteId, proveedorId);
                transacciones.add(transaccion);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return transacciones;
}
 

 //metodo eliminar
 
public int eliminar(int idTransaccion) {
    String query = "DELETE FROM Transacciones WHERE id_transaccion = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, idTransaccion);
        return preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        return 0; // Devuelve 0 para indicar un error
    }
}

// Método para actualizar una transacción
public int actualizarTransaccion(int idTransaccion, LocalDateTime fechaHora, double total, String tipo, String metodoPago) {
    String query = "UPDATE Transacciones SET fecha_hora=?, total=?, tipo=?, metodo_pago=? WHERE id_transaccion=?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setObject(1, java.sql.Timestamp.valueOf(fechaHora));
        preparedStatement.setDouble(2, total);
        preparedStatement.setString(3, tipo);
        preparedStatement.setString(4, metodoPago);
        preparedStatement.setInt(5, idTransaccion);

        return preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        return 0; // Devuelve 0 para indicar un error
    }
}





}