/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
/**
 *
 * @author Usuario
 */
public class Compra implements InterfazDataBase {

    private int idCompra;
    private Timestamp fecha;
    private Bodega bodega;
    private Proveedor proveedor;
    public static final Compra INSTANCE = new Compra();

    public Compra(int idCompra, Timestamp fecha, Proveedor proveedor, Bodega bodega) {
        this.idCompra = idCompra;
        this.fecha = fecha;
        this.bodega = bodega;
        this.proveedor = proveedor;
    }

    public Compra(Timestamp fecha, Proveedor proveedor, Bodega bodega) {
        this.fecha = fecha;
        this.bodega = bodega;
        this.proveedor = proveedor;
    }

    private Compra() {
    }

    @Override
    public void insert(Object value) {
        Compra p = (Compra) value;
        Conexion.ejecutar("INSERT INTO compras (fecha, id_proveedor, id_bodega) VALUES ('" + p.getFecha().toString() + "' , " + p.getProveedor().getIdProveedor() + ", " + p.getBodega().getIdBodega() + ")");
    }

    @Override
    public void update(Object value) {
        Compra p = (Compra) value;
        Conexion.ejecutar("UPDATE compras SET fecha = '" + p.getFecha().toString() + "', id_proveedor = " + p.getProveedor().getIdProveedor() + ", id_bodega = " + p.getBodega().getIdBodega() + " WHERE id_compra = " + p.getIdCompra());
    }

    @Override
    public void delete(Object value) {
        Compra p = (Compra) value;
        Conexion.ejecutar("DELETE FROM compras WHERE id_compra = " + p.getIdCompra());
    }

    public ArrayList<Compra> getList() {
        ArrayList<Compra> list = new ArrayList<>();
        List<Map<String, Object>> p = Conexion.query("SELECT * FROM compras c INNER JOIN proveedores p ON p.id_proveedor = c.id_proveedor INNER JOIN bodegas b ON b.id_bodega = c.id_bodega;");
        for (int i = 0; i < p.size(); i++) {
            int idCompra = (int) p.get(i).get("id_compra");
            Timestamp fecha = (Timestamp) p.get(i).get("fecha");
            int idProveedor = (int) p.get(i).get("id_proveedor");
            String nombreEmpresa = (String) p.get(i).get("nombre_empresa");
            String ruc = (String) p.get(i).get("ruc");
            String email = (String) p.get(i).get("email");
            Proveedor proveedor = new Proveedor(idProveedor, nombreEmpresa, ruc, email);
            int idBodega = (int) p.get(i).get("id_bodega");
            String nombreBodega = (String) p.get(i).get("nombre_bodega");
            String ubicacion = (String) p.get(i).get("ubicacion");
            Bodega bo = new Bodega(idBodega, nombreBodega, ubicacion);

            Compra temp = new Compra(idCompra, fecha, proveedor, bo);
            list.add(temp);

        }
        return list;

    }

    public int getIdCompra() {
        return idCompra;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public Bodega getBodega() {
        return bodega;
    }

}
