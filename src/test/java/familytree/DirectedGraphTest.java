package familytree;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class DirectedGraphTest {
	
	private DirectedGraph<Integer> graph; 
	@Before
	public void setUp() throws Exception {
		graph = new DirectedGraph<Integer>();
	}

	@Test
	public void hasNewVerticesAfterAddingAnEdge() {
		graph.addEdge(1,2);
		assertTrue(graph.hasVertix(1));
		assertTrue(graph.hasVertix(2));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void exceptionOnRemovingNonexistantEdge() {
		graph.addEdge(1,2);
		graph.removeEdge(1, 3);
	}
	
	@Test
	public void recognisesNoCycle() {
		graph.addEdge(1,2);
		graph.addEdge(1,3);
		graph.addEdge(2, 3);
		assertFalse(graph.hasCycle());
	}
	
	@Test
	public void recognisesUnitCycle() {
		graph.addEdge(1,2);
		graph.addEdge(1,3);
		graph.addEdge(3, 3);
		assertTrue(graph.hasCycle());
	}
	
	@Test
	public void recognisesSimpleCycle() {
		graph.addEdge(1,2);
		graph.addEdge(2,3);
		graph.addEdge(3, 1);
		assertTrue(graph.hasCycle());
	}
	
	@Test
	public void recognisesComplexCycle() {
		graph.addEdge(1,2);
		graph.addEdge(3,1);
		graph.addEdge(3,2);
		graph.addEdge(2,4);
		graph.addEdge(4,5);
		graph.addEdge(2,6);
		graph.addEdge(2,7);
		graph.addEdge(7,8);
		graph.addEdge(7,3);
		assertTrue(graph.hasCycle());
	}
	
	

}
