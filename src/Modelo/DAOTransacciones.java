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
import Conexion.database;
import java.sql.Connection;
import Modelo.Producto;
import Modelo.Proveedores;
import Modelo.Transacciones;

public class DAOTransacciones {

    private Connection connection;

    public DAOTransacciones() {
        this.connection = new database().getConnection();
        // Agregar un mensaje de prueba
        System.out.println("Connection object: " + connection);
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
//metodo actualizar transacciones, correspondiente para el apartado de editar
public int actualizarTransaccion(int idTransaccion, Map<String, Object> cambios) {
    try {
        StringBuilder query = new StringBuilder("UPDATE Transacciones SET ");
        int numParametros = 0;

        for (Map.Entry<String, Object> entry : cambios.entrySet()) {
            // Verificar si la columna existe antes de agregarla a la consulta
            if (existeColumna(entry.getKey(), "Transacciones")) {
                query.append(entry.getKey()).append("=?, ");
                numParametros++;
            }
        }
        query.delete(query.length() - 2, query.length()); // Elimina la última coma
        query.append(" WHERE id_transaccion=?");
        numParametros++; // Añadir el parámetro del ID al total

        System.out.println("Consulta SQL: " + query.toString()); // Imprimir la consulta SQL

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int parameterIndex = 1;

            for (Object value : cambios.values()) {
                if (value instanceof LocalDateTime) {
                    preparedStatement.setObject(parameterIndex++, Timestamp.valueOf((LocalDateTime) value));
                } else {
                    preparedStatement.setObject(parameterIndex++, value);
                }
            }

            // Agregar el parámetro del ID al final
            preparedStatement.setInt(parameterIndex, idTransaccion);

            if (parameterIndex != numParametros) {
                System.out.println("¡Error! La cantidad de parámetros no coincide.");
            }

            return preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return 0; // Devuelve 0 para indicar un error
    }
}

// Método para verificar la existencia de una columna en una tabla
    private boolean existeColumna(String nombreColumna, String nombreTabla) {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getColumns(null, null, nombreTabla, null);

            while (resultSet.next()) {
                String nombre = resultSet.getString("COLUMN_NAME");
                if (nombreColumna.equals(nombre)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

//metodos para las opciones de editar y demas... necesario hasta cierto punto
//metodo para obtener una transacción por su ID
    public Transacciones obtenerTransaccionPorId(int idTransaccion) {
        String query = "SELECT * FROM Transacciones WHERE id_transaccion = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idTransaccion);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id_transaccion");
                    LocalDateTime fechaHora = resultSet.getTimestamp("fecha_hora").toLocalDateTime();
                    String tipo = resultSet.getString("tipo");
                    double total = resultSet.getDouble("total");
                    String metodoPago = resultSet.getString("metodo_pago");
                    int clienteId = resultSet.getInt("cliente_id");
                    int proveedorId = resultSet.getInt("proveedor_id");

                    return new Transacciones(id, fechaHora, tipo, total, metodoPago, clienteId, proveedorId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
