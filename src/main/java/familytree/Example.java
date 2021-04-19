package familytree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Example {

	public static void main(String[] args) throws Exception {
		FamilyTree familyTree = new FamilyTree();
		System.out.println(args[0]);
		try {
			Scanner scanner = new Scanner(new File(args[0]));
			while (scanner.hasNextLine()) {
				String[] relation = scanner.nextLine().split("\\s+");
				familyTree.addRelation(relation[1], relation[0]);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (familyTree.hasCyclicRelations()) {
			throw new Exception("Cycle detected in family tree!");
		}
		
		System.out.println(familyTree.prettyRepresentation());
		
	}

}
