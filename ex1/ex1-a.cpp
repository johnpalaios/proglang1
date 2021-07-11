#include <bits/stdc++.h>
using namespace std;


bool comparison(const pair<int, int>& a, const pair<int, int>& b)
{
    if (a.first == b.first)
        return a.second < b.second;

    return a.first < b.first;
}


int index(vector<pair<int, int> >& preSum, int n, int val)
{


    int l = 0;
    int h = n - 1;
    int mid;


    int ans = -1;


    while (l <= h) {
        mid = (l + h) / 2;
        if (preSum[mid].first <= val) {
            ans = mid;
            l = mid + 1;
        }
        else
            h = mid - 1;
    }

    return ans;
}


int longest(int arr[], int n, int x)
{
    int i;

    for (i = 0; i < n; i++)
    {
        arr[i] -= x;
    }

    int maxlength = 0;


    vector<pair<int, int> > prefixSum;


    int sum = 0;


    int minInd[n];

    for (i = 0; i < n; i++) {
        sum = sum + arr[i];
        prefixSum.push_back({ sum, i });
    }

    sort(prefixSum.begin(), prefixSum.end(), comparison);
    minInd[0] = prefixSum[0].second;

    for (i = 1; i < n; i++) {
        minInd[i] = min(minInd[i - 1], prefixSum[i].second);
    }
    sum = 0;
    for (i = 0; i < n; i++) {
        sum = sum + arr[i];


        if (sum >= 0)
            maxlength = i + 1;


        else {
            int ind = index(prefixSum, n, sum);
            if (ind != -1 && minInd[ind] < i)
                maxlength = max(maxlength, i - minInd[ind]);
        }
    }

    return maxlength;
}
int main(int argc,char **argv)
{
    if(argc==1)
    {
      printf("Usage : ./longest infile1\nPlease try again!\n");
      exit(1);
    }
    string filename(argv[1]);
    vector<int> numbers;
    int number;

    ifstream input_file(filename);

    if (!input_file.is_open()) {
           cout << "Could not open the file - '" << filename <<"'\n";
        return 0;
    }

    while (input_file >> number) {
        numbers.push_back(number);
    }
    int n,m;
    m = 10;
    for(int i = 0;i<1000000;i++)
    {
     n = m+1;
    }
    input_file.close();
    n = numbers[0];
    m = numbers[1];

    int d[n];

    for(size_t i=2; i < numbers.size(); i++)
    {
      d[i-2] =  - numbers[i];
    }

    int result = longest(d,n,m);
    printf("%d\n",result);
    return 0;
}