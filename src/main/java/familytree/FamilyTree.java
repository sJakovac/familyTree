package familytree;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FamilyTree {

	private DirectedGraph<String> familyTreeGraph;
	private Set<String> orphans;
	
	public FamilyTree() {
		familyTreeGraph = new DirectedGraph<String>();
		orphans = new HashSet<String>();
	}
	
	public Set<String> getOrphans() {
		return Collections.unmodifiableSet(orphans);
	}
	
	public void addRelation(String parent, String child) {
		if (!familyTreeGraph.hasVertix(parent)) orphans.add(parent);
		if (orphans.contains(child)) orphans.remove(child);
		
		familyTreeGraph.addEdge(parent, child);
	}

	public List<String> getKids(String parent) {
		return familyTreeGraph.getNeighbours(parent);
	}
	
	public boolean hasCyclicRelations() {
		return familyTreeGraph.hasCycle();
	}
	
	public String prettyRepresentation() {
		StringBuilder repr = new StringBuilder();
		for (String orphan : orphans) {
			representSubFamily(0, orphan, repr);
		}
		
		return repr.toString();
	}
	
	private void representSubFamily(int depth, String parent, StringBuilder repr) {
		for (int i = 0; i < depth; i++) repr.append("\t");
		repr.append(parent); repr.append("\n");
		
		for (String child : getKids(parent)) {
			representSubFamily(depth + 1, child, repr);
		}
	} 
}
