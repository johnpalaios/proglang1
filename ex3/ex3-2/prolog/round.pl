/* TAKE N,M,L */
read_input(File, N, M, L) :-
    open(File, read, Stream),
    read_line(Stream, [N, M]),
    read_line(Stream, L).

read_line(Stream, L) :-
    read_line_to_codes(Stream, Line),
    atom_codes(Atom, Line),
    atomic_list_concat(Atoms, ' ', Atom),
    maplist(atom_number, Atoms, L). 
/* END OF INPUT */

%BUILD [0,0,0,0,0]
build(X, N, List)  :- 
    length(List, N), 
    maplist(=(X), List).

replace_nth0(List, Index, NewList) :-
    % predicate works forward: Index,List -> OldElem, Transfer
    nth0(Index,List,OldElem,Transfer),
    % predicate works backwards: Index,NewElem,Transfer -> NewList
    NewElem is OldElem + 1,
    nth0(Index,NewList,NewElem,Transfer).
same(X,X).
make_city_list_rec([], List, Result):- same(Result, List).
make_city_list_rec([HD|TL], List, Result) :-
    replace_nth0(List, HD, OldResult),
    make_city_list_rec(TL, OldResult, Result).

make_city_list(NumberOfCities, [HD|TL], Result) :-
    build(0, NumberOfCities, CityAndCars),
    replace_nth0(CityAndCars, HD, OldResult),
    make_city_list_rec(TL, OldResult, Result).
findMax(0, TempMax, _, NewTempMax) :-
    same(NewTempMax, TempMax).

findMax(HD, TempMax, Distance, NewTempMax) :-
    HD \= 0,
    NewTempMax is max(TempMax, Distance).

findSumAndMax([], _, TempMax, Max,
              TempSum, Sum, _) :-
    same(TempMax, Max),
    same(TempSum, Sum).

findSumAndMax([_|TL], NumberOfCities, TempMax, Max,
              TempSum, Sum, 0) :-
    findSumAndMax(TL, NumberOfCities, TempMax, Max,
                  TempSum, Sum, 1).

findSumAndMax([HD|TL], NumberOfCities, TempMax, Max,
              TempSum, Sum, Counter) :-
    Counter \= 0,
    NewCounter is Counter + 1,
    Distance is NumberOfCities - Counter,
    findMax(HD,TempMax, Distance, NewTempMax),
    NewTempSum is TempSum + HD * Distance,
    NewCounter is Counter +1,
    findSumAndMax(TL, NumberOfCities, NewTempMax, Max,
                  NewTempSum, Sum, NewCounter).

getNext([], MaxPointer, CityAndCars) :-
    NewDistance is Distance + 1,
    getNext(CityAndCars, MaxPointer, CityAndCars, NewDistance, FinalDistance).
getNext([0|TL], MaxPointer, CityAndCars, Distance, FinalDistance) :-
    NewDistance is Distance + 1,
    getNext(TL, MaxPointer, CityAndCars, NewDistance, FinalDistance).
getNext([HD|TL], MaxPointer, _, Distance, FinalDistance) :-
    HD \= 0,
    MaxPointer is HD,
    FinalDistance is Distance.
two_pointer(_,_,[], _, Min, MinCity, FinalMin, FinalMinCity, _,_,_) :-
    FinalMin is Min,
    FinalMinCity is MinCity.
two_pointer(Cities, Cars, [HD|TL], NumberOfCities, Min, MinCity
            , FinalMin, FinalMinCity, Counter, Sum, Max) :-
    getNext(TL, MaxPointer, CityAndCars, 1, Distance),
    NewSum is Sum + Cars - Cities * HD.
round(File) :-
    read_input(File, NumberOfCities, NumberOfCars, CarInCity),
    make_city_list(NumberOfCities, CarInCity, CityAndCars),
    findSumAndMax(CityAndCars, NumberOfCities, 0 , Max, 0, Sum, 0),
    write(Sum),write(' '),write(Max),write('\n'),
    two_pointers(NumberOfCities, NumberOfCars, CityAndCars
                , CityAndCars, NumberOfCities, Min, MinCity
                , 0, Sum, Max),
    write(Min),write(' '),write(MinCity).
    