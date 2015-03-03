package solver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Automatically copy and paste the words in a selected input field and press enter
 * 
 * @author Frank
 */
public class AutoFiller {

	public static void main(String[] args) {
		BoardSolver solver = new BoardSolver(5, "Data/sowpods.txt");
		solver.sortWordList(false);
		List<Path> paths = solver.getWordList();
		
		int minLength = 4;
		
		// Give some time to select the input field
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		// Copy and paste each word and press enter
		for (Path s : paths) {
			if (s.size() >= minLength) {
				try {
					Robot robot = new Robot();
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					
					StringSelection stringSelection = new StringSelection(s.toString());
					clipboard.setContents(stringSelection, stringSelection);
		
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					
					Thread.sleep(150);
				} catch (AWTException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
