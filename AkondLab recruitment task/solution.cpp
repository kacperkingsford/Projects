#include <iostream>
#include <vector>
#include <algorithm>
#include <limits>

using namespace std;

void printVector(vector<int> &vec)
{
    for (int i = 0; i < vec.size(); i++)
    {
        cout << vec.at(i) << " ";
    }
}

void binarySearch(vector<pair<float, int>> B, int l, int r, float A, float &mindiff, int &index)
{
    if (r >= l)
    {
        int mid = l + (r - l) / 2;
        if (B[mid].first == A)
        {
            mindiff = 0;
            index = mid;
            return;
        }
        if (abs(B[mid].first - A) < mindiff)
        {
            mindiff = abs(B[mid].first - A);
            index = mid;
        }
        if (B[mid].first > A)
        {
            return binarySearch(B, l, mid - 1, A, mindiff, index);
        }
        else
        {
            return binarySearch(B, mid + 1, r, A, mindiff, index);
        }
    }
}
vector<pair<int, int>> findNeighbors(vector<float> A, vector<pair<float, int>> B)
{                             //let length of B is m and length of A is n
    sort(B.begin(), B.end()); //sort B in O(mlogm)
    vector<pair<int, int>> result;
    for (int i = 0; i < A.size(); i++) //for every element of A(n elements) we use binarysearch
    {                                  // binarysearch is O(logm) so whole loop is O(n*logm)
        int index;
        float curr_mindiff = numeric_limits<float>::max();
        binarySearch(B, 0, B.size() - 1, A.at(i), curr_mindiff, index);
        result.push_back(make_pair(curr_mindiff, B[index].second));
    }
    return result; //cosidering sorting and for-loop algorithm is O(mlogm + nlogm) = O(logm * max(n,m))
}
//naive implementation would be O(n*m)
//complexity of my solution is O(logm * max(n,m))
int main()
{
    vector<float> A;
    int a_length;
    cout << "Enter length of A list: ";
    cin >> a_length;

    vector<pair<float, int>> B;
    int b_length;
    cout << "Enter length of B list: ";
    cin >> b_length;

    float in;
    for (int i = 0; i < a_length; i++)
    {
        cin >> in;
        A.push_back(in);
    }
    for (int i = 0; i < b_length; i++)
    {
        cin >> in;
        B.push_back(make_pair(in, i));
    }
    vector<pair<int, int>> result = findNeighbors(A, B);
    vector<int> neighbors;
    vector<int> distances;
    for (auto it = std::make_move_iterator(result.begin()), //convert vector of pairs into neighbors and distances
              end = std::make_move_iterator(result.end());
         it != end; ++it)
    {
        neighbors.push_back(std::move(it->first));
        distances.push_back(std::move(it->second));
    }
    printVector(neighbors);
    cout << "\n";
    printVector(distances);

    return 0;
}
