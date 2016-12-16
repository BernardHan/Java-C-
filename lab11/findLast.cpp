#include <iostream>
#include <vector>
#include <string>
#include <sstream>
using namespace std;

vector<int> readVals();
void printVals(const vector<int>& v, const vector<int>& fv);
vector<int> valsGT0(const vector<int>& v);
int findLast(const vector<int>& v, int target);
void testFindLast(const vector<int>& v);


int main(){

  const vector<int> v = readVals();

  const vector<int> fv = valsGT0(v);

  printVals(v, fv);
  
  int loc = findLast(v, 7);
  
  cout << loc << endl;
  //testFindLast(v);
  return 0;
}

void testFindLast(const vector<int>& v){
  

}

int findLast(const vector<int>& v, int target){
  int ind;
  for(ind = v.size() - 1; ind >= 0; ind--){
    if(target == v[ind]){
      return ind;
    }
  }
  return ind;
}


vector<int> valsGT0(const vector<int>& v){
  vector<int> result;
  for(int ind = 0; ind < v.size(); ind++){
    if(v[ind] > 0){
      result.push_back(v[ind]);
    }
  }
  return result;
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

void printVals(const vector<int>& v, const vector<int>& fv){
  cout << "Vector:";
  for(int ind = 0; ind < v.size(); ind++){
    cout << " " << v[ind];
  }
  cout << endl;

  cout << "Filtered vector:";
  for(int ind = 0; ind < fv.size(); ind++){
    cout << " " << fv[ind];
  }
  cout << endl;


  cout << "Original vector:";
  for(int ind = 0; ind < v.size(); ind++){
    cout << " " << v[ind];
  }
  cout << endl;

}
