(*fun round l =
let *)
fun printList1(nil) = ()
  | printList1(x::xs) = (
            print(Int.toString(x));
            print(", ");
            printList1(xs)
    );
fun printList(list : int list) = (
    print("[");
    printList1(list);
    print("]")
    );

fun printListOfLists(listlist : int list list) =
    if null listlist 
    then print "]"
    else (
        printList(hd listlist);
        print ", ";
        printListOfLists(tl listlist)
    );
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


val tup = parse "r1.txt";
val numberOfCities = #1 tup;
val numberOfCars = #2 tup;
val carInCity = #3 tup;

fun makeList(j : int, city : int, carInCity2 : int list) =
    if j > numberOfCars-1
    then []
    else if city >= hd carInCity2
        then city - hd carInCity2 :: makeList(j+1, city, tl carInCity2)
        else numberOfCities - hd carInCity2 + city :: makeList(j+1, city,tl carInCity2)

fun makeListOfLists(i : int, carInCity1 : int list) =
    if i < numberOfCities
    then makeList(0, i, carInCity1) :: makeListOfLists(i+1, carInCity1)
    else []

val gang = makeListOfLists(0, carInCity);
(*
in
print(Int.toString(numberOfCities));
print"\n";
print(Int.toString(numberOfCars));
print"\n";
printList carInCity;
print"\n";
printListOfLists gang
end    *)    