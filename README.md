# MultiHashTable CuckooHashTable DLeftHashTable

# Description
In the project, I have implemented a multi-hashing table, Cuckoo hash table, and d-left hash table.

## Multi-hashing table

**Input**: number of table entries, number of flows, number of hashes – for demo, they are 1000, 1000 and 3, respectively

**Function**: generate flow IDs randomly, assume each flow has one packet, record one flow at a time into the hash table, and ignore the flows that cannot be placed into the hash table.  

**Output**: number of flows in the hash table, and the list of table entries (print out the flow ID if an entry has a flow or zero otherwise)  

## Cuckoo hash table

**Input**: number of table entries, number of flows, number of hashes, number of Cuckoo steps – for demo, they are 1000, 1000, 3, and 2, respectively

**Function**: generate flow IDs randomly, assume each flow has one packet, record one flow at a time into the hash table, and ignore the flows that cannot be placed into the hash table.  

**Output**: number of flows in the hash table, and the list of table entries (print out the flow ID if an entry has a flow or zero otherwise)  

## d-left hash table

**Input**: total number of table entries, number of flows, number of segments (hashes) – for demo, they are 1000, 1000, and 4 respectively; each segment has 250 table entries.

**Function**: generate flow IDs randomly, assume each flow has one packet, record one flow at a time into the hash table, and ignore the flows that cannot be placed into the hash table.  

**Output**: number of flows in the hash table, and the list of table entries (print out the flow ID if an entry has a flow or zero otherwise)  

# For running the file:
•	Extract the files from the folder.


•	They consist of three java files and three output files denoted to each hash table.


•	Run the files using the system having Java Run time environment setup. 


     javac MultiHashTable.java | CuckooHashTable.java | DLeftHashTable.java
     
     java MultiHashTable 1000 1000 3 | java CuckooHashTable 1000 1000 3 2 | java DLeftHashTable 1000 1000 4
     
Please pass the dynamic parameters otherwise default parameters will run.
The output file will be generated.

# Implementation of Hash Tables

## 1)	Multi-hashing table

Below are the input parameters for Demo: 

number of table entries: 1000

number of flows: 1000

number of hashes: 3

These input parameters are passed to the constructor.
Below is the structure of the program:

### •	MultiHashTable(): 
The constructor of the program initializes the values of entries in the array, number of flows, and the number of hashes. Then it calls the generateHash method.

### •	generateHash(int[] s): 
This method is called by the constructor of the MultiHashTable and assigns unique values to the number of hash values needed in the program.

### •	random(): 
This method uses the random class in java. According to the functionality I have assigned to the method it generates a random number between 1 to maximum integer value supported in java.

### •	generateHashFunction(int flowID): 
This method create the XOR values obtained and assigns for each flowID.

### •	fillHashTable(): 
This method is called for filling the hash table. It counts the number of flowid’s that are filled in the hash table and generates a unique set of flowID for processing.

It generates the output file “OutputMultiHashTable.txt” for storing the data of the has table. As per the project requirement, the first line is the count of flow ID’s in the hash table and the rest of the lines consist of the unique flow ID’s residing in the table. 
According to the output generated, the hash table filled using the Multi-hashing technique occupies around 820+ values in the hast table.

## 2)	Cuckoo hash table

Below are the input parameters for Demo: 

number of table entries: 1000

number of flows: 1000

number of hashes: 3

number of Cuckoo Steps: 2

These input parameters are passed to the constructor.

Below is the structure of the program:

### •	CuckooHashTable(): 
The constructor of the program initializes the values of entries in the array, number of flows, and the number of hashes. Then it calls the generateHash method.

### •	generateHash(int[] s): 
This method is called by the constructor of the CuckooHashTable and assigns unique values to the number of hash values needed in the program.

### •	random(): 
This method uses the random class in java. According to the functionality I have assigned to the method it generates a random number between 1 to maximum integer value supported in java.

### •	generateHashFunction(int flowID): 
This method create the XOR values obtained and assigns for each flowID.

### •	fillHashTable(int numofcuckoosteps): 
This method is called for filling the hash table. For the CuckooHashTable we pass the number of cuckoo steps it can take if the values that the flow Id is currently trying to use are occupied. It counts the number of flowid’s that are filled in the hash table and generates a unique set of flow id for processing.

### •	Move(Hash, numberOfCuckooSteps): 
This method is used to check if for the values that currently occupy the hash table for the current flow ID. Can they be moved to some other hash table position to empty the space? This check is performed for the number of cuckoo steps we can take. This moves the values into different entries and makes space for current flow id if possible, within the number of cuckoo steps

It generates the output file “OutputCuckooHashTable.txt” for storing the data of the has table. As per the project requirement, the first line is the count of flow ID’s in the hash table and the rest of the lines consist of the unique flow ID’s residing in the table. 
According to the output generated, the hash table filled using the Cuckoo-hashing technique occupies around 930+ values in the hast table. This gives us the best efficiency out of all the three hashing techniques. 

## 3)	d-left hash table

Below are the input parameters for Demo: 

number of table entries: 1000

number of flows: 1000

number of hashes (number of segments): 4

These input parameters are passed to the constructor.

Below is the structure of the program:

### •	DLeftHashTable(): 
The constructor of the program initializes the values of entries in the array, number of flows, and the number of hashes. Then it calls the generateHash method.

### •	generateHash(int[] s): 
This method is called by the constructor of the DLeftHashTable and assigns unique values to the number of hash values needed in the program.

### •	random(): 
This method uses the random class in java. According to the functionality I have assigned to the method it generates a random number between 1 to maximum integer value supported in java.

### •	generateHashFunction(int flowID): 
This method create the XOR values obtained and assigns for each flowID.

### •	fillHashTable(): 
This method is called for filling the hash table. It counts the number of flowid’s that are filled in the hash table and generates a unique set of flow id for processing. Here, the table entries are divided into segments and the priority of filling the flow ID is for the leftmost vacant hash table entry.

It generates the output file “OutputDLeftHashTable.txt” for storing the data of the has table. As per the project requirement the first line is the count of flow ID’s in the hash table and the rest of the lines consist of the unique flow ID’s residing in the table. 
According to the output generated, the hash table filled using the D-left-hashing technique occupies around 880+ values in the hast table.  
If we check for small output like 20, 20, and 4. We see it follows the property that 80% of the left table is filled and 20% of the right side of the table.
