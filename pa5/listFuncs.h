// Name: Zhaoyang Han
// Loginid: zhaoyanh
// CSCI 455 PA5
// Fall 2016


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.


#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
  
using namespace std;

struct Node {
  string key;
  int value;

  Node *next;

  Node(const string &theKey, int theValue);

  Node(const string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

// remove target from list
bool listRemove(ListType& list, string target);

// insert target item into the end of list
bool listInsert(ListType & list, string item, int value);

// get the target node from the list
Node * getNode(const ListType& list, string target);

// print all the nodes in the list
void printEntry(const ListType& list);

// count the number of nodes in this list
int numNodes(const ListType& list);

// get the node before the target node from this list
Node * getNodeBefore(const ListType& list, string target);





// keep the following line at the end of the file
#endif
