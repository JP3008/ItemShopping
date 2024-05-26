package com.example.itemshopping.domain;

public class Market {
    private int n;
    private Node[] items;

    public Market(int n) {
        this.n = n;
        this.items = new Node[n];
    }

    public void setElements(int n) {
        this.n = n;
        this.items = new Node[n];
    }

    // Método que agrega un Item
    //Metodo que agrega un Item
    public boolean addItems(Item newItem){
        int valueASCII = com.example.itemshopping.util.Utility.convertASCII(newItem.getName());
        //Verificar que el item no existe para no volverlo a ingresarlo y que el valor del ASCII sea más pequeño que el tamaño de la hash table
        if (searchItem(newItem.getName())==null) {
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
            } else {
                //Sino aumenta el tamaño de la hash table y vuelve a ingresar
                setElements(valueASCII + 1);
                addItems(newItem);
            }
        }
        return false;
    }

    //Metodo que elimina un Item
    public boolean deleteItems(Item deleteItem) {
        int valueASCII = com.example.itemshopping.util.Utility.convertASCII(deleteItem.getName());
        //Verificar que el elemento exista para poder eliminarlo y que la posicion inicial no sea nula
        if (items[valueASCII] != null && searchItem(deleteItem.getName()) != null) {
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
                while (!(itemTemp.getName().trim().equalsIgnoreCase(deleteItem.getName().trim()))) {
                    aux = aux.next;
                    itemTemp = (Item) aux.next.data;
                }
                if (itemTemp.getName().trim().equalsIgnoreCase(deleteItem.getName().trim())) {
                    aux.next = aux.next.next;
                    return true;
                }
            }
        }
        //Sino elimina nada devuelve false
        return false;
    }

    // Método que busca un Item
    public Item searchItem(String nameProduct) {
        int valueASCII = com.example.itemshopping.util.Utility.convertASCII(nameProduct);
        if (valueASCII >= this.n || items[valueASCII] == null) {
            return null;
        }
        Node aux = items[valueASCII];
        while (aux != null) {
            Item tempItem = (Item) aux.data;
            if (tempItem.getName().trim().equalsIgnoreCase(nameProduct.trim())) {
                return tempItem;
            }
            aux = aux.next;
        }
        return null;
    }
    public Item shopping(Item itemBuy){
        int valueASCII = com.example.itemshopping.util.Utility.convertASCII(itemBuy.getName());
        //Si en la posicion hay nodos hace la posible compra sino retorna nulo
        if (items[valueASCII] != null && searchItem(itemBuy.getName())!=null) {//Meter validacion con el search tambien
            Item tempFirst = (Item) items[valueASCII].data;
            //Validación poder si el elemento no esta en la posicion inicial
            if (tempFirst.getName().trim().equalsIgnoreCase(itemBuy.getName().trim())) {
                //Si no hay suficiente cantidad retorna nulo
                if (tempFirst.getQuantity() - itemBuy.getQuantity() < 0) {
                    return new Item(itemBuy.getName(),0,tempFirst.getQuantity());
                } else {
                    //Sino ajusta la cantidad de items que hay y lo introduce nuevamente en la lista
                    tempFirst.setQuantity(tempFirst.getQuantity() - itemBuy.getQuantity());
                    //Si se actualizaron la cantidad y esta queda en 0 se elimina el item de la lista
                    if (tempFirst.getQuantity() == 0) {
                        items[valueASCII] = items[valueASCII].next;
                    }else {
                        //Sino sigue habiendo solo se actualiza la cantidad
                        items[valueASCII].data = tempFirst;
                    }
                    //Se devuelve el item, pero con el valor que tiene segun el inventario
                    itemBuy = new Item(itemBuy.getName(), tempFirst.getValue(), itemBuy.getQuantity());
                    return itemBuy;
                }
            } else{
                //Sino se hace un nodo auxiliar con la siguiente posicion al primero
                Node aux = items[valueASCII].next;
                Item tempOther = (Item) aux.data;
                //Se compara los nombre o hasta que llegue al final de la lista de nodos
                while (!(tempOther.getName().trim().equalsIgnoreCase(itemBuy.getName().trim()))) {
                    tempOther = (Item)aux.next.data;
                    aux = aux.next;
                }
                //Si cuando se sale del while coinciden los nombres del temporal con el item a comprar
                if (tempOther.getName().trim().equalsIgnoreCase(itemBuy.getName().trim())) {
                    //Si no hay suficiente cantidad retorna nulo
                    if (tempOther.getQuantity() - itemBuy.getQuantity() < 0) {
                        return new Item(itemBuy.getName(),0,tempFirst.getQuantity());
                    } else {
                        //Sino ajusta la cantidad de items que hay y lo introduce nuevamente en la lista
                        tempOther.setQuantity(tempOther.getQuantity() - itemBuy.getQuantity());
                        if (tempOther.getQuantity() == 0) {
                            aux.next = aux.next.next;
                        }else {
                            //Sino sigue habiendo solo se actualiza la cantidad
                            aux.next.data = tempOther;
                        }

                        //Se devuelve el item, pero con el valor que tiene segun el inventario
                        itemBuy = new Item(itemBuy.getName(), tempOther.getValue(), itemBuy.getQuantity());
                        return itemBuy;
                    }
                }
            }
        }
        //Si el item no se logro encontrar ya no que no hay se retorna nulo
        return null;
    }

    // Método que obtiene todos los Items
    public String getAllItems() {
        StringBuilder result = new StringBuilder();
        // Recorre cada posición de la hash table y recoge todos los elementos contenidos en esta
        // Solo no recorre la posición si desde el inicio está nula
        for (Node item : items) {
            if (item != null) {
                Node aux = item;
                while (aux != null) {
                    Item tempItem = (Item) aux.data;
                    result.append(tempItem).append("\n");
                    aux = aux.next;
                }
            }
        }
        return result.toString();
    }
}

