//Задание 8.1
//Приведите пример использование хеш-таблиц.
// Используются, когда необходимо реализовать поиск, вставку и удаление элемента за константрое время.

//Задание 8.2
//Приведите примеры ключей и коллизий
//Размер массива - 7, ключи 10 и 17 попадают в ячейку 3 при хэш-функции ключ % размер массива, что является коллизией.

//Задание 8.3
//Приведите примеры популярных и эффективных хеш-функций.
//Открытая адресация и линейное пробирование, квадратичное пробирование, двойное хэширование.

// 8.4 , 8.5 в коде ниже

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] arr = new int[400];
        Random random = new Random();
        for (int i = 0; i < 400; i++) {
            arr[i] = random.nextInt(999);
        }

        Item item;
        int size = 800;

        HashTable ht =  new HashTable(size);

        for (int i = 0; i < arr.length; i++) {
            item = new Item(arr[i]);
            ht.insert(item);

        }
        ht.display();

    }

}
class Item{
    private int data;

    public Item(int data) {
        this.data = data;
    }
    public int getKey(){
        return this.data;
    }
}

class HashTable{
    private Item[] hashArr;
    private int arrSize;
    private Item nonItem;

    public HashTable(int size){
        this.arrSize = size;
        hashArr = new Item[arrSize];
        nonItem = new Item(-1);
    }

    public void display(){
        for (int i = 0; i < arrSize; i++) {
            if(hashArr[i]!=null){
                System.out.println(hashArr[i].getKey());
            } else {
                System.out.println("***");
            }

        }
    }
    public int hashFunc(int key){
        return key % arrSize;
    }
    public int hashFuncDouble(int key){return 7-key%7;}

    public void insert(Item item){
        int key = item.getKey();
        int hashVal = hashFunc(key);
        int step = hashFuncDouble(key);
        while (hashArr[hashVal] != null && hashArr[hashVal].getKey() != -1){
            hashVal+=step;
            hashVal %= arrSize;
        }
        hashArr[hashVal] = item;
    }

    public Item delete(int key){
        int hashVAl = hashFunc(key);
        int step = hashFuncDouble(key);
        while (hashArr[hashVAl] != null){
            if(hashArr[hashVAl].getKey() == key){
                Item temp = hashArr[hashVAl];
                hashArr[hashVAl] = nonItem;
                return temp;
            }

            hashVAl+=step;
            hashVAl %= arrSize;

        }
        return null;
    }

    public Item find(int key){
        int hashVal = hashFunc(key);
        int step = hashFuncDouble(key);
        while (hashArr[hashVal] != null){
            if(hashArr[hashVal].getKey() == key){
                return hashArr[hashVal];
            }
            hashVal+=step;
            hashVal %= arrSize;
        }
        return null;
    }

    private int getPrime(int min){
        for (int i = min+1; true; i++) {
            if(isPrime(i)){
                return i;
            }

        }
    }

    private boolean isPrime(int n){
        for (int i = 2; i*i <=n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
