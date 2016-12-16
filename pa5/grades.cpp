// Name: Zhaoyang Han
// Loginid: zhaoyanh
// CSCI 455 PA5
// Fall 2016

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"
#include <string>
// cstdlib needed for call to atoi
#include <cstdlib>
#include <sstream>

void operation(Table * grades);

int main(int argc, char * argv[]) {

  // gets the hash table size from the command line

  int hashSize = Table::HASH_SIZE;

  Table * grades;  // Table is dynamically allocated below, so we can call
                   // different constructors depending on input from the user.

  if (argc > 1) {
    hashSize = atoi(argv[1]);  // atoi converts c-string to int

    if (hashSize < 1) {
      cout << "Command line argument (hashSize) must be a positive number" 
	   << endl;
      return 1;
    }

    grades = new Table(hashSize);

  }
  else {   // no command line args given -- use default table size
    grades = new Table();
  }


  grades->hashStats(cout);

  // add more code here
  // Reminder: use -> when calling Table methods, since grades is type Table*
  
  operation(grades); // start the operation loop
  
  return 0;
}

void operation(Table * grades) {
  string line, command, name, sscore;
  int score;
  // endless loop
  while(1) {
    cout << "cmd> ";
    getline(cin, line);
    istringstream stream(line);
    stream >> command;
    if(command == "quit"){
      //grades->clear();
      return;
    }
    else if(command == "print"){
      grades->printAll();
    }
    else if(command == "size"){
      cout << grades->numEntries() << endl;
    }
    else if(command == "stats"){
      grades->hashStats(cout);
    }
    else if(command == "insert"){
      // ask for name & score
      stream >> name;
      stream >> sscore;
      score = atoi(sscore.c_str()); // convert string to int
      if(!grades->insert(name, score)){
	cout << "The student is already in the list." << endl;
      }
    }
    else if(command == "change"){
      // ask for name & new score
      stream >> name;
      stream >> sscore;
      score = atoi(sscore.c_str());
      int *val = grades->lookup(name);
      if(val == NULL){
	// the student is not in the list
	cout << "This student is not in the list yet, please insert." << endl;
      }
      else{
	*val = score;
      }
    }
    else if(command == "lookup"){
      // ask for name
      stream >> name;
      int *val = grades->lookup(name);
      if(val == NULL){
	// the student is not in the list yet
	cout << "This student is not in the list yet." << endl;
      }
      else {
	cout << *val << endl;
      }
    }
    else if(command == "remove"){
      // ask for name
      stream >> name;
      if(!grades->remove(name)){
	// the student is not in the list
	cout << "This student is not in the list yet." << endl;
      }
    }
    else if(command == "help"){
      cout << "insert name score: Insert this name and score in the grade table." << endl;
      cout << "change name newscore: Change the score for name." << endl;
      cout << "lookup name: Lookup the name, and print out his or her score." << endl;
      cout << "remove name: Remove this student." << endl;
      cout << "print: Prints out all names and scores in the table." << endl;
      cout << "size: Prints out the number of entries in the table." << endl;
      cout << "stats: Prints out statistics about the hash table at this point." << endl;
      cout << "quit: Exits the program." << endl;
    }
    else{
      cout << "ERROR: invalid command, type 'help' for command summary" << endl;
    }
  }
}
