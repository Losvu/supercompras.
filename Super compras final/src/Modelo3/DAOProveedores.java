package Modelo3;

import Conexion.database;
import java.util.AbstractCollection.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DAOProveedores {
    public Proveedores Insertar (String nombres,String apellidos, String direccion,
            String telefono, String correo, String id){
        String transaccion = "INSERT INTO Proveedores (nombres, apellidos, direccion, telefono, correo, id) VALUES(´"
                + nombres + "´, ´"
                + apellidos + "´, ´"
                + direccion + "´, ´"
                + telefono + "´,´"
                + correo + "´, ´"
                + id + "´, ´";
        
        if (new database().Actualizar(transaccion) > 0) {
            return new Proveedores(nombres, apellidos, direccion, telefono, correo, id);
            
        }
        
        return null;
    }
    
    public int Actualizar (int id, String nombres, String apellidos, String direccion,
            String telefono, String correo){
        
        String transaccion = "UPDATE Proveedores SET nombres=´"
                + nombres + "´, apellidos=´"
                + apellidos + "´, direccion=´"
                + direccion + "´, telefono=´"
                + telefono + "´, correo=´"
                + correo + "´ WHERE id_proveedor="
                + id;
        
        return new database().Actualizar(transaccion);
    }
    
    public List ObtenerDatos() {
        String transaccion ="SELECT * FROM Proveedores";
        
        List<Map> registros = new database().Listar(transaccion);
        List<Proveedores> proveedores = new ArrayList();
        
        for (Map registro : registros) {
            Proveedores prov = new Proveedores(            (String) registro.get("nombres"),
            (String) registro.get("apellidos"),
            (String) registro.get("direccion"),
            (String) registro.get("telefono"),
            (String) registro.get("correo"));
            proveedores.add(prov);
        }
        return proveedores;
    }
    
    public int Eliminar(int id){
        String transaccion = "DELETE FROM Proveedores WHERE id_proveedor=´"+ id +"´";
        return new database().Actualizar(transaccion);
    }
}
