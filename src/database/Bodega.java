/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Bodega implements InterfazDataBase {

    private int idBodega;
    private String nombreBodega;
    private String ubicacion;
    public static final Bodega INSTANCE = new Bodega();

    public Bodega(int idBodega, String nombreBodega, String ubicacion) {
        this.idBodega = idBodega;
        this.nombreBodega = nombreBodega;
        this.ubicacion = ubicacion;
    }

    public Bodega(String nombre, String ubicacion) {
        this.nombreBodega = nombre;
        this.ubicacion = ubicacion;
    }

    private Bodega() {
    }

    @Override
    public void insert(Object value) {
        Bodega b = (Bodega) value;
        Conexion.ejecutar("INSERT INTO bodegas (nombre_bodega, ubicacion) VALUES ('" + b.getNombreBodega() + "' , '" + b.getUbicacion() + "')");
    }

    @Override
    public void update(Object value) {
        Bodega b = (Bodega) value;
        Conexion.ejecutar("UPDATE bodegas SET nombre_bodega = '" + b.getNombreBodega() + "', ubicacion = '" + b.getUbicacion() + "' WHERE id_bodega = " + b.getIdBodega());
    }

    @Override
    public void delete(Object value) {
        Bodega b = (Bodega) value;
        Conexion.ejecutar("DELETE FROM bodegas WHERE id_bodega = " + b.getIdBodega());
    }

    public ArrayList<Bodega> getList() {
        ArrayList<Bodega> list = new ArrayList<>();
        List<Map<String, Object>> b = Conexion.query("SELECT * FROM bodegas");
        for (int i = 0; i < b.size(); i++) {
            int idBodega = (int) b.get(i).get("id_bodega");
            String nombreBodega = (String) b.get(i).get("nombre_bodega");
            String ubicacion = (String) b.get(i).get("ubicacion");

            Bodega temp = new Bodega(idBodega, nombreBodega, ubicacion);
            list.add(temp);

        }
        return list;

    }

    public int getIdBodega() {
        return idBodega;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public String getUbicacion() {
        return ubicacion;
    }
    
    @Override
    public String toString(){
        return nombreBodega;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Bodega other = (Bodega) obj;
        return this.idBodega == other.idBodega; // Cambia 'id' por el nombre de tu atributo clave
    }


}
