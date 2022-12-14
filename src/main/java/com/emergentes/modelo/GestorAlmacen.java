package com.emergentes.modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class GestorAlmacen {
    private ArrayList<Producto> lista;

    public GestorAlmacen() {
        this.lista = new ArrayList<Producto>();
    }

    public ArrayList<Producto> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Producto> lista) {
        this.lista = lista;
    }
    
    public void insertarProducto(Producto item){
        this.lista.add(item);
    }
    
    public void modificarProducto(int pos, Producto item){
        this.lista.set(pos, item);
    }
    
    public void eliminarProducto(int pos){
        this.lista.remove(pos);
    }
    
    public int obtieneId(){
        int idaux = 0;
        for (Producto item : lista) {
            idaux = item.getId();
        }
        return idaux + 1;
    }
    
    public int ubicarProducto(int id){
        int pos = -1;
        Iterator<Producto> it = lista.iterator();
        while(it.hasNext()){
            ++pos;
            Producto aux = it.next();
            if(aux.getId() == id){
                break;
            }
        }
        return pos;
    }
}
