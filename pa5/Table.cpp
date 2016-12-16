// Name: Zhaoyang Han
// Loginid: zhaoyanh
// CSCI 455 PA5
// Fall 2016

// Table.cpp  Table class implementation


/*
 * Modified 11/22/11 by CMB
 *   changed name of constructor formal parameter to match .h file
 */

#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {
  hashSize = HASH_SIZE;
  table = new ListType[hashSize];
  // initialize
  for(int i = 0; i < hashSize; i++){
    table[i] = NULL;
  }
}


Table::Table(unsigned int hSize) {
  hashSize = hSize;
  table = new ListType[hashSize];
  // initialize
  for(int i = 0; i < hashSize; i++){
    table[i] = NULL;
  }
}

// returns the address of the value or NULL if key is not present
int * Table::lookup(const string &key) {
  int * result = NULL;
  // first find the hashcode of key, ie: the index of table
  int index = hashCode(key);

  Node * pointer = table[index]; // set a iterator pointer to the start of the list

  while(pointer != NULL){
    if(pointer->key == key){
      // found the key
      result = &(pointer->value); // assign the address of the value in the node to the result
      break;
    }
    pointer = pointer->next;
  }
  return result;
}

// remove an element
// false iff element wasn't present
bool Table::remove(const string &key) {
  int index = hashCode(key); // find the index of table
  ListType *list = &(table[index]); // get the whole chain
  
  return listRemove(*list, key); // may need test later          <------
}

// insert a new pair into the table
// return false iff this key was already present 
//         (and no change made to table)
bool Table::insert(const string &key, int value) {
  int index = hashCode(key);
  ListType *list = &(table[index]); // need to modify the address of this table index
  return listInsert(*list, key, value);
}

// number of entries in the table
int Table::numEntries() const {
  int ind = 0;
  int num = 0;
  ListType list = NULL;
  for(ind = 0; ind < hashSize; ind++){
    if(table[ind] != NULL){
      // here is a non empty list, count the num
      list = table[ind];
      num += numNodes(list);
    }
  }
  
  return num;
}

// print out all the entries in the table, one per line.
// Sample output:
//   joe 32
//   sam 273
//   bob 84
void Table::printAll() const {
  int ind = 0;
  ListType list = NULL;

  for(ind = 0; ind < hashSize; ind++) {
    if(table[ind] != NULL) {
      // here is an entry!
      list = table[ind];
      cout << ind << ": ";
      printEntry(list);
    }
  }
}


// hashStats: for diagnostic purposes only
// prints out info to let us know if we're getting a good distribution
// of values in the hash table.
// Sample output from this function
//   number of buckets: 997
//   number of entries: 10
//   number of non-empty buckets: 9
//   longest chain: 2
void Table::hashStats(ostream &out) const {
  int numEntry = numEntries();
  int max = findMax();
  int numBucket = numBuckets();

  cout << "Number of buckets: " << hashSize << endl;
  cout << "Number of entries: " << numEntry << endl;
  cout << "Number of non-empty buckets: " << numBucket << endl;
  cout << "Longest chain: " << max << endl;
}


// add definitions for your private methods here
int Table::findMax() const {
  int max = 0;
  int ind = 0;
  int num = 0;
  ListType list = NULL;

  for(ind = 0; ind < hashSize; ind++){
    if(table[ind] != NULL) {
      // there is a non empty bucket
      list = table[ind];
      num = numNodes(list); // call the function from listFunction
      max = num >= max ? num : max;
    }
  }

  return max;
}

int Table::numBuckets() const {
  int num = 0;
  int ind = 0;
  for(ind = 0; ind < hashSize; ind++){
    if(table[ind] != NULL){
      // there is a non empty bucket, count it
      num++;
    }
  }
  return num;
}
