fun round l =
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
   	(n,m, readInts m [])
    end


val tup = parse l;
val numberOfCities = #1 tup;
val numberOfCars = #2 tup;
val carInCity = #3 tup;

fun makeList(j : int, city : int, carInCity2 : int list) =
    if j > numberOfCars-1
    then []
    else if city >= hd carInCity2
        then  city - hd carInCity2 :: makeList(j+1, city, tl carInCity2) 
        else numberOfCities - hd carInCity2 + city :: makeList(j+1, city,tl carInCity2)

fun makeListOfLists(i : int, carInCity1 : int list) =
    if i < numberOfCities
    then makeList(0, i, carInCity1) :: makeListOfLists(i+1, carInCity1)
    else []

fun findSumOfList(list : int list) = 
    if null list
    then 0
    else hd list + findSumOfList(tl list)

fun findSum(listlist : int list list) = 
    if null listlist
    then []
    else findSumOfList(hd listlist) :: findSum(tl listlist)

fun findMaxOfList(list : int list, max : int) = 
    if null list
    then max
    else if hd list > max 
         then findMaxOfList(tl list, hd list)
         else findMaxOfList(tl list, max)

fun findMax(listlist : int list list) = 
    if null listlist
    then []
    else findMaxOfList(hd listlist, 0) :: findMax(tl listlist)

fun findResult1(sums : int list, maxes : int list, 
                moves : int, city : int) = 
    if null sums 
    then (~1,~1)
    else if moves < hd sums
         then if 2 * hd maxes < hd sums + 2
              then (hd sums, city)
              else findResult1(tl sums, tl maxes
                               , moves, city+1)
        else findResult1(tl sums, tl maxes
                               , moves, city+1)

fun findResult2(sums : int list, maxes : int list, 
                city : int, resultTuple : int * int) = 
    if null sums 
    then resultTuple
    else if #1 resultTuple > hd sums
         then if 2 * hd maxes < hd sums + 2
              then findResult2(tl sums, tl maxes
              , city+1, (hd sums, city))
              else findResult2(tl sums, tl maxes
                               , city+1, resultTuple)
        else findResult2(tl sums, tl maxes
                         , city+1, resultTuple)

fun findResult(sums : int list, maxes : int list) = 
    let
        val size = numberOfCities;
        val moves = 0;
        val tuple1 = findResult1(sums, maxes, moves, 0);
        val tuple2 = findResult2(sums, maxes, 0, tuple1);
    in
        print(Int.toString(#1 tuple2));
        print " ";
        print(Int.toString(#2 tuple2));
        print "\n"
    end

val differenceList = makeListOfLists(0, carInCity);
val sumList = findSum(differenceList);
val maxList = findMax(differenceList);

in
findResult(sumList, maxList)
end 