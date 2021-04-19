package familytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Stack;

public class DirectedGraph<T> {
	
	private Map<T,List<T>> adjLists;
	
	public DirectedGraph() {
		adjLists = new LinkedHashMap<T, List<T>>();
	}
	
	public List<T> getNeighbours(T element) {
		List<T> neighbours = adjLists.get(element);
		if (neighbours == null) neighbours = new ArrayList<T>();
		return Collections.unmodifiableList(neighbours);
	}
	
	public boolean hasVertix(T element) {
		return adjLists.containsKey(element);
	}
	
	public void addVertix(T element) {
		if (hasVertix(element)) return;
		adjLists.put(element, new ArrayList<T>());
	}
	
	public void addEdge(T from, T to) {
		if (!hasVertix(from)) addVertix(from);
		if (!hasVertix(to)) addVertix(to);
		adjLists.get(from).add(to);		
	}
	
	public boolean hasEdge(T from, T to) {
		if (!adjLists.containsKey(from)) return false;
		return adjLists.get(from).contains(to);	
	}
	
	public void removeEdge(T from, T to) throws NoSuchElementException{
		List<T> adjList = adjLists.get(from);
		if (adjList == null || !adjList.contains(to)) throw new NoSuchElementException();
		adjList.remove(to);
	}
	
	private enum State {UNVISITED, VISITED, CURRENTLY_VIEWED}
	public boolean hasCycle() {
		Stack<T> dfsStack = new Stack<T>();
		
		Map<T,State> status = new HashMap<T, State>();
		for (T v : adjLists.keySet()) {
			status.put(v, State.UNVISITED);
		}
		
		for (T v : status.keySet()) {
			dfsStack.removeAllElements();
			dfsStack.push(v);
			status.put(v, State.CURRENTLY_VIEWED);
			if (subTreeHasCycle(dfsStack, status)) return true;
		}
		
		return false;
	}
	
	private boolean subTreeHasCycle(Stack<T> stack, Map<T, State> status) {
		T top = stack.peek();
		
		for (T child : adjLists.get(top)) {
			State childStatus = status.get(child);
			if (childStatus == State.CURRENTLY_VIEWED) {
				return true;
			}
			else if (childStatus == State.UNVISITED) {
				stack.push(child);
				status.put(child, State.CURRENTLY_VIEWED);
				if (subTreeHasCycle(stack, status)) return true;
			}
		}
		
		status.put(top, State.VISITED);
		stack.pop();
		return false;
	}
}

