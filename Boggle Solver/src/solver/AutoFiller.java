package solver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;

import javax.swing.UnsupportedLookAndFeelException;

/**
 * Automatically type words in a selected input field and press enter
 * 
 * @author Frank
 */
public class AutoFiller {
	
    private Robot robot;

    /**
     * Constructor
     * @throws AWTException
     */
    public AutoFiller() throws AWTException {
        this.robot = new Robot();
    }

    /**
     * Constructor
     * @param robot
     */
    public AutoFiller(Robot robot) {
        this.robot = robot;
    }

    /**
     * Type the given String and wait for the amount of milliseconds given by delay
     * @param s
     * @param delay
     */
    public void typeString(String s, int delay) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isUpperCase(c)) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }
            
            robot.keyPress(Character.toUpperCase(c));
            robot.keyRelease(Character.toUpperCase(c));

            if (Character.isUpperCase(c)) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
        }
        
        robot.delay(delay);
    }
    
    /**
     * Press the enter key and wait for the amount of milliseconds given by delay
     * @param delay
     */
    public void enter(int delay) {
    	robot.keyPress(KeyEvent.VK_ENTER);
    	robot.keyRelease(KeyEvent.VK_ENTER);
    	robot.delay(delay);
    }

	public static void main(String[] args) throws AWTException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		AutoFiller filler = new AutoFiller();

		BoardSolver solver = new BoardSolver(5, "Data/sowpods.txt");
		solver.sortWordList(false);
		Set<String> paths = solver.getWordMap().keySet();
		
		int minLength = 4;
		
		// Give some time to select the input field
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		// Type each word and press enter
		for (String s : paths) {
			if (s.length() >= minLength) {
				filler.typeString(s, 0);
				filler.enter(5);
			}
		}
	}
	
}
