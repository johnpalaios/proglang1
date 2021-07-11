
          
import sys
from queue import Queue

#read from file
with open(sys.argv[1], 'r') as file:
    firstlist = [[int(x) for x in line.split()] for line in file] 

#inialize queue
queue = []
# queue.pop(0)
# queue.append()

size = firstlist[0][0]

for i in range(size):
    queue.append(firstlist[1][i])

#initialize stack
# stack.pop()
# stack.append()
stack = []
def stacksamenumber(path):
    j = 0
    for i in range(len(path)):
        if path[i] == 'Q':
            j=j+1
        if path[i] == 'S':
            j=j-1
    if j==0 :
        return True
    else :
        return False

def checkifsorted(queue):
    sorted_queue = sorted(queue)
    if queue == [] :
        return False
    if sorted_queue == queue and len(queue) == size :
        return True
    else :
        return False

if queue == []:
    print('empty')
    exit()

def Q(queue,stack):
    if queue != []:
        stack.append(queue.pop(0))
        return True
    else :
        return False

def S(queue,stack):
    if stack != []:
        queue.append(stack.pop())
        return True
    else :
        return False
        

def execute(path,stores,visited,q111) : 
   # oldpath = previous string
   oldpath = path[0:len(path)-1]
  # q kai s idia me prin 
    
   (q1,s1) = stores[oldpath]
   
   q = q1.copy()
   s = s1.copy()

   qors = path[len(path)-1]
   
   if qors == 'Q' :
       if Q(q,s) == False:
           return
   elif qors == 'S':
       if S(q,s) == False:
           return

   
   if (len(path) % 2) == 0 :
        if checkifsorted(q) == True:
            print(path)
            exit()
   
   qt = tuple(q)
   st = tuple(s)
   stores[path] = (q,s)
   if (qt,st) not in visited :
        visited.add((qt,st))
        if q != [] and s != []:
            if q[0] == s[-1]:
                q111.append(path+'Q')
                return
        if q != []:
            q111.append(path+'Q')
        if s != []:
            q111.append(path+'S')



def bfs(stores,visited):
    # maintain a queue of paths
    q = ['Q']
    #k=0
    while q:
        #k=k+1
        # get the first path from the queue
        size = len(q)
        path = []
        q1 = []
        #print(k)
        for i in range(size):
            path.append(q.pop(0))
            execute(path[i],stores,visited,q1)
        q = q1
        q1 is q
        #print(q)
       

 

qe = queue.copy()
se = stack.copy()

Q(queue,stack)

qet = tuple(qe)
set = tuple(se)

queuet = tuple(queue)
stackt = tuple(stack)

visited = {
    (qet,set) ,
    #(queuet,stackt)
}

stores = {
    '' : (qe,se),
    'Q' : (queue,stack)
}
if checkifsorted(qe) == True:
    print('empty')
    exit()
bfs(stores,visited)  