import java.io.*;
import java.util.*;

public class DLeftHashTable {
    int[] hashTable;
    int[] s;
    int numofFlows;
    int numOfEntries;
    int segments;

    //Constructor for initializing the values for the DLeftHashTable
    DLeftHashTable(int numOfEntries, int numofFlows, int numOfHashes) {
        this.numOfEntries = numOfEntries;
        this.numofFlows = numofFlows;
        hashTable = new int[numOfEntries];
        s = new int[numOfHashes];
        this.segments = numOfEntries/numOfHashes;
        generateHash(s);
    }

    // Generate all the Unique Hash values
    private void generateHash(int[] s) {
        Set<Integer> uniqueHash = new HashSet<>();
        for(int i = 0; i < s.length; i++) {
            while(true) {
                int newHash = random();
                if(!uniqueHash.contains(newHash)) {
                    s[i] = newHash;
                    break;
                }
            }
        }
    }

    //Generate a random positive number greater than 1
    private int random(){
        Random random = new Random();
        return random.nextInt(Integer.MAX_VALUE - 1) + 1;
    }

    private int[] generateHashFunction(int flowID) {
        int[] result = new int[s.length];
        for(int i = 0; i < result.length; i++) {
            result[i] = flowID ^ s[i];
        }
        return result;
    }

    //Fill in the Hash Table with Flow ID's
    public int fillHashTable(){
        int totalCount = 0;
        Set<Integer> uniqueHashTable = new HashSet<>();
        for(int i = 0; i < numofFlows; i++) {
            int flowID = 0;
            // Generate Unique HashID's
            while(true) {
                flowID = random();
                if(!uniqueHashTable.contains(flowID)) {
                    break;
                }
            }
            int resultHash[] = generateHashFunction(flowID);
            for(int j = 0; j < resultHash.length; j++) {
                int index = (resultHash[j] % segments) + (j * segments);
                if(hashTable[index] == 0) {
                    hashTable[index] = flowID;
                    totalCount++;
                    break;
                }
            }
        }
        return totalCount;
    }

    public static void main(String arg[]) throws IOException {
        DLeftHashTable dht = new DLeftHashTable(1000,1000,4);
        if(arg.length == 3) {
            try {
                dht = new DLeftHashTable(Integer.parseInt(arg[0]),Integer.parseInt(arg[1]),Integer.parseInt(arg[2]));
            } catch(NumberFormatException nfe) {
                System.out.println("Please provide a valid Input");
            }
        }
        File fout = new File("OutputDLeftHashTable.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        bw.write(Integer.toString(dht.fillHashTable()));
        bw.newLine();
        for (int i = 0; i < dht.hashTable.length; i++) {
            bw.write(Integer.toString(dht.hashTable[i]));
            bw.newLine();
        }
        bw.close();
    }


}
