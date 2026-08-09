/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Usuario
 */
public class Producto implements InterfazDataBase {

    private int idProducto;
    private String nombre;
    private double precioUnitario;
    private Proveedor proveedor;
    public static final Producto INSTANCE = new Producto();

    public Producto(int idProducto, String nombre, double precioUnitario, Proveedor proveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.proveedor = proveedor;
    }

    public Producto(String nombre, double precioUnitario, Proveedor proveedor) {
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.proveedor = proveedor;
    }

    private Producto() {
    }

    @Override
    public void insert(Object value) {
        Producto p = (Producto) value;
        Conexion.ejecutar("INSERT INTO productos (nombre, precio_unitario, id_proveedor) VALUES ('" + p.getNombre() + "' , " + p.getPrecioUnitario() + ", " + p.getProveedor().getIdProveedor() + ")");
    }

    @Override
    public void update(Object value) {
        Producto p = (Producto) value;
        Conexion.ejecutar("UPDATE productos SET nombre = '" + p.getNombre() + "', precio_unitario = " + p.getPrecioUnitario() + ", id_proveedor = " + p.getProveedor().getIdProveedor() + " WHERE id_producto = " + p.getIdProducto());
    }

    @Override
    public void delete(Object value) {
        Producto p = (Producto) value;
        Conexion.ejecutar("DELETE FROM productos WHERE id_producto = " + p.getIdProducto());
    }

    public ArrayList<Producto> getList() {
        ArrayList<Producto> list = new ArrayList<>();
        List<Map<String, Object>> p = Conexion.query("SELECT * FROM productos p1 Inner Join proveedores p2 On p1.id_proveedor = p2.id_proveedor");
        for (int i = 0; i < p.size(); i++) {
            int idProducto = (int) p.get(i).get("id_producto");
            String nombre = (String) p.get(i).get("nombre");
            double precioUnitario = Double.parseDouble(((BigDecimal) p.get(i).get("precio_unitario")).toString());
            int idProveedor = (int) p.get(i).get("id_proveedor");
            String nombreEmpresa = (String) p.get(i).get("nombre_empresa");
            String ruc = (String) p.get(i).get("ruc");
            String email = (String) p.get(i).get("email");
            Proveedor proveedor = new Proveedor(idProveedor, nombreEmpresa, ruc, email);

            Producto temp = new Producto(idProducto, nombre, precioUnitario, proveedor);
            list.add(temp);

        }
        return list;

    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    @Override
    public String toString() {
        return nombre + ", " + precioUnitario;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Producto other = (Producto) obj;
        return this.idProducto == other.idProducto; // Cambia 'id' por el nombre de tu atributo clave
    }

}
