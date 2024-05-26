package com.example.itemshopping.domain;

public class Market {
    private int n;
    private Node items[];
    public Market(int n){
        this.n = n;
        this.items = new Node[n];
    }
    public void setElements(int n){
        this.n = n;
        this.items = new Node[n];
    }
    //Metodo que agrega un Item
    public boolean addItems(Item newItem){
        int valueASCII = com.example.itemshopping.util.Utility.convertASCII(newItem.getName());
        //Verificar que el item no existe para no volverlo a ingresarlo y que el valor del ASCII sea más pequeño que el tamaño de la hash table
        if (valueASCII < this.n) {
            Node productNode = new Node(newItem);
            if (items[valueASCII] == null) {
                items[valueASCII] = productNode;
            } else {
                Node aux = items[valueASCII];
                while (aux.next != null) {
                    aux = aux.next;
                }
                aux.next = productNode;
            }
            return true;
        }else{
            //Sino aumenta el tamaño de la hash table y vuelve a ingresar
            setElements(valueASCII+1);
            addItems(newItem);
        }
        return false;
    }
    //Metodo que elimina un Item
    public boolean deleteItems(Item deleteItem) {
        int valueASCII = com.example.itemshopping.util.Utility.convertASCII(deleteItem.getName());
        //Si la posicion obtenida no tiene elemento y esta nula no elimina nada
        if (items[valueASCII] == null) {
            return false;
        }
        //Verificar que el elemento exista para poder eliminarlo
        //if (searchItem(deleteItem.getName()).getName().equalsIgnoreCase(deleteItem.getName())) {
            Item tempItem = (Item) items[valueASCII].data;
            //Si es el primer elemento de la lista y no haya mas elementos para solo agregar un null
            //Sino el primer elemento se vuelve el que estaba de siguiente
            if (tempItem.getName().trim().equalsIgnoreCase(deleteItem.getName().trim())) {
                if (items[valueASCII].next != null) {
                    items[valueASCII] = items[valueASCII].next;
                } else {
                    items[valueASCII] = null;
                }
                return true;
                //Sino es el primer elemento, pero hay mas elementos los busca en todos los nodos hasta que lo encuentra
            } else if (items[valueASCII].next != null) {
                Node aux = items[valueASCII];
                Item itemTemp = (Item) aux.data;
                while (!(itemTemp.getName().trim().equalsIgnoreCase(deleteItem.getName().trim())) && aux != null) {
                    aux = aux.next;
                    itemTemp = (Item) aux.data;
                }
                if (itemTemp.getName().trim().equalsIgnoreCase(deleteItem.getName().trim())) {
                    aux.next = aux.next.next;
                    return true;
                }
            }
       // }
        //Sino lo elimina nada devuelve false
            return false;
    }

    //Metodo que busca un Item
   // public Item searchItem (String nameProduct){

   // }
    //Metodo que obtiene todos los Items
    public String getAllItems(){
        String result = "";
        //Recorre cada posicion de la hash table y recoge todos los elementos contenidos en esta
        //Solo no recorre la posicion si desde el inicio esta nula
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null){
                Node aux = items[i];
                while (aux != null){
                    Item tempItem = (Item)aux.data;
                    result += tempItem + "\n";
                    aux = aux.next;
                }
            }
        }
        return result;
    }
}
