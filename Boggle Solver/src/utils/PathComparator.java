package utils;

import solver.Path;

/**
 * Custom comparator for paths
 * 
 * @author Frank
 */
public class PathComparator implements java.util.Comparator<Path> {

	boolean ascending;
	
	public PathComparator(boolean ascending) {
		this.ascending = ascending;
	}
	
	@Override
	public int compare(Path path1, Path path2) {
		if (ascending) {
			return path1.size() - path2.size();
		} else {
			return path2.size() - path1.size();
		}
	}

}
