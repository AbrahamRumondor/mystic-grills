package model;

import java.util.Stack;
import javafx.scene.Node;

public class ActivityLog {
	private Stack<Node> sceneStack = new Stack<>();
	
	private static ActivityLog activityLog;
	
	public void pop() {
		sceneStack.pop();
	}

	public Stack<Node> getSceneStack() {
		return sceneStack;
	}

	public void add(Node object) {
		this.sceneStack.add(object);
	}
	
//	singleton
	public static ActivityLog getInstance() {
		if(activityLog == null) {
			activityLog = new ActivityLog();
		}
		return activityLog;
	}
}
