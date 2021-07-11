#include <iostream>
#include <fstream>
using namespace std;
int counter = 0;
const int maxr=1000;
const int maxc=1000;

bool findout(char a[maxr][maxc]
          ,int i,int j
          ,int b[maxr][maxc]
          ,bool visited[maxr][maxc])
{
  if(b[i][j] == 1) return true;
  if(visited[i][j] == 1) return false;
  visited[i][j] = 1;
  int i1 = i,j1 = j;
  // check for different letters
  if(a[i][j] == 'U') 
    i1--;
  if(a[i][j] == 'D') 
    i1++;
  if(a[i][j] == 'L') 
    j1--;
  if(a[i][j] == 'R') 
    j1++;
  // we solve it ~kinda recursively
  b[i][j] = findout(a,i1,j1,b,visited);
  return b[i][j];
}
int main(int argc,char **argv)
{
    //if : ./loop_rooms (without infile1)
    if(argc==1) 
    {
      cout << "Usage : ./loop_rooms infile1\nPlease try again!\n";
      exit(1);
    }

    // argv[1] == the name of the file
    string filename(argv[1]);
    ifstream input_file(filename);
    if (!input_file.is_open()) {
        cerr << "Could not open the file - '"
             << filename << "'" << endl;
        return 0;
    }
    
    // r = rows & c = columns
    int r,c;
    input_file >> r >> c;
    //cout << r << " " << c << endl;
    
    // create a 2d array of char 
    // and store up,down,right,left
    char a[maxr][maxc];
    int b[maxr][maxc];
    bool visited[maxr][maxc];
    for(int i=0;i<r;++i)
    {
      for(int j=0;j<c;++j)
      {
        input_file >> a[i][j]; 
        b[i][j]=-1;
        visited[i][j] = 0;
      }
    }
    for(int i=0;i<c;++i)
    {
      if(a[0][i]=='U') b[0][i]=1;
      if(a[r-1][i]=='D') b[r-1][i]=1;
    }
    for(int i=0;i<r;++i)
    {
      if(a[i][0]=='L') b[i][0]=1;
      if(a[i][c-1]=='R') b[i][c-1]=1;
    }
    for(int i=0;i<r;++i)
    {
      for(int j=0;j<c;++j)
      {
        findout(a,i,j,b,visited);
      }
    }
    int n=0;
    for(int i=0;i<r;++i)
    {
      for(int j=0;j<c;++j)
      {
        if(b[i][j]==0) ++n;
      }
    }
    cout << n << endl;
    input_file.close();

}