package kit.com.moritz;

import kit.com.moritz.gui.MainFrame;
import kit.com.moritz.util.Logger;

import javax.swing.*;

public class Main {

    /** TODO
	 	Custom ColorPicker erstellen
	 */

	public static void main(String args[]) {

		if (args.length != 0) {
			Logger.warn("No commandline arguments are required / allowed");
		}

		Logger.info("Application launched");

		// Launch the GUI
		SwingUtilities.invokeLater(MainFrame::new);
	}

}
