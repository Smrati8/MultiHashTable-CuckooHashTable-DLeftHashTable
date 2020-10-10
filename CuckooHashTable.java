import java.lang.reflect.Array;
import java.util.*;

public class CuckooHashTable {
    int[] hashTable;
    int[] s;
    int numofFlows;
    int numOfEntries;
    Map<Integer, int[]> parentMap = new HashMap<>();

    //Constructor for initializing the values for the CuckooHashTable
    CuckooHashTable(int numOfEntries, int numofFlows, int numOfHashes) {
        this.numOfEntries = numOfEntries;
        this.numofFlows = numofFlows;
        hashTable = new int[numOfEntries];
        s = new int[numOfHashes];
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
        int result[] = new int[s.length];
        for(int i = 0; i < result.length; i++) {
            result[i] = flowID ^ s[i];
        }
        return result;
    }

    //Fill in the Hash Table with Flow ID's
    public int fillHashTable(int numOfCuckooSteps){
        int totalCount = 0;
        Set<Integer> uniqueHashTable = new HashSet<>();
        for(int i = 0; i < numofFlows; i++) {
            int flowID = 0;
            boolean found = false;
            // Generate Unique HashID's
            while(true) {
                flowID = random();
                if(!uniqueHashTable.contains(flowID)) {
                    break;
                }
            }
            int resultHash[] = generateHashFunction(flowID);
            parentMap.put(flowID, resultHash);
            for(int j = 0; j < resultHash.length; j++) {
                if(hashTable[resultHash[j] % numOfEntries] == 0) {
                    hashTable[resultHash[j] % numOfEntries] = flowID;
                    found = true;
                    totalCount++;
                    break;
                }
            }
            if(!found) {
                for(int j = 0; j < resultHash.length; j++) {
                    if(move(resultHash[j], numOfCuckooSteps)) {
                        hashTable[resultHash[j] % numOfEntries] = flowID;
                        totalCount++;
                        break;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(hashTable));
        return totalCount;
    }

    private boolean move(int newHashValue, int numOfCuckooSteps) {
        if(numOfCuckooSteps == 0) {
            return false;
        } else{
            int newFlowID = hashTable[newHashValue % numOfEntries];
            int resultHash[] = parentMap.get(newFlowID);
            for(int i = 0; i < resultHash.length; i++) {
                if(resultHash[i] != newHashValue && hashTable[resultHash[i] % numOfEntries] == 0) {
                    hashTable[resultHash[i] % numOfEntries] = newFlowID;
                    return true;
                }
            }

            for(int i = 0; i < resultHash.length; i++) {
                if(resultHash[i] != newHashValue && move(resultHash[i], numOfCuckooSteps - 1)) {
                    hashTable[resultHash[i] % numOfEntries] = newFlowID;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String arg[]) {
        CuckooHashTable cht = new CuckooHashTable(1000,1000,3);
        System.out.println(cht.fillHashTable(2));
    }


}

