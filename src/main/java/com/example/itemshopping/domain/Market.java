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
    public boolean addItems(Item newItem) {
        int valueASCII = com.example.itemshopping.util.Utility.convertASCII(newItem.getName());
        // Verificar que el item no existe para no volverlo a ingresar y que el valor del ASCII sea más pequeño que el tamaño de la hash table
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
            // Sino aumenta el tamaño de la hash table y vuelve a ingresar
            setElements(valueASCII + 1);
            return addItems(newItem); // Debe retornar el resultado de la nueva llamada
        }
    }

    // Método que elimina un Item
    public boolean deleteItems(Item deleteItem) {
        int valueASCII = com.example.itemshopping.util.Utility.convertASCII(deleteItem.getName());
        // Si la posición obtenida no tiene elemento y está nula no elimina nada
        if (items[valueASCII] == null) {
            return false;
        }
        // Verificar que el elemento exista para poder eliminarlo
        if (searchItem(deleteItem.getName()) != null) {
            Node currentNode = items[valueASCII];
            // Si es el primer elemento de la lista y no hay más elementos, se elimina
            if (((Item)currentNode.data).getName().equalsIgnoreCase(deleteItem.getName())) {
                if (currentNode.next != null) {
                    items[valueASCII] = currentNode.next;
                } else {
                    items[valueASCII] = null;
                }
                return true;
            } else {
                // Sino es el primer elemento, pero hay más elementos, los busca en todos los nodos hasta que lo encuentra
                Node prev = currentNode;
                currentNode = currentNode.next;
                while (currentNode != null && !((Item)currentNode.data).getName().equalsIgnoreCase(deleteItem.getName())) {
                    prev = currentNode;
                    currentNode = currentNode.next;
                }
                if (currentNode != null) {
                    prev.next = currentNode.next;
                    return true;
                }
            }
        }
        // Si no lo encuentra, devuelve false
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
            if (tempItem.getName().equalsIgnoreCase(nameProduct)) {
                return tempItem;
            }
            aux = aux.next;
        }
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

