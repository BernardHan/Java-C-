#include <iostream>
#include <vector>
#include <string>
#include <sstream>
using namespace std;

vector<int> readVals();
void printVals(vector<int> v);



int main(){
  vector<int> v = readVals();
  printVals(v);
  return 0;
}

vector<int> readVals(){
  string line;
  getline(cin, line);
  vector<int> result;
  int token;
  istringstream stream(line);
  while(stream >> token){
    result.push_back(token);
  }
  return result;
}

void printVals(vector<int> v){

  for(int ind = 0; ind < v.size(); ind++){
    cout << v[ind] << " ";
  }
  cout << endl;

}
