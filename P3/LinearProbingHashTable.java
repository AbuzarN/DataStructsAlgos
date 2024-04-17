import java.util.*;
import java.io.*;
public class LinearProbingHashTable <K,V> {
    public static void main(String[] args) throws Exception {
        LinearProbingHashTable <Integer, String> table= new LinearProbingHashTable<>(7);
        String input;
        

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while ( (input = in.readLine()) != null)
        {
            String []s = input.split(" ");
            switch (s[0])
            {
                case "P":
                    if (s.length < 3){
                        System.out.println("Too few args");
                        break;
                    }
                    if(!table.put( Integer.parseInt(s[1]), s[2])){
                        System.out.println("Could Not Insert " + s[1]);
                    }
                    break;

                case "G":
                    if (table.get(Integer.parseInt(s[1])) != null){
                        System.out.println(table.get(Integer.parseInt(s[1])));
                    }
                    else{
                        System.out.println("Not Found");
                    }
                    
                    break;

                case "D":
                    if (!table.delete(Integer.parseInt(s[1]))){
                        System.out.println(s[1] + "Not Found");
                    }
                    break;

                case "K":
                    Set<Integer> keySet = null;
                    keySet = table.keySet();
                    Iterator<Integer> itr = keySet.iterator();
                    while(itr.hasNext()){
                        System.out.print(itr.next() + " ");
                    }
                    System.out.println();
                    break;

                case "R":
                    System.out.print(table.size + " ");
                    table.rehash();
                    System.out.println(table.size);
                    break;

                case "S":
                    System.out.print(table.toString());
                    break;

                default:
                    System.out.println("Incorrect input");
                    break;
            }
        }
        in.close();
    }
    private class Entry<K ,V>{
        K key;
        V value;
        boolean removed;

        Entry(K key, V value){
            this.key = key;
            this.value = value;
            removed = false;
        }
    }
    Entry<K, V>[] table;
    private int size;
    private int num;

    LinearProbingHashTable(int size){
        this.size = size;
        num = 0;
        table = new Entry[size];
    }

    public boolean put(K key, V value){

        if ((double) num/size > 0.5){
            rehash();
        }

        int index = key.hashCode();

        for (int i = 0; i <size; i++){
            int modifiedIndex= (index + i) %size;
            if (table[modifiedIndex] == null){
                Entry<K,V> temp = new Entry<>(key, value);
                table[modifiedIndex] = temp;
                num++;
                return true;

            }else if (table[modifiedIndex].removed && table[modifiedIndex].value == value){
                table[modifiedIndex].removed = true;
                return true;
            }else if (table[modifiedIndex].key == key){
                return false;
            }
        }

        return true;
    }

    public V get(K key) {

        int index = key.hashCode();

        for (int i = 0; i <size; i++){
            int modifiedIndex= (index + i) % size;
            if (table[modifiedIndex] != null){
                if (table[modifiedIndex].key == key){
                    return table[modifiedIndex].value;
                }
            }
        }


        return null;
    }

    public boolean delete(K key) {
        int index = key.hashCode();
        for (int i = 0; i <size; i++){
            int modifiedIndex= (index + i) %size;
            if (table[modifiedIndex] != null){
                if (table[modifiedIndex].key == key && !table[modifiedIndex].removed){
                    table[modifiedIndex].removed = true;
                    return true;
                }
            }
        }
        return false;
    }

    public Set<K> keySet(){
        Set<K> keySet = new HashSet<>();
        for (int i = 0; i< size; i++){
            if (table[i]!= null){
                if (!table[i].removed){
                    keySet.add(table[i].key);
                }
            }
        }

        return keySet;
    }

    private void rehash() {
        Entry<K, V>[] newTable;
        newTable =  new Entry[size*2+3]; 
        for (int i = 0; i<size; i++){
            if (table[i] != null && !table[i].removed){
                int index = table[i].key.hashCode();
                while (newTable[index] != null){
                    index= (index + i) % size;
                }
                newTable[index] = table[i];
            }
        }
        table = newTable;
        size = size*2+3;
        
    }

    public String toString(){
        String r = "";
        for (int i = 0; i < size; i++){
            if (table[i] == null){
                r += i + "\n";
            }else{
                r+= i + " " + table[i].key + ", " + table[i].value;
                if (table[i].removed){
                    r += "   deleted";
                }

                if (i<size-1){
                    r+="\n"; 
                }
                
            }  
        }
        return r;
    }
}
