read_input(File, N, C) :-
    open(File, read, Stream),
    read_line(Stream, [N]),
    read_line(Stream, C).

read_line(Stream, L) :-
    read_line_to_codes(Stream, Line),
    atom_codes(Atom, Line),
    atomic_list_concat(Atoms, ' ', Atom),
    maplist(atom_number, Atoms, L).

copylists(L,R) :- accCp(L,R).
accCp([],[]).
accCp([H|T1],[H|T2]) :- accCp(T1,T2).

pivoting(_,[],[],[]).
pivoting(H,[X|T],[X|L],G):-X>=H,pivoting(H,T,L,G).
pivoting(H,[X|T],L,[X|G]):-X<H,pivoting(H,T,L,G).

quick_sort(List,Sorted):-q_sort(List,[],Sorted).
q_sort([],Acc,Acc).
q_sort([H|T],Acc,Sorted):-
    pivoting(H,T,L1,L2),
    q_sort(L1,Acc,Sorted1),q_sort(L2,[H|Sorted1],Sorted).

pop([X|List],X,List).
enqueue(OldQueue, Element, NewQueue) :-
    length(OldQueue, Length),
    Idx is Length + 1,
    nth1(Idx, NewQueue, Element, OldQueue).
  
dequeue(OldQueue, Element, NewQueue) :-
    length(OldQueue, Length),
    nth1(Length, OldQueue, Element, NewQueue).

copy(X,X).
q_move(Q,S,XQ,XS,A) :-
    length(Q,Len),
    Len > 0,
    copy('Q',A),
    pop(Q,X,XQ),
    enqueue(S,X,XS).

s_move(Q,S,XQ,XS,A) :-
    copy('S',A),
    dequeue(S,X,XS),
    enqueue(Q,X,XQ). 



moves(Q,S,XQ,XS,A) :-
    length(Q,Len),
    Len > 0,
    q_move(Q,S,XQ,XS,A).

moves([Queue_head|Queue_tail],Stack,XQ,XS,A) :-
    length(Stack,Len),
    Len > 0,
    Len1 is Len-1,
    nth0(Len1, Stack, Stack_head),
    \+ Stack_head =:= Queue_head,
    s_move([Queue_head|Queue_tail],Stack,XQ,XS,A).



method(Q,[],SORTEDA,[]) :-
    copy(Q,SORTEDA).

method(Q,S,SORTEDA,[Move|Moves]) :-
    moves(Q,S,XQ,XS,Move),
    method(XQ,XS,SORTEDA,Moves).

checkifempty([],NE) :-
    copy(['e','m','p','t','y'],NE).
checkifempty(E,NE) :-
    copy(E,NE).

sorting(A,X) :-
    quick_sort(A,SORTEDA),
    length(E,_),
    method(A,[],SORTEDA,E),
    checkifempty(E,NE),
    string_to_list(X,NE).

qssort(File,Answer) :-
    read_input(File,_,A),
    once(sorting(A,Answer)).

/* search for  visited */