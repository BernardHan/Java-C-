#include <iostream>
#include <vector>
#include <string>
#include <sstream>
using namespace std;

vector<int> readVals();
void printVals(vector<int>& v, vector<int>& fv);
vector<int> valsGT0(vector<int>& v);


int main(){
  vector<int> v = readVals();
  vector<int> fv = valsGT0(v);
  printVals(v, fv);
  return 0;
}

vector<int> valsGT0(vector<int>& v){
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

void printVals(vector<int>& v, vector<int>& fv){
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
