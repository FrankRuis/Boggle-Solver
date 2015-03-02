package utils;

import solver.Path;

/**
 * Custom comparator for paths
 * 
 * @author Frank
 */
public class PathComparator implements java.util.Comparator<Path> {

	@Override
	public int compare(Path path1, Path path2) {
		return path1.size() - path2.size();
	}

}
