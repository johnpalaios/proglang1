import sys
with open(sys.argv[1], 'r') as f:
    r, c = [int(x) for x in next(f).split()]
    a =f.readlines()
if (r*c) > 900 :
    sys.setrecursionlimit(r*c)
# grammes -> r
# stiles -> c

#visited -> boolean arrays that stores 
#           True if we visited
#           and false if we didnt
visited = [[False]*c for _ in range(r)]

#b -> int array that stores 
#     False -> at the start
#     True-> if this rooms goes outside 
b = [[False]*c for _ in range(r)]

# initialize to True all outer rooms 
# that lead to OUT 
for i in range(c):
    if a[0][i] == 'U' :
        b[0][i] = True
    if a[r-1][i] == 'D' : 
        b[r-1][i] = True
    
for i in range(r):
    if a[i][0] == 'L' :
        b[i][0] = True
    if a[i][c-1] == 'R' : 
        b[i][c-1] = True

#check which rooms are leading
# OUT 

def findout(a,i,j,b,visited):
    if b[i][j] == True:
        return True
    if visited[i][j] == True:
        return False
    visited[i][j] = True
    i1 = i
    j1 = j
    if a[i][j] == 'U' :
        i1=i1-1
    if a[i][j] == 'D' :
        i1=i1+1
    if a[i][j] == 'L' :
        j1=j1-1
    if a[i][j] == 'R' :
        j1=j1+1
    b[i][j] = findout(a,i1,j1,b,visited)
    return b[i][j]


for i in range(r):
    for j in range(c):
        findout(a,i,j,b,visited)

n = 0
for i in range(r):
    for j in range(c):
        if b[i][j] == 0:
            n = n + 1
print (n)