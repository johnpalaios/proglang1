fun loop_rooms l =
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
    fun next_String input = (TextIO.inputAll input) 
    val stream = TextIO.openIn file
    val a = next_String stream
        (* A function to read N integers from the open file. *)
    fun readInts 0 acc = acc (* Replace with 'rev acc' for proper order. *)
      | readInts i acc = readInts (i - 1) (readInt inStream :: acc)
    in
   	(n,m, explode(a))
    end


val tup = parse l;
val r = #1 tup;
val c = #2 tup;
val chars = #3 tup;

fun another(l:char list,i:int) = 
    if null l 
    then []
    else if i > 3 then hd l::another(tl l,i+1)
         else another(tl l,i+1)

val chars = another(chars,0);

val a = Array.fromList chars;

fun make (i: int , b:bool) = 
    let 
        val hdd = Array.sub(a,i)
        fun makerows(i:int,stop:int,bound:int) = 
            let 
                val t = Array.sub(a,i) 
            in 
            if i = stop 
            then []
            else if i >= bound 
                 then t::[]
                 else t::makerows(i+1,stop,bound)
            end 
    in
    if i = r * c + r - 3
    then []
    else if hdd = #"\n" 
         then make(i+1,true)
         else if b = true
         	  then if i <= r*c+r-2-c 
               	   then makerows(i,i+c,r*c+r) :: make(i+c,true)
                   else makerows(i,i+c,r*c+r-2) :: []
              else []
    end

val charlistlist = make(0,true);

val arr = Array2.fromList charlistlist;
val b = Array2.array(r,c,0);
val visited = Array2.array(r,c,0);
fun un i = ();
fun f1 i = 
    if i = c 
    then ()
    else 
        let 
            val a1 = Array2.sub(arr,0,i); 
        in
        if a1 = #"U"
         then 
         	let 
             	val () = Array2.update(b,0,i,1);
            in
         		f1(i+1)
            end
         else f1(i+1)
        end 


fun f2 i = 
    if i = c
    then ()
    else 
        let 
            val a2 = Array2.sub(arr,r-1,i);
        in
         if a2 = #"D"
         then 
         	let 
             	val () = Array2.update(b,r-1,i,1) ;
         	in
         		f2(i+1)
            end
         else f2(i+1)
        end 


fun f3 i = 
    if i = r
    then ()
    else 
        let 
            val a2 = Array2.sub(arr,i,0);
        in
         if a2 = #"L"
         then 
         	let 
             	val () = Array2.update(b,i,0,1)
         	in
         		f3(i+1)
            end
         else f3(i+1)
        end

fun f4 i = 
    if i = r
    then ()
    else
        let 
            val a2 = Array2.sub(arr,i,c-1); 
        in 
         if a2 = #"R"
         then 
         	let
             	 val () = Array2.update(b,i,c-1,1)
            in
             f4(i+1)
            end
         else f4(i+1)
        end

(*
fun ii i = case i of
      1 => f1(0)
    | 2 => f2(0)
    | 3 => f3(0)
    | 4 => f4(0)

fun pp i = 
    ii i;

val () = pp 1;
val () = pp 2;
val () = pp 3;
val () = pp 4;
*)

val () = f1(0);
val () = f2(0);
val () = f3(0);
val () = f4(0);
(*Array2.sub(b,0,0);
Array2.sub(b,0,1);
Array2.sub(b,0,2);
Array2.sub(b,1,0);
Array2.sub(b,1,1);
Array2.sub(b,1,2);
Array2.sub(b,2,0);
Array2.sub(b,2,1);
Array2.sub(b,2,2);
if res = 1
        then true
        else false
*)
fun booltoone(b:bool) = 
    if b = true 
    then 1
    else 0

fun onetobool(i:int) = 
    if i = 0
    then false
    else true

fun findout (i:int,j:int) = 
    if Array2.sub(b,i,j) = 1
    then true
    else if Array2.sub(visited,i,j) = 1
        then false 
        else 
            let
         val ()  =
          Array2.update(visited,i,j,1)
         val i1 = i;
         val j1 = j;
         val i1 = 
             if Array2.sub(arr,i,j) = #"U"
             then i1-1
             else i1
         val i1 = 
             if Array2.sub(arr,i,j) = #"D"
             then i1+1
             else i1
         val j1 = 
             if Array2.sub(arr,i,j) = #"L"
             then j1-1
             else j1
         val j1 = 
             if Array2.sub(arr,i,j) = #"R"
             then j1+1
             else j1	
        val res = findout(i1,j1);
        val res1 = booltoone(res);
        val () = Array2.update(b,i,j,res1);
            in
            res	 
            end

fun fir (i:int ,j:int) = 
        if i = r 
        then ()
        else
             if j = c
             then fir(i+1,0)
             else
            let 
                val b = findout(i,j);
            in 
                fir(i,j+1)
        	end 
(*fun fir (i:int ,j:int) = 
        if i = r 
        then ()
        else
             if j = c
             then fir(i+1,0)
             else
            let 
                val b = findout(i,j);
            in 
                fir(i,j+1)
        	end 
*)

val counter = 0
fun f (i:int ,j:int,coun:int) = 
        if i = r 
        then coun
        else
         if j = c
         then f(i+1,0,coun)
         else
            let 
                val cc = 
                        if Array2.sub(b,i,j) = 0
                        then coun+1
                        else coun			
            in 
                f(i,j+1,cc)
       		end
val () = fir(0,0);
val result = f(0,0,0);
in
print(Int.toString(result));
print"\n"
end        