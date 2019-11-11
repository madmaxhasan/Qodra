/* ====================================================== */
#include <bits/stdc++.h>
#define FileIn(file) freopen(file".txt", "r", stdin)
#define FileOut(file) freopen(file".txt", "w", stdout)
#define FOR(i, a, b) for(int i=a; i<=b; i++)
#define REP(i, n) for(int i=0; i<n; i++)
#define Fill(arr, val) memset(arr, val, sizeof arr)
#define All(arr) arr.begin(), arr.end()
#define ULL unsigned long long
#define LLI long long int
#define LL long long
#define PB push_back
#define MKP make_pair
#define MAX 105
#define MOD 1000000007
#define EPS 1e-6
#define INF 1llu<<61
#define PI acos(-1.0)
#define debug cout << "DEBUG" << endl;
using namespace std;
typedef   pair <int, int>  ii;
typedef   pair <char, int> ci;
typedef   pair <int, char> ic;
typedef vector <ii>       vii;
typedef vector <ci>       vci;
typedef vector <ic>       vic;
typedef vector <int>       vi;
typedef vector <bool>      vb;
/* ====================================================== */
int drX[] = {1, 1, 0, -1, -1, -1, 0, 1};
int drY[] = {0, 1, 1, 1, 0, -1, -1, -1};
/* ====================================================== */

vector <string> Bus, Place, v;
vector < vi > Graph;

void printBus()
{
    ofstream file;
    file.open("Bus.txt");
    REP(i, Bus.size())
        file << Bus[i] << endl;
    file.close();
}

void printPlace()
{
    ofstream file;
    file.open("Place.txt");
    REP(i, Place.size())
        file << Place[i] << endl;
    file.close();
}

void printGraph()
{
    REP(i, Graph.size())
    {
        cout << "Node " << i << " : ";
        REP(j, Graph[i].size())
        {
            cout << Graph[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
}

void GraphInput()
{
    string lol;
    int P, B;
    Graph.assign(135, vi());
    bool flag = false;
    REP(i, v.size())
    {
        lol = v[i];
        if(lol == "==")
        {
            REP(j, Place.size())
            {
                if(Place[j] == v[i-1])
                {
                    P = j;
                    break;
                }
            }
            flag = true;
        }
        if(lol == "!=")
            flag = false;
        if(flag && lol != "==" && lol != "!=")
        {
            REP(j, Bus.size())
            {
                if(Bus[j] == lol)
                {
                    Graph[P].push_back(j);
                    break;
                }
            }
        }
    }
//    printGraph();
}

void genQuestion()
{
    ofstream file;
    file.open("Question.txt");
    REP(i, Place.size())
    {
        REP(j, Place.size())
        {
            if(i != j)
            {
                file << "Which Bus i should take to go from " << Place[i] << " to " << Place[j] << endl;
            }
        }
    }
    file.close();
}

int getPlaceID(string str)
{
    REP(i, Place.size())
    {
        if(str == Place[i])
            return i;
    }
}

void genAnswers()
{
    ofstream file;
    file.open("Answers.txt");
    REP(i, Place.size())
    {
        REP(j, Place.size())
        {
            if(i != j)
            {
                bool found = false;
                vector <int> junk;
                junk.clear();
                REP(k, Graph[i].size())
                {
                    int from = Graph[i][k], to;
                    REP(l, Graph[j].size())
                    {
                        to = Graph[j][l];
                        if(from == to)
                        {
                            found = true;
                            junk.push_back(to);
                            break;
                        }
                    }
                }
                if(found && junk.size() == 1)
                {
                    file << "Bus available on this route - " << Bus[junk[0]] << endl;
                }
                else if(found && junk.size() > 1)
                {
                    file << "Buses available on this Route - ";
                    REP(idx, junk.size())
                    {
                        if(idx < junk.size() - 1)
                            file << Bus[junk[idx]] << ", ";
                        else
                            file << Bus[junk[idx]];
                    }
                    file << endl;
                }
                else
                    file << "Sorry, No bus on this route" << endl;
            }
        }
    }
    file.close();
}

void corpus()
{
    ofstream file;
    file.open("Route.yml");
    file << "categories:" << endl;
    file << "- route" << endl;
    file << "conversations:" << endl;
    REP(i, Place.size())
    {
        REP(j, Place.size())
        {
            if(i != j)
            {
                // Question
                file << "- - Which Bus i should take to go from " << Place[i] << " to " << Place[j] << endl;

                // Answers
                bool found = false;
                vector <int> junk;
                junk.clear();
                REP(k, Graph[i].size())
                {
                    int from = Graph[i][k], to;
                    REP(l, Graph[j].size())
                    {
                        to = Graph[j][l];
                        if(from == to)
                        {
                            found = true;
                            junk.push_back(to);
                            break;
                        }
                    }
                }
                if(found && junk.size() == 1)
                {
                    file << "  - Bus available on this route " << Bus[junk[0]] << endl;
                }
                else if(found && junk.size() > 1)
                {
                    file << "  - Buses available on this Route ";
                    REP(idx, junk.size())
                    {
                        if(idx < junk.size() - 1)
                            file << Bus[junk[idx]] << ", ";
                        else
                            file << Bus[junk[idx]];
                    }
                    file << endl;
                }
                else
                    file << "  - Sorry, No bus on this route" << endl;
            }
        }
    }
    file.close();
}

int main()
{
#ifndef ONLINE_JUDGE
    FileIn("in");
    FileOut("out");
#endif //ONLINE_JUDGE

    string str;
    while(getline(cin, str))
        v.push_back(str);
    set <string> s1, s2;
    set <string> :: iterator itr1, itr2;
    bool flag = false;
    REP(i, v.size())
    {
        str = v[i];
        if(str == "==")
        {
            s1.insert(v[i-1]);
            flag = true;
        }
        if(str == "!=")
            flag = false;
        if(flag && str != "==" && str != "!=")
            s2.insert(str);
    }
    for(itr1 = s1.begin(); itr1 != s1.end(); ++itr1)
        Place.push_back(*itr1);
    for(itr2 = s2.begin(); itr2 != s2.end(); ++itr2)
        Bus.push_back(*itr2);

//    printBus();
//    printPlace();

    GraphInput();
//    genQuestion();
//    genAnswers();

    corpus();
    return 0;
}
