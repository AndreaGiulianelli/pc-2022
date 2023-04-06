/*
    This example will show the fact that plan and so intections are not executed atomically.
    There is no macro-step semantics like in actors.
    We have a macro-step semantics only at the level of the single operation, not of the all plan.
    In fact, we can see the println being overlapped in time, interleaved.
    For this reason we can create high-level race conditions like in the user agent with "tick".
    In order to have a plan that is executed atomically itself, we can use the annotation [atomic]
*/

!prova.
!prova2.

// to solve: @prova [atomic]
+!prova
    <- println("ciao");
       println("come");
       println("stai?");
       println("bene");
       !!prova.

// to solve: @prova2 [atomic]
+!prova2
    <- println("prova2");
       println("prova2-1");
       println("prova2-2");
       println("prova2-3");
       !!prova2.