package Conexion;

import java.sql.*;
import java.util.*;
import java.math.BigDecimal;
import java.util.Date;

public class database {
    
    private final String url = "jdbc:mysql://localhost:3306/dbsupercompras";
    private final String user = "sandia";
    private final String password = "ytz12345678";
    
    private Connection conexion;

    public database() {
        try {
            // Establecer la conexión utilizando los valores de URL, usuario y contraseña
            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida");
        } catch (SQLException e) {
            System.out.println("Error de conexión");
            e.printStackTrace();
        }
    }

//-------------------------------------------------------------------------------------------------------
// cerrar conexion con la bd
    public void cerrarConexion(){
    try{
        conexion.close();
    }catch(SQLException e){
       e.printStackTrace();
       }
    }
        //-----------------------------------------------------------------------------------------------
    //metodo para actualizar datos en la db
    
    public int Actualizar(String consulta){
    try{
       Statement st=conexion.createStatement();
       return st.executeUpdate(consulta);
    }catch (SQLException e){
       e.printStackTrace();
    }
    return 0;
    }
    //---------------------------------------------------------------------------------------------------
    //metodo para organizar datos antes de enlistarlos en las tablas
private List<Map<String, Object>> organizarDatos(ResultSet rs) {
    List<Map<String, Object>> filas = new ArrayList<>();
    try {
        int numColumnas = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            Map<String, Object> renglon = new HashMap<>();
            for (int i = 1; i <= numColumnas; i++) {
                String nombreCampo = rs.getMetaData().getColumnName(i); // Usar getColumnName en lugar de getColumName
                Object valor = rs.getObject(nombreCampo);
                renglon.put(nombreCampo, valor);
            }
            filas.add(renglon);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return filas;
}

    //-----------------------------------------------------------------------------------------------
//metodo para listar datos
public List Listar(String consulta) {
   ResultSet rs=null;
   List resultado=new ArrayList();
   try{
      Statement st=conexion.createStatement();
      rs=st.executeQuery(consulta);
      resultado=organizarDatos(rs);
   }catch(SQLException e) {
      System.out.println("No se realizo la consulta");
      e.printStackTrace();
   }
   return resultado;
}
//---------------------------------------------------------------------------------------------------
// clases java
//quitar

public class Productos {
    private int id;
    private String nombre;
    private String categoria;
    private BigDecimal precioUnitario;
    private String marca;

    // Getters y Setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public BigDecimal getPrecioUnitario() {
            return precioUnitario;
        }

        public void setPrecioUnitario(BigDecimal precioUnitario) {
            this.precioUnitario = precioUnitario;
        }

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }
    
}

public class Clientes {
    private int id;
    private String nombre;
    private String direccion;
    private String numeroTelefono;
    private String correoElectronico;

    // Getters y Setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getNumeroTelefono() {
            return numeroTelefono;
        }

        public void setNumeroTelefono(String numeroTelefono) {
            this.numeroTelefono = numeroTelefono;
        }

        public String getCorreoElectronico() {
            return correoElectronico;
        }

        public void setCorreoElectronico(String correoElectronico) {
            this.correoElectronico = correoElectronico;
        }
    
}

public class Transaccion {
    private int id;
    private Date fechaHora;
    private String tipo;
    private BigDecimal total;
    private String metodoPago;
    private Clientes cliente;
    private Proveedores proveedor;
    private List<DetalleTransaccion> detalles;

    // Getters y Setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getFechaHora() {
            return fechaHora;
        }

        public void setFechaHora(Date fechaHora) {
            this.fechaHora = fechaHora;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        public String getMetodoPago() {
            return metodoPago;
        }

        public void setMetodoPago(String metodoPago) {
            this.metodoPago = metodoPago;
        }

        public Clientes getCliente() {
            return cliente;
        }

        public void setCliente(Clientes cliente) {
            this.cliente = cliente;
        }

        public Proveedores getProveedor() {
            return proveedor;
        }

        public void setProveedor(Proveedores proveedor) {
            this.proveedor = proveedor;
        }

        public List<DetalleTransaccion> getDetalles() {
            return detalles;
        }

        public void setDetalles(List<DetalleTransaccion> detalles) {
            this.detalles = detalles;
        }
    
}

public class DetalleTransaccion {
    private int id;
    private Transaccion transaccion;
    private Productos producto;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    // Getters and Setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Transaccion getTransaccion() {
            return transaccion;
        }

        public void setTransaccion(Transaccion transaccion) {
            this.transaccion = transaccion;
        }

        public Productos getProducto() {
            return producto;
        }

        public void setProducto(Productos producto) {
            this.producto = producto;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public BigDecimal getPrecioUnitario() {
            return precioUnitario;
        }

        public void setPrecioUnitario(BigDecimal precioUnitario) {
            this.precioUnitario = precioUnitario;
        }

        public BigDecimal getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
        }
    
    
}

public class Empleados {
    private int id;
    private String nombre;
    private String rol;
    private String horarioLaboral;

    // Getters y Setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getRol() {
            return rol;
        }

        public void setRol(String rol) {
            this.rol = rol;
        }

        public String getHorarioLaboral() {
            return horarioLaboral;
        }

        public void setHorarioLaboral(String horarioLaboral) {
            this.horarioLaboral = horarioLaboral;
        }
    
    
}

public class Proveedores {
    private int id;
    private String nombre;
    private String direccion;
    private String numeroTelefono;
    private String correoElectronico;

    // Getters y Setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getNumeroTelefono() {
            return numeroTelefono;
        }

        public void setNumeroTelefono(String numeroTelefono) {
            this.numeroTelefono = numeroTelefono;
        }

        public String getCorreoElectronico() {
            return correoElectronico;
        }

        public void setCorreoElectronico(String correoElectronico) {
            this.correoElectronico = correoElectronico;
        }
    
    
  }
}
