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

/*  val l = map(fn x => ~(x+m)) l; --> NL   */

dif(M,OLD,NEW) :- 
    NEW is OLD+M. /* this is M */
neg(OLD,NEW) :-
    NEW is -OLD.
mapprocess(NL,L,M) :-
    maplist(dif(M), L,NNL),
    maplist(neg, NNL,NL).

/* end of map process */ 

/*  val prefixarray = prefix (l, 0);     */
copy(L,R) :- accCp(L,R).
accCp([],[]).
accCp([H|T1],[H|T2]) :- accCp(T1,T2).

prefix(_,[],L,X) :- copy(L,X).

prefix(SUM,[H|T],L2,X) :-
   NEWLMENT is H+SUM,
   append(L2,[NEWLMENT],L3),
   prefix(NEWLMENT,T,L3,X).

prefixproc(X,L):-
    prefix(0, L,[], X). 
/* end of prefix */

/* make sortedpairlist  */

prefixind(_,[],L,X) :- copy(L,X).

prefixind(COUNT,[H|T],L2,X) :-
   NEWCOUNT is COUNT+1,
   append(L2,[[H,COUNT]],L3),
   prefixind(NEWCOUNT,T,L3,X).

proc(X,L):-
    prefixind(0, L,[], X). 

procc(X,Y) :-
    proc(X,Y).

firstelem(X,L) :- 
    nth0(0, L, X).

bubble_sort(List,Sorted):-b_sort(List,[],Sorted).
b_sort([],Acc,Acc).
b_sort([H|T],Acc,Sorted):-bubble(H,T,NT,Max),b_sort(NT,[Max|Acc],Sorted).
   
bubble(X,[],[],X).
bubble(X1,[Y1|T],[Y1|NT],Max):-
    firstelem(X,X1),firstelem(Y,Y1),
    X>Y,bubble(X1,T,NT,Max).
bubble(X1,[Y1|T],[X1|NT],Max):- 
    firstelem(X,X1),firstelem(Y,Y1),
    X=<Y,bubble(Y1,T,NT,Max).


sortedpairlist(L,X) :-
    procc(Y,L),
    bubble_sort(Y,X).

/* end of making sortedpairlist */

/* making minindex list  */

secondelem(X,L) :- 
    nth0(1, L, X).

minindex(_,[],L,X) :- copy(L,X).
minindex(MIN,[H|T],L2,X) :-
   secondelem(IND,H),
   Y is min(MIN,IND),
   append(L2,[Y],L3),
   minindex(Y,T,L3,X).

minind(SPL,MINDEX) :-
    minindex(100000000,SPL,[],MINDEX).

/* start of looping */

firstelem1(WHERE,LOFL,VALUE) :- 
    /* write(' WHERE : '),write(WHERE),nl, */
    nth0(WHERE, LOFL, X),
    firstelem(VALUE,X).
    
ifcheck(SUM, TEMPMID, ANS, 
        NEWANS, L, H, NEWL, NEWH,X) :-
        X =< SUM,
        NEWANS is TEMPMID,
        NEWL is TEMPMID + 1,
        NEWH is H
        ;
        X > SUM, 
        NEWANS is ANS,
        NEWL is L,
        NEWH is TEMPMID - 1.


loop_entry1(L,H, _, _, NEWANS, IND) :-
        L > H,
        IND is NEWANS.

loop_entry1(L, H, PSL, VAL, ANS,IND) :-
    L =< H, !, 
    TEMP is L+H,
    TEMPMID is TEMP // 2,
    firstelem1(TEMPMID,PSL,CHECKER),
    ifcheck(VAL, TEMPMID, ANS, 
            NEWANS, L, H, NEWL, NEWH,CHECKER),
    loopchecker(NEWL, NEWH, PSL, VAL, NEWANS,IND ).

loopchecker(L,H, PSL, VAL, NEWANS, IND) :-
    L > H,
    IND is NEWANS
    ;
    L =< H,
    loop_entry1(L,H, PSL, VAL, NEWANS, IND).

/*  end of while  */

/* 
    exei thema sto for 
    den ksanakaleite h findind
*/

findind(SPL,N,VAL,IND) :-
    L is 0,
    H is N - 1,
    ANS is -1,
    loop_entry1(L, H, SPL, VAL, ANS,IND).

max1(OLDRES,Y,X) :-
    OLDRES >= Y,
    X is OLDRES
    ;
    Y > OLDRES,
    X is Y.

/* checkif prob??? */
checkif(IND,MININD,I,OLDRES,NEWRES) :-
    IND =\= -1,
    nth0(IND,MININD,X), 
    X < I,    
    Y is I - X,
    max1(OLDRES,Y,NEWRES)
    ;
    NEWRES is OLDRES.

checksum(SUM,I,OLDRES,NEWRES,N,SPL,MININD) :-
    SUM >= 0,
    NEWRES is I+1
    ;
    SUM < 0,
    findind(SPL,N,SUM,IND),
    checkif(IND,MININD,I,OLDRES,NEWRES).


for([],_,NEWRES,ENDRES,_,_,_,_) :- ENDRES is NEWRES.

for([H|T],SUM1,OLDRES,ENDRES,I1,N,SPL,MININD) :-
    SUM2 is H + SUM1,
    checksum(SUM2,I1,OLDRES,NEWRES,N,SPL,MININD),
    I2 is I1 + 1,
    for(T,SUM2,NEWRES,ENDRES,I2,N,SPL,MININD).

afterfor(SPL,RES,N,MININD,NL) :-
    for(NL,0,0,X,0,N,SPL,MININD),
    RES is X.


/* end of looping */




makeminind(X,SPL,N,MININD,NL) :-
    afterfor(SPL,X,N,MININD,NL).

findsolution(Answer,SPL,N,MININD,NL) :-
    makeminind(Answer,SPL,N,MININD,NL),!.
/* end of making minindex list */


longest(File,Answer) :-
    read_input(File,N,M,L),
    process(Answer,N,M,L).

process(Answer,N,M,L) :- 
    /*reverse(RL,L),*/
    mapprocess(NL,L,M),
    prefixproc(PL,NL),
    sortedpairlist(PL,SPL),
    minind(SPL,MININD),
    findsolution(Answer,SPL,N,MININD,NL). 