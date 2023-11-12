package Modelo;

import java.sql.Connection;
import Conexion.database;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.Time;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Statement;


public class DAOEmpleados {
    private Connection connection;

    public DAOEmpleados(Connection connection) {
        this.connection = connection;
    }

  
    
    // nueva función para obtener el último ID insertado
    private int obtenerUltimoId() {
        String transaccion = "SELECT MAX(id_empleado) as max_id FROM Empleados";
        List<Map> registros = new database().Listar(transaccion);

        if (registros != null && registros.size() > 0) {
            return (int) registros.get(0).get("max_id");
        }

        return 0;
    }

    // nuevo método para obtener un empleado por ID
    public Empleados obtenerEmpleadoPorId(int id) {
        String transaccion = "SELECT * FROM Empleados WHERE id_empleado = " + id;
        List<Map> registros = new database().Listar(transaccion);

        if (registros != null && registros.size() > 0) {
            Map registro = registros.get(0);
            return new Empleados(
                    (int) registro.get("id_empleado"),
                    (String) registro.get("nombre"),
                    (String) registro.get("rol"),
                    LocalTime.parse((String) registro.get("horario_inicio")),
                    LocalTime.parse((String) registro.get("horario_fin")),
                    (String) registro.get("dias_trabajo")
            );
        }

        return null;
    }

    // metodo insertar
    public Empleados Insertar(String nombre, String rol, LocalTime horarioInicio, LocalTime horarioFin, String diasTrabajo) {
        String query = "INSERT INTO Empleados (nombre, rol, horario_inicio, horario_fin, dias_trabajo) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, rol);
            preparedStatement.setObject(3, Time.valueOf(horarioInicio));
            preparedStatement.setObject(4, Time.valueOf(horarioFin));
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
    // metodo actualizar
public int Actualizar(int id, String nombre, String rol, Time horarioInicio, Time horarioFin, String diasTrabajo) {
    try {
        String transaccion = "UPDATE Empleados SET nombre='"
                + nombre + "', rol='"
                + rol + "', horario_inicio='"
                + horarioInicio + "', horario_fin='"
                + horarioFin + "', dias_trabajo='"
                + diasTrabajo + "' WHERE id_empleado=" + id;

        return new database().Actualizar(transaccion);
    } catch (Exception e) {
        e.printStackTrace();
        return 0; // Devuelve 0 para indicar un error
    }
}
  // método obtenerDatos
public List<Empleados> obtenerDatos() {
    String transaccion = "SELECT * FROM Empleados";

    List<Map> registros = new database().Listar(transaccion);
    List<Empleados> empleados = new ArrayList<>();

    for (Map registro : registros) {
        Empleados empleado = new Empleados((int) registro.get("id_empleado"),
                (String) registro.get("nombre"),
                (String) registro.get("rol"),
                ((Time) registro.get("horario_inicio")).toLocalTime(),
                ((Time) registro.get("horario_fin")).toLocalTime(),
                (String) registro.get("dias_trabajo"));
        empleados.add(empleado);
    }

    return empleados;
}

    // metodo eliminar
    public int Eliminar(int id) {
        String transaccion = "DELETE FROM Empleados WHERE id_empleado=" + id;

        return new database().Actualizar(transaccion);
    }
}