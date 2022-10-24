// CArtAgO artifact code for project helloworld

package tools;

import cartago.*;
/*
Cartago is a common infrastucture to model artifacts in Java.
Artifact can be model in whatever language, but cartago is a framework for this.

This is the template to specify an Artifact.

The Artifact is making sure that there are no race-conditions.
 */
public class Counter extends Artifact {
	void init(int initialValue) { // It's executed when the Artifacts is created
		defineObsProperty("count", initialValue); // We define a property that is observable.
	}

	@OPERATION
	void inc() { // Describe a possible action
		ObsProperty prop = getObsProperty("count");
		prop.updateValue(prop.intValue()+1);
		signal("tick"); // signal is for generating observable events.
	}
}

