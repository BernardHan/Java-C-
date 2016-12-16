// Name: Zhaoyang Han
// Loginid: zhaoyanh
// CSCI 455 PA5
// Fall 2016


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
  key = theKey;
  value = theValue;
  next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
  key = theKey;
  value = theValue;
  next = n;
}




//*************************************************************************
// put the function definitions for your list functions below

// remove the target item from list, return false if the list does not have this item, and remain list unchanged
bool listRemove(ListType& list, string target){
  Node * toDelete;
  // in case target is the first node
  if(list->key == target){
    toDelete = list; // store the to be deleted node
    list = list->next; // reconnect the node with the next next node
    delete toDelete; // reclaim the memory
    return true;
  }

  // if target is after the first node
  Node * targetNodeBefore = getNodeBefore(list, target); // get the node before target node

  if(targetNodeBefore == NULL) {
    // the list does not have it
    return false;
  }
  toDelete = targetNodeBefore->next; // catch the node to be deleted

  targetNodeBefore->next = targetNodeBefore->next->next; // assign the target to the next of target
  delete toDelete; // reclaim the memory
  
  return true;
}

// insert item into the last position of the list, return false if item is already existed, and remain list unchanged
bool listInsert(ListType & list, string item, int value){
  Node* pointer = list; // initial a pointer to iterate through the list

  if(pointer == NULL) {
    // empty
    // now assign the next of pointer to the item
    Node * newNode = new Node(item, value);
    list = newNode;

    return true;
  }
  
  while((pointer)->next != NULL){
    if((pointer)->key == item){
      // the list already contains the item
      return false;
    }
    pointer = (pointer)->next;
  }
  
  if((pointer)->key == item){
    return false;
  }
  
  // now assign the next of pointer to the item
  Node * newNode = new Node(item, value);
  (pointer)->next = newNode;

  return true;
}

// get the target node
Node * getNode(const ListType& list, string target){
  Node * targetNode = NULL;
  Node * pointer = list;
  while(pointer != NULL) {
    if(pointer->key == target) {
      // found the target
      targetNode = pointer;
      break;
    }
    pointer = pointer->next;
  }

  return targetNode;
}

// get the node before target node
Node * getNodeBefore(const ListType& list, string target){
  if(list == NULL){
    return NULL;
  }
  Node * result = NULL;
  Node * pointer = list;
  while(pointer->next != NULL) {
    if(pointer->next->key == target){
      // found the node before target
      result = pointer;
      break;
    }
    pointer = pointer->next;
  }

  return result;
}

// print all the items in this entry
void printEntry(const ListType& list){
  Node * pointer = list;

  while(pointer != NULL) {
    cout << pointer->key << ": " << pointer->value << "  ";
   
    pointer = pointer->next; 
  }
  cout << endl;
}

// calculate the number of nodes in this list
int numNodes(const ListType& list){
  int num = 0;
  Node * pointer = list;
  // for safe, check if null
  while(pointer != NULL){
    num++;
    pointer = pointer->next;
  }

  return num;
}
