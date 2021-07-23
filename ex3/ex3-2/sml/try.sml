fun printList1 (list : int string) = 
    case list of    
        []    => ()
    |(hd::tl) => 
fun printIt [] = 
    printIt (hd::tl) = 
    (print(Int.toString(hd));printList(tl))
fun printList (list : int list) = 
    if list = [] 
    then ()
    else printIt(list);