package com.example.itemshopping.util;

public class Utility {
    //Metodo para obtener el valor del nombre del item segun ASCII
    public static int convertASCII(String nameProduct){
        int result = 0;
        //Quitamos los espacios del String
        nameProduct = nameProduct.trim();
        //Despues generamos un arreglo de caracteres de las letras del String
        char productChars[] = nameProduct.toCharArray();
        //Se obtiene el valor de las letras segun ASCII
        for (int i = 0; i < productChars.length; i++) {
            switch (String.valueOf(productChars[i]).toUpperCase()){
                case "A":
                    result += 65;
                    break;
                case "B":
                    result += 66;
                    break;
                case "C":
                    result += 67;
                    break;
                case"D":
                    result += 68;
                    break;
                case"E":
                    result += 69;
                    break;
                case"F":
                    result += 70;
                    break;
                case"G":
                    result += 71;
                    break;
                case"H":
                    result += 72;
                    break;
                case"I":
                    result += 73;
                    break;
                case"J":
                    result += 74;
                    break;
                case"K":
                    result += 75;
                    break;
                case"L":
                    result += 76;
                    break;
                case"M":
                    result += 77;
                    break;
                case"N":
                    result += 78;
                    break;
                case"Ñ":
                    result += 64;
                    break;
                case"O":
                    result += 79;
                    break;
                case"P":
                    result += 80;
                    break;
                case"Q":
                    result += 81;
                    break;
                case"R":
                    result += 82;
                    break;
                case"S":
                    result += 83;
                    break;
                case"T":
                    result += 84;
                    break;
                case"U":
                    result += 85;
                    break;
                case"V":
                    result += 86;
                    break;
                case"W":
                    result += 87;
                    break;
                case"X":
                    result += 88;
                    break;
                case"Y":
                    result += 89;
                    break;
                case"Z":
                    result += 90;
                    break;
                default:
                    result += 1;
            }
        }
        //Para obtener la posicion en el hash table se divide el resultado por el tamaño del String
        return result/nameProduct.length();
    }
}
