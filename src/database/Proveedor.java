/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Usuario
 */
public class Proveedor implements InterfazDataBase {

    private int idProveedor;
    private String nombreEmpresa;
    private String ruc;
    private String email;
    public static final Proveedor INSTANCE = new Proveedor();

    public Proveedor(int idProveedor, String nombreEmpresa, String ruc, String email) {
        this.idProveedor = idProveedor;
        this.nombreEmpresa = nombreEmpresa;
        this.ruc = ruc;
        this.email = email;
    }

    public Proveedor(String nombreEmpresa, String ruc, String email) {
        this.nombreEmpresa = nombreEmpresa;
        this.ruc = ruc;
        this.email = email;
    }

    private Proveedor() {
    }

    @Override
    public void insert(Object value) {
        Proveedor b = (Proveedor) value;
        Conexion.ejecutar("INSERT INTO proveedores (nombre_empresa, ruc, email) VALUES ('" + b.getNombreEmpresa() + "' , '" + b.getRuc() + "', ' " + b.getEmail() + "')");
    }

    @Override
    public void update(Object value) {
        Proveedor b = (Proveedor) value;
        Conexion.ejecutar("UPDATE proveedores SET nombre_empresa = '" + b.getNombreEmpresa() + "', ruc = '" + b.getRuc() + "', email = '" + b.getEmail() + "' WHERE id_proveedor = " + b.getIdProveedor());
    }

    @Override
    public void delete(Object value) {
        Proveedor b = (Proveedor) value;
        Conexion.ejecutar("DELETE FROM proveedores WHERE id_proveedor = " + b.getIdProveedor());
    }

    public ArrayList<Proveedor> getList() {
        ArrayList<Proveedor> list = new ArrayList<>();
        List<Map<String, Object>> b = Conexion.query("SELECT * FROM proveedores");
        for (int i = 0; i < b.size(); i++) {
            int idProveedor = (int) b.get(i).get("id_proveedor");
            String nombreEmpresa = (String) b.get(i).get("nombre_empresa");
            String ruc = (String) b.get(i).get("ruc");
            String email = (String) b.get(i).get("email");

            Proveedor temp = new Proveedor(idProveedor, nombreEmpresa, ruc, email);
            list.add(temp);

        }
        return list;

    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getRuc() {
        return ruc;
    }

    public String getEmail() {
        return email;
    }
    
    @Override
    public String toString(){
        return nombreEmpresa;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Proveedor other = (Proveedor) obj;
        return this.idProveedor == other.idProveedor; // Cambia 'id' por el nombre de tu atributo clave
    }
 
    
}
