/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

// import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Empresa {

    private ArrayList<Bodega> bodegas = new ArrayList<>();
    private ArrayList<Proveedor> proveedores = new ArrayList<>();
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Compra> compras = new ArrayList<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public Empresa() {
        updateBodegas();
        updateProveedores();
        updateProductos();
        updateCompras();
        updateMovimientos();

    }

    public ArrayList<Bodega> getBodegas() {
        return bodegas;
    }

    public void updateBodegas() {
        bodegas = Bodega.INSTANCE.getList();
    }

    public void updateCompras() {
        compras = Compra.INSTANCE.getList();
    }

    public void updateProveedores() {
        proveedores = Proveedor.INSTANCE.getList();
    }

    public void updateProductos() {
        productos = Producto.INSTANCE.getList();
    }

    public void updateMovimientos() {
        movimientos = Movimiento.INSTANCE.getList();
    }

    public ArrayList<Proveedor> getProveedores() {
        return proveedores;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }
}
