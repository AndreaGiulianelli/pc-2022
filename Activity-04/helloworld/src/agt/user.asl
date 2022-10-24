number_ticks(0).

!go.

// In satisfying this goal we want to create an artifact
+!go
    <- !createCounter(C); // C will be unified below. Like in Prolog.
       !useCounter(C).
       

+!createCounter(Id)
    <- makeArtifact(c0, "tools.Counter", [10], Id); // 10 will specify the initial value, so here we pass the values for the init. Id is for uniquely identify the artifact.
       println("Counter created: ", Id); // NOTE: there is no dot (.), so we aren't calling the internal. Default we have some artifact. For example this is the artifact of Console, that provide the print action.
       focus(Id). // With focus we can observe some part of the environment, we need to specify the Id of the artifacts. As soon as I start to focus, all the observable state is automaticle mapped in my beliefs (fantastic, like in humans).

+!useCounter(C)
    <- println("going to use counter ", C);
       inc; // We can use it without specifying the artifact (if there is ambiguity is not deterministic)
       inc [aid(C)]; // But if want to execute an action on a precise artifact (also when we have ambiguity) we can do like this. It's an annotation.
       println("done").

+count(C) // If my beliefs of count (observed through focus) has changed, react to it.
    <- println("new count: ", C).

// In this way we can generate race-conditions between different ticks, in fact if you run it, N will be 0 when printed.
// We can specify atomic plans, so that are not interleaved.
+tick : number_ticks(N) // we can react also to events (signal in our Artifact), but these are not modeled as beliefs, so they are not persistant, they are no persist. If we need to persistem them, we need to do it manually here.
    <- println("tick! ", N);
       -+number_ticks(N + 1). //-number_ticks(N); +number_ticks(N + 1)

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
