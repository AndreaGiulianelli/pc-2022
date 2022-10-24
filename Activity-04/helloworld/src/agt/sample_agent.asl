// Agent sample_agent in project helloworld

/* Initial beliefs and rules */
/* The knowledge that the agent has of the state. Ex: the language to use */
language(ita). // I don't have to use string, we can also use symbols.
// language(eng).
//language(jap).


/* Initial goals */

/*we are assign the agent the goal to say hello
we can pass also parameters, also partial information with underscore -> we specify only the information we know
If we don't specify a plan for that goal, in the console there will be an error, but the agent will stay on, because it's normal, like in human when we don't have a specific pre-defined plan for a thing.
*/
!greet. 
!anotherGoal("bla", 13, p(1, _)).

/*
We can have multiple goals, that will try to achieve concurrently.
Remember it has a single cycle of control, it choose always one action at a time, we can't have race conditions.

*/

/* Plans */

/*
It's an achievement goal.
*/
+!greet : language(eng) // specify the context for which the plan can be selected
    <- .println("Hello Andrea"). // actions that start with dot (.) are internal, so they don't have effect on the environment

/*
    In the body of a plan I can trigger another event, to trigger another subPlan
    in order to decomposing the behavior. This is useful when cope with complex actions.
    The subplan is put on top of intections, so it has an higher priority.
*/
+!greet : language(ita)
    <- .println("Ciao Andrea");
       !sayHello;
       .wait(100000); // It wil susped the agent, not the control flow
       !sayWorld;
       .println("greet completed.").

-!greet // we can react to failure (try to delete sayHello or/and sayWorld)
    <- .println("sorry, I don't know how do it.").

+!sayHello
    <- .print("Hello ").

+!sayWorld 
    <- .println("World").

+!anotherGoal(Info1, Num, Str) // we can use matching (see the 13 al posto di Num) because the unification is based on prolog
    <- .println("another goal...", Info1);
       .wait(1000000);
       .println("done").


//+!start : true <- .print("hello world.").

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
