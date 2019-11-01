package kit.com.moritz.gui;

import kit.com.moritz.util.Constants;
import kit.com.moritz.util.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * The panel that contains the two subpanels that contain the previewimage and filteredpreviewimage
 */
public class DisplayPanel extends AbstractPanel {

	ImagePanel inputPanel, outputPanel;

	public DisplayPanel(MainFrame frame) {
		super(frame);
	}

	@Override
	public void addPanel() {
		inputPanel = new ImagePanel(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT);
		inputPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5, false));
		configurePanel(inputPanel, null, Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT, SCREEN_COLOR);

		outputPanel = new ImagePanel(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT);
		outputPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5, false));
		configurePanel(outputPanel, null, Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT, SCREEN_COLOR);

		setDefaultValuesGB();
		gbc.gridx = 9;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.gridheight = 3;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 0, 0, 10);
		frame.add(inputPanel, gbc);

		gbc.gridx = 9;
		gbc.gridy = 4;
		frame.add(outputPanel, gbc);

		Logger.info("Successfully created the Display Panel");
	}
}
