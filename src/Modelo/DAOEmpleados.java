package Modelo;

import Conexion.database;

import java.sql.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DAOEmpleados {

    private Connection connection;

    public DAOEmpleados() {
        this.connection = new database().getConnection();
    }

    public DAOEmpleados(Connection connection) {
        this.connection = connection;
    }

    private int obtenerUltimoId() {
        String transaccion = "SELECT MAX(id_empleado) as max_id FROM Empleados";
        List<Map<String, Object>> registros = new database().Listar(transaccion);

        if (registros != null && registros.size() > 0) {
            return (int) registros.get(0).get("max_id");
        }

        return 0;
    }

    public Empleados Insertar(String nombre, String rol, Timestamp horarioInicio, Timestamp horarioFin, String diasTrabajo) {
        String query = "INSERT INTO Empleados (nombre, rol, horario_inicio, horario_fin, dias_trabajo) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, rol);
            preparedStatement.setTimestamp(3, horarioInicio);
            preparedStatement.setTimestamp(4, horarioFin);
            preparedStatement.setString(5, diasTrabajo);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idEmpleado = generatedKeys.getInt(1);
                    return new Empleados(idEmpleado, nombre, rol, horarioInicio, horarioFin, diasTrabajo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int Actualizar(int id, Map<String, Object> cambios) {
        try {
            StringBuilder query = new StringBuilder("UPDATE Empleados SET ");
            for (Map.Entry<String, Object> entry : cambios.entrySet()) {
                query.append(entry.getKey()).append("=?, ");
            }
            query.delete(query.length() - 2, query.length()); // Elimina la última coma
            query.append(" WHERE id_empleado=?");

            try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
                int parameterIndex = 1;
                for (Object value : cambios.values()) {
                    preparedStatement.setObject(parameterIndex++, value);
                }
                preparedStatement.setInt(parameterIndex, id);

                return preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // Devuelve 0 para indicar un error
        }
    }

    public List<Empleados> obtenerDatos() {
        String transaccion = "SELECT * FROM Empleados";

        List<Map<String, Object>> registros = new database().Listar(transaccion);
        List<Empleados> empleados = new ArrayList<>();

        for (Map<String, Object> registro : registros) {
            System.out.println("Valor de horario_inicio desde la base de datos: " + registro.get("horario_inicio").toString());
            System.out.println("Valor de horario_fin desde la base de datos: " + registro.get("horario_fin").toString());

            Timestamp horarioInicio = (Timestamp) registro.get("horario_inicio");
            Timestamp horarioFin = (Timestamp) registro.get("horario_fin");

            Empleados empleado = new Empleados(
                    (int) registro.get("id_empleado"),
                    (String) registro.get("nombre"),
                    (String) registro.get("rol"),
                    horarioInicio,
                    horarioFin,
                    (String) registro.get("dias_trabajo")
            );

            empleados.add(empleado);
        }

        return empleados;
    }

    public int Eliminar(int id) {
        String transaccion = "DELETE FROM Empleados WHERE id_empleado=" + id;

        return new database().Actualizar(transaccion);
    }

    public int ActualizarRegistro(String nombreTabla, int id, Map<String, Object> cambios) throws SQLException {
        if (cambios.isEmpty()) {
            return 0;  // No hay cambios, retornar 0
        }

        StringBuilder consulta = new StringBuilder("UPDATE " + nombreTabla + " SET ");

        for (Map.Entry<String, Object> entry : cambios.entrySet()) {
            consulta.append(entry.getKey()).append(" = ?, ");
        }

        consulta.delete(consulta.length() - 2, consulta.length());
        consulta.append(" WHERE id_empleado = ?");

        try (Connection conexion = this.connection;
             PreparedStatement pstmt = conexion.prepareStatement(consulta.toString())) {

            int i = 1;
            for (Object valor : cambios.values()) {
                pstmt.setObject(i++, valor);
            }

            pstmt.setInt(i, id);

            return pstmt.executeUpdate();
        }
    }

    public List<String> obtenerNombresColumnas(String nombreTabla) throws SQLException {
        List<String> columnas = new ArrayList<>();
        DatabaseMetaData metaData = null;

        try {
            metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getColumns(null, null, nombreTabla, null);

            while (resultSet.next()) {
                String nombreColumna = resultSet.getString("COLUMN_NAME");
                columnas.add(nombreColumna);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return columnas;
    }

    public LocalDateTime obtenerLocalDateTimeDesdeString(String cadenaFechaHora) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(cadenaFechaHora, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;  // Devolver null en caso de error de formato
        }
    }
}