fun longest l =
let 
fun parse file =
    let
    (* A function to read an integer from specified input. *)
        fun readInt input = 
        Option.valOf (TextIO.scanStream (Int.scan StringCvt.DEC) input)

    (* Open input file. *)
    	val inStream = TextIO.openIn file

        (* Read an integer (number of countries) and consume newline. *)
    val n = readInt inStream
    val m = readInt inStream
    val _ = TextIO.inputLine inStream

        (* A function to read N integers from the open file. *)
    fun readInts 0 acc = acc (* Replace with 'rev acc' for proper order. *)
      | readInts i acc = readInts (i - 1) (readInt inStream :: acc)
    in
   	(n,m, readInts n [])
    end

val tup = parse l;
val n = #1 tup;
val m = #2 tup;
val l = #3 tup;

fun max(i:int,j:int) = 
    if i > j 
    then i 
    else j;
  (* Reverse a list *)
fun reverse nil = nil 
  | reverse (x::xs) = (reverse xs) @ [x];

  (* Calculate the Prefix Sum and put it in a list *)
fun prefix (l1: int list,up: int) = 
    if null l1
    then []
    else (hd l1) + up :: prefix( (tl l1) , (hd l1) + up )

    (* Make a list of pairs with list : [ (prefix[0],0) , ... , (prefix[n],n) ] *)
fun makepairlist (l1: int list,index: int) =
    if null l1
    then []
    else (hd l1,index) :: makepairlist(tl l1,index+1)

  (* Function bubblesort() to sort the pair list (as of StackOverflow) *)
fun bsort [] = []
|   bsort [ (x,i) ] = [(x,i)]
|   bsort ((x, i) :: (y, j) :: xs) =   
                    if(y < x) then
                        (y, j) :: bsort((x,i) :: xs)
                    else
                        (x, i) :: bsort((y, j) :: xs);
                    
fun bubblesort [] = []
|   bubblesort ((x,i) :: xs) = 
                bsort((x,i) :: (bubblesort(xs)));

fun pairtoint (xs:(int*int) list) = 
    if null xs 
    then []
    else  #1(hd xs) :: pairtoint(tl xs)
  (* Function to reverse a pair list (because left <- right is needed for finding maxind) *)

fun reversepairlist nil = nil 
  | reversepairlist ((x,i)::xs) = (reversepairlist xs) @ [(x,i)];
val l = reverse l;
val l = map(fn x => ~(x+m)) l;
val prefixarray = prefix (l, 0);
val prepairlist = makepairlist(prefixarray, 0);
val rev = reversepairlist prepairlist;
val sortedpairlist = bubblesort prepairlist;

(*val k = reversepairlist ( sortedpairlist );*)

(* preSum == preSum *)
val preSum = bubblesort prepairlist; 
val l23 = pairtoint(preSum);
val presum = Array.fromList l23;
fun minind (l1: (int * int) list ,minindex: int) =
    if null l1
    then []
    else if   #2 (hd l1) < minindex
        then  #2 (hd l1) :: minind( tl l1, #2 (hd l1))
        else  minindex :: minind( tl l1, minindex)

val minind =  minind(sortedpairlist,n+1) ;
val minind1 = Array.fromList minind;
fun whilee (l: int, h: int ,s: int,an:int) = 
    let 
        val mid = (l+h) div 2 
    in
    if l <= h
    then 
        let 
            val j = Array.sub(presum,mid);
        in 
        if j <= s 
        then whilee(mid+1,h,s,mid)
        else whilee(l,mid-1,s,an)
        end 
    else
        an
    end
fun printlist xs = 
    print(String.concatWith ", " (map Int.toString xs));


fun findInd(s: int) = 
    let 
        val l = 0;
        val h = n - 1;
        val ans = ~1;
    in
    whilee(l,h,s,ans)
    end

fun i1 (i:int,a: int list
      ,maxlen: int ,sum: int) =
    if null a 
    then maxlen
    else 
      let 
         val sum = sum + hd a;
      in
      if i = n
      then maxlen 
      else if sum >= 0 
           then i1(i+1,tl a,i+1,sum)
           else 
              let 
                val ind = findInd(sum);
                val m = Array.sub(minind1,ind);
                val maxlen1 = max(maxlen,i-m);
              in
                if ind <> ~1 andalso 
                       m < i 
                then i1(i+1,tl a,maxlen1,sum)
                else i1(i+1,tl a,maxlen,sum)
              end
      end 

(* arr[] == arr (int list) *)
val arr = l;
(* minInd == minind (int list) *)
val res = i1(0,arr,0,0);
in
  print (Int.toString(res)) ;
  print "\n"
end
(* I want to see *)