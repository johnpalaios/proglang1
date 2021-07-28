
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

fun makeListOfZeros(size : int) =
    if size = 0
    then []
    else 0 :: makeListOfZeros(size - 1)

val zeroList = makeListOfZeros(numberOfCities);

val cityCar = Array.array(numberOfCities, 0);

fun makeCityCar(carInCity : int list) = 
    if null carInCity 
    then ()
    else 
        let 
            val element = Array.sub(cityCar, hd carInCity);
        in
            Array.update(cityCar, hd carInCity, element + 1);
            makeCityCar(tl carInCity)
        end

val un = makeCityCar(carInCity);

fun findMax(position : int, counter : int) = 
    if position = numberOfCities
    then findMax(0, counter)
    else
        let 
            val element = Array.sub(cityCar, position)
        in
            if element > 0
            then numberOfCities - counter -1
            else findMax(position + 1, counter + 1)
        end

fun findInitialSum(position : int, sum : int) =
    if position = numberOfCities
    then sum
    else 
        let 
            val element = Array.sub(cityCar, position);
            val newSum = (numberOfCities - position) * element
        in
            if element > 0
            then findInitialSum(position + 1, sum + newSum)
            else findInitialSum(position + 1, sum)
        end

val initialSum = findInitialSum(1, 0);

fun restrict(max : int, sum : int) = 
    if sum + 2 > 2 * max
    then 1
    else 0

fun getSum(position : int, oldSum : int) = 
    if position = 0 
    then initialSum
    else oldSum + numberOfCars - (numberOfCities * Array.sub(cityCar, position))
(*fun printit(sum : int, max : int) =
    print(Int.toString(sum)).*)

fun twoPointer(position : int, min : int, minCity : int
              , oldSum : int) = 
    if position = numberOfCities
    then (min, minCity)
    else     
        let 
            val max = findMax(position + 1, 0);
            val sum = getSum(position, oldSum);
            (*val printSum = printit(sum,max);*)
            val restriction = restrict(max, sum);
        in
           if min < 0 
           then if restriction = 0
                then twoPointer(position + 1, min, minCity, sum)
                else twoPointer(position + 1, sum, position, sum)
           else 
            if sum >= min 
            then twoPointer(position + 1, min, minCity, sum)
            else 
                if restriction = 0
                then twoPointer(position + 1, min, minCity, sum)
                else twoPointer(position + 1, sum, position, sum)
        end
(*
val i1 = findMax(1,0);
val i2 = findMax(2,0);
val i3 = findMax(3,0);
val i4 = findMax(4,0);
val i5 = findMax(5,0);
*)

val result = twoPointer(0, ~1, 420, initialSum);
val sum2 = initialSum + numberOfCars - (numberOfCities * Array.sub(cityCar, 1))

in
print(Int.toString(#1 result));
print(" ");
print(Int.toString(#2 result));
print("\n")
end