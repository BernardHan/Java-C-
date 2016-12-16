/*  Name: Zhaoyang Han
 *  loginid: zhaoyanh
 *  CS 455 Fall 2016, Extra Credit assignment
 *
 *  See ecListFuncs.h for specification of each function.
 */

#include <iostream>

#include <cassert>

#include "ecListFuncs.h"

using namespace std;



/*
 * isInOrder
 *
 * Pre: list is a well-formed list.
 *
 * returns true iff the elements in the list are in increasing order
 *  (duplicate elements allowed)
 *
 * Examples:
 *  list                   isInOrder(list)
 *
 *    ()                    true
 *    (2)                   true
 *    (7 2 2 3)             false
 *    (2 2 3 7)             true
 *    (2 2 3 3 3 4 4 4 4)   true
 *    (-12 0 7 10)          true
 *    (-12 0 7 5)           false
 */
bool isInOrder(ListType list) {
  bool result = true;
  Node *pointer = list;
  int previous;
  if(pointer != NULL){
    previous = pointer->data;
    pointer = pointer->next;
  }
  else{
    return result;
  }
  while(pointer != NULL){
    if(pointer->data < previous){
      result = false;
      return result;
    }
    previous = pointer->data;
    pointer = pointer->next;
  }

  return result;
}



/*
 * insertInOrder
 *
 * PRE: list is a well-formed list and isInOrder(list) [see function above]
 *  and itemP is a pointer to a single node (i.e., itemP->next == NULL)
 *
 * inserts the new node into the ordered list.
 *
 * POST: list' includes itemP, and isInOrder(list)
 * (so a client should not change the node pointed to by itemP after using this
 *  function, because it will invalidate the list)
 *
 * NOTE: this function does not create any new nodes.
 *
 * Examples:
 *
 *  list          itemP    list'
 *    ()          (2)      (2)
 *    (7)         (2)      (2 7)
 *    (1 3 5)     (4)      (1 3 4 5)
 *    (1 3 5)     (-12)    (-12 1 3 5)
 *    (1 3 5 9)   (12)     (1 3 5 9 12)
 *    (1 3 5 9)   (3)      (1 3 3 5 9)
 */
void insertInOrder(ListType & list, Node *itemP) {
  assert(isInOrder(list));     // checks the preconditions
  assert(itemP->next == NULL);
  // add the rest of the code after this line
  Node * pointer = list;
  if(pointer == NULL){
    list = itemP;
    assert(isInOrder(list));     // checks the preconditions
    return;
  }
  // when list not empty
  int data = itemP->data;
  Node * previous = pointer;
  while(pointer != NULL){
    if(data <= pointer->data){
      // insert the item before this node
      break;
    }

    previous = pointer;
    pointer = pointer->next;
  }

  // if previous == pointer, then means item insert at the front
  if(previous == pointer){
    itemP->next = list;
    list = itemP;

    assert(isInOrder(list));     // checks the preconditions
    return;
  }
  // previous -> item -> pointer
  previous->next = itemP;
  itemP->next = pointer;

  assert(isInOrder(list));     // checks the preconditions
  return;
}



/*
 * insertionSort
 *
 * PRE: list is a well-formed list
 *
 * sorts the list in increasing order using the insertion sort algorithm
 *
 * POST: isInOrder(list)
 *
 * NOTE: this function does not create or delete any nodes, but relinks up the
 * nodes in the original list.
 *
 * Examples:
 *
 *  list            list'
 *    ()            ()
 *    (7)           (7)
 *    (1 3 5)       (1 3 5)
 *    (8 2 7 9 5)   (2 5 7 8 9)
 *    (3 1 3 12 2)  (1 2 3 3 12)
 */
void insertionSort(ListType &list) {
  
  Node * sortedHead = NULL;
  Node * pointer = list;
  Node * item = NULL;

  while(pointer != NULL){
    // need to make pointer ->next = null before insert
    item = pointer;
    pointer = pointer->next;
    item->next = NULL;
    insertInOrder(sortedHead, item);
  }

  list = sortedHead;
  assert(isInOrder(list));     // checks the preconditions
}




/*
 * splitEvenOdd
 *
 * PRE: list is a well-formed list
 *
 * splits list into two sub-lists. "a" will contain the first, third,
 * fifth, etc.  element from list.  And "b" will contain the second,
 * fourth, sixth, etc. element from list.  So, if there are an odd number
 * of nodes in list, "a" will end up with one more node than "b".  This
 * operation will destroy the list, because it reuses nodes from the
 * original list.  After the operation, list will be NULL.
 *
 * NOTE: this function does not create or delete any nodes, but reuses
 * nodes from the original list.
 *
 * Examples:
 *
 *  list           a        b
 *    ()           ()      ()
 *    (7)          (7)     ()
 *    (7 2)        (7)     (2)
 *    (1 3 5)      (1 5)   (3)
 *    (1 2 3 4)    (1 3)   (2 4)
 *    (1 2 3 4 5)  (1 3 5) (2 4)
 */
void splitEvenOdd(ListType &list, ListType &a, ListType &b){
  int count = 1;
  Node * item = NULL;
  Node * pointer = list;
  while(pointer != NULL){
    item = pointer;
    pointer = pointer->next;
    item->next = NULL;
    if(count % 2 == 1){
      // odd
      insertInOrder(a, item);
    }
    else{
      insertInOrder(b, item);
    }
    count++;
  }
  list = NULL;
}
