package kit.com.moritz.gui;

import kit.com.moritz.util.Constants;
import kit.com.moritz.util.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * The panel that contains all the buttons of the lower part of the GUI
 */
public class LowerButtonPanel extends AbstractPanel {

	private JButton generateButton, copyButton, createButton;

	public LowerButtonPanel(MainFrame frame) {
		super(frame);
	}

	@Override
	public void addPanel() {
		int width = (Constants.WINDOW_WIDTH / 2) - 30;

		generateButton = new JButton("Create Filtered-Image-Preview");
		generateButton.setForeground(Color.WHITE);
		generateButton.setBackground(SCREEN_COLOR);
		generateButton.setFont(FONT_SMALL);
		generateButton.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH - Constants.IMAGE_WIDTH - 30, 40));
		generateButton.setFocusPainted(false);
		//generateButton.setBorder(BorderFactory.createLineBorder(Color.RED, 3, false));
		generateButton.setEnabled(false);
		generateButton.addActionListener(actionEvent -> frame.asciiConverter.createPreview());

		copyButton = new JButton("Copy ASCII-ART to Clipboard");
		copyButton.setForeground(Color.WHITE);
		copyButton.setBackground(SCREEN_COLOR);
		copyButton.setFont(FONT_SMALL);
		copyButton.setPreferredSize(new Dimension(width, 40));
		copyButton.setFocusPainted(false);
		//copyButton.setBorder(BorderFactory.createLineBorder(Color.RED, 3, false));
		copyButton.setEnabled(false);
		copyButton.addActionListener(actionEvent -> frame.asciiConverter.copyToClipboard());

		createButton = new JButton("Create File with ASCII-ART");
		createButton.setForeground(Color.WHITE);
		createButton.setBackground(SCREEN_COLOR);
		createButton.setFont(FONT_SMALL);
		createButton.setPreferredSize(new Dimension(width, 40));
		createButton.setFocusPainted(false);
		//createButton.setBorder(BorderFactory.createLineBorder(Color.RED, 3, false));
		createButton.setEnabled(false);
		createButton.addActionListener(actionEvent -> frame.asciiConverter.createFile());

		setDefaultValuesGB();
		gbc.gridx = 0;
		gbc.gridy = 12;
		gbc.gridwidth = 9;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(-50, 10, 0, 0);
		frame.add(generateButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 13;
		gbc.gridwidth = 7;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.insets = new Insets(0, 10, 10, 0);
		frame.add(copyButton, gbc);

		gbc.gridx = 6;
		gbc.gridwidth = 7;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		gbc.insets = new Insets(0, 0, 10, 10);
		frame.add(createButton, gbc);

		Logger.info("Successfully created the LowerButton Panel");

	}

	public void enableButtons() {
		generateButton.setEnabled(true);
		copyButton.setEnabled(true);
		createButton.setEnabled(true);
	}
}
