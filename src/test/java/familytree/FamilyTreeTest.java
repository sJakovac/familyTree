package familytree;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class FamilyTreeTest {
	
	private FamilyTree familyTree;
	@Before
	public void setUp() throws Exception {
		familyTree = new FamilyTree();
	}
	

	@Test
	public void recognisingOrphans() {
		familyTree.addRelation("A", "B");
		familyTree.addRelation("B", "C");
		familyTree.addRelation("D", "C");
		familyTree.addRelation("A", "E");
		
		Set<String> expectedOrphans = new HashSet<String>();
		expectedOrphans.add("A"); expectedOrphans.add("D");
		
		assertEquals(expectedOrphans, familyTree.getOrphans());
	}
	
	@Test
	public void recognisingNoOrphansInIllegalTree() {
		familyTree.addRelation("A", "B");
		familyTree.addRelation("B", "C");
		familyTree.addRelation("C", "A");
		
		assertTrue(familyTree.getOrphans().isEmpty());
	}
	
	@Test
	public void recognisingSimpleCyclicRelation(){
		familyTree.addRelation("A", "B");
		familyTree.addRelation("B", "C");
		familyTree.addRelation("C", "A");
		
		assertTrue(familyTree.hasCyclicRelations());
	}
	
	@Test
	public void recognisingComplexCyclicRelation(){
		familyTree.addRelation("A", "B");
		familyTree.addRelation("B", "C");
		familyTree.addRelation("E", "B");
		familyTree.addRelation("C", "D");
		familyTree.addRelation("F", "E");
		familyTree.addRelation("D", "E");
		
		assertTrue(familyTree.hasCyclicRelations());
	}
	
	@Test
	public void simpleFlatRepresentation() {
		familyTree.addRelation("A", "B");
	
		StringBuilder expected = new StringBuilder();
		expected.append("A\n")
				.append("\tB\n");
		
		assertEquals(familyTree.prettyRepresentation(), expected.toString());
	}
	
	@Test
	public void multipleFlatRepresentation() {
		familyTree.addRelation("A", "B");
		familyTree.addRelation("C", "D");
		familyTree.addRelation("E", "F");
		
		StringBuilder expected = new StringBuilder();
		expected.append("A\n")
				.append("\tB\n")
				.append("C\n")
				.append("\tD\n")
				.append("E\n")
				.append("\tF\n");
		
		assertEquals(familyTree.prettyRepresentation(), expected.toString());
	}
	
	@Test
	public void simpleSingleChildrenRepresentation() {
		familyTree.addRelation("A", "B");
		familyTree.addRelation("B", "C");
		familyTree.addRelation("C", "D");
		
		StringBuilder expected = new StringBuilder();
		expected.append("A\n")
				.append("\tB\n")
				.append("\t\tC\n")
				.append("\t\t\tD\n");
		
		assertEquals(familyTree.prettyRepresentation(), expected.toString());
	}
	
	@Test
	public void multipleChildrenRepresentation() {
		familyTree.addRelation("A", "B");
		familyTree.addRelation("B", "C");
		familyTree.addRelation("C", "E");
		familyTree.addRelation("B", "D");
		
		StringBuilder expected = new StringBuilder();
		expected.append("A\n")
				.append("\tB\n")
				.append("\t\tC\n")
				.append("\t\t\tE\n")
				.append("\t\tD\n");
		
		assertEquals(familyTree.prettyRepresentation(), expected.toString());
	}
	
	@Test
	public void simpleMultipleParentsSingleChildDeepRepresentation() {
		familyTree.addRelation("A", "B");
		familyTree.addRelation("C", "B");
		familyTree.addRelation("D", "B");
		
		StringBuilder expected = new StringBuilder();
		expected.append("A\n")
				.append("\tB\n")
				.append("C\n")
				.append("\tB\n")
				.append("D\n")
				.append("\tB\n");
		
		assertEquals(familyTree.prettyRepresentation(), expected.toString());
	}
	
	@Test
	public void complexMultipleParentsSingleChildDeepRepresentation() {
		familyTree.addRelation("A", "B");
		familyTree.addRelation("A", "C");
		familyTree.addRelation("B", "D");
		familyTree.addRelation("C", "D");
		familyTree.addRelation("D", "E");
		familyTree.addRelation("C", "E");
		
		StringBuilder expected = new StringBuilder();
		expected.append("A\n")
				.append("\tB\n")
				.append("\t\tD\n")
				.append("\t\t\tE\n")
				.append("\tC\n")
				.append("\t\tD\n")
				.append("\t\t\tE\n")
				.append("\t\tE\n");
		
		assertEquals(familyTree.prettyRepresentation(), expected.toString());
	}
	
	
	
	
	

}
