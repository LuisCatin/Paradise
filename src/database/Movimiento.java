/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

/**
 *
 * @author Usuario
 */
public class Movimiento implements InterfazDataBase {

    private int idMovimiento;
    private String tipo;
    private int cantidad;
    private Timestamp fecha;
    private Producto producto;
    private Bodega bodega;
    public static final Movimiento INSTANCE = new Movimiento();

    public Movimiento(int idMovimiento, String tipo, int cantidad, Timestamp fecha, Producto producto, Bodega bodega) {
        this.idMovimiento = idMovimiento;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.producto = producto;
        this.bodega = bodega;

    }

    public Movimiento(String tipo, int cantidad, Timestamp fecha, Producto producto, Bodega bodega) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.producto = producto;
        this.bodega = bodega;

    }

    private Movimiento() {
    }

    @Override
    public void insert(Object value) {
        Movimiento p = (Movimiento) value;
        Conexion.ejecutar("INSERT INTO movimientos (tipo, cantidad, fecha, id_producto, id_bodega) VALUES "
                + "('" + p.getTipo() + "' ,"
                + " " + p.getCantidad() + " ,"
                + " '" + p.getFecha().toString() + "' ,"
                + " " + p.getProducto().getIdProducto() + ","
                + "  " + p.getBodega().getIdBodega() + ")");
    }

    @Override
    public void update(Object value) {
        Movimiento p = (Movimiento) value;
        Conexion.ejecutar("UPDATE movimientos SET "
                + "tipo = '" + p.getTipo() + "', "
                + "cantidad = " + p.getCantidad() + ", "
                + "fecha = '" + p.getFecha().toString() + "', "
                + "id_producto= " + p.getProducto().getIdProducto() + ", "
                + "id_bodega = " + p.getBodega().getIdBodega() + " "
                + "WHERE id_movimiento = " + p.getIdMovimiento());
    }

    @Override
    public void delete(Object value) {
        Movimiento p = (Movimiento) value;
        Conexion.ejecutar("DELETE FROM movimientos WHERE id_movimiento = " + p.getIdMovimiento());
    }

    public ArrayList<Movimiento> getList() {
        ArrayList<Movimiento> list = new ArrayList<>();
        List<Map<String, Object>> p = Conexion.query("SELECT * FROM movimientos m  INNER JOIN productos p ON p.id_producto = m.id_producto INNER JOIN bodegas b ON b.id_bodega = m.id_bodega;");
        for (int i = 0; i < p.size(); i++) {
            int idMovimiento = (int) p.get(i).get("id_movimiento");
            String tipo = (String) p.get(i).get("tipo");
            int cantidad = (int) p.get(i).get("cantidad");
            Timestamp fecha = (Timestamp) p.get(i).get("fecha");
            String nombreEmpresa = (String) p.get(i).get("nombre_empresa");
            String ruc = (String) p.get(i).get("ruc");
            String email = (String) p.get(i).get("email");
            int idProveedor = (int) p.get(i).get("id_proveedor");
            Proveedor proveedor = new Proveedor(idProveedor, nombreEmpresa, ruc, email);
            int idProducto = (int) p.get(i).get("id_producto");
            String m = (String) p.get(i).get("nombre");
            double precioUnitario = Double.parseDouble(((BigDecimal) p.get(i).get("precio_unitario")).toString());
            Producto producto = new Producto(idProducto, m, precioUnitario, proveedor);
            int idBodega = (int) p.get(i).get("id_bodega");
            String nombreBodega = (String) p.get(i).get("nombre_bodega");
            String ubicacion = (String) p.get(i).get("ubicacion");
            Bodega bo = new Bodega(idBodega, nombreBodega, ubicacion);

            Movimiento temp = new Movimiento(idMovimiento, tipo, cantidad, fecha, producto, bo);
            list.add(temp);

        }
        return list;

    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public Bodega getBodega() {
        return bodega;
    }

    @Override
    public String toString() {
        return idMovimiento + "," + tipo + "," + fecha + "," + producto.getNombre() + "," + bodega.getNombreBodega();
    }

}
