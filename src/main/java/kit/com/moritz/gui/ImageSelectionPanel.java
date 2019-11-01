package kit.com.moritz.gui;

import kit.com.moritz.util.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;

/**
 * The panel that contains the elements with which the image can be selected
 */
public class ImageSelectionPanel extends AbstractPanel {
	private JLabel labelFileChooser;

	private JButton fileButton;

	public ImageSelectionPanel(MainFrame frame) {
		super(frame);
	}

	@Override
	public void addPanel() {
		labelFileChooser = new JLabel();
		configureLabel(labelFileChooser, "Image to convert", FONT_SMALL, Color.WHITE, SCREEN_COLOR);
		//labelFileChooser.setBorder(BorderFactory.createLineBorder(Color.RED, 3, false));

		fileButton = new JButton("Search ...");
		fileButton.addActionListener( e -> {
			frame.setVisible(false);

			FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
			fd.setFilenameFilter(new FilenameFilter() {
				@Override
				public boolean accept(File f, String name) {
					if (f.isDirectory())
						return true;
					int lastIndexOf = f.getName().lastIndexOf('.');

					if (lastIndexOf == 0)
						return false;

					String extension = f.getName().substring(lastIndexOf + 1);
					return extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg");
				}
			});
			fd.setFile("*.jpg;*.jpeg;*.png");
			fd.setVisible(true);
			File[] files = fd.getFiles();

			if (files != null && files.length >= 1 && files[0] != null && files[0].isFile()) {
				File file = files[0];
				if (file != null && file.isFile()) {
					String extension = file.getName().substring(file.getName().lastIndexOf('.') + 1);
					if (extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg")) {
						frame.asciiConverter.setImage(files[0]);
						frame.enableButtons();
					}
				}
			}

			frame.setVisible(true);
		});
		fileButton.setForeground(Color.WHITE);
		fileButton.setBackground(SCREEN_COLOR);
		fileButton.setFont(FONT_SMALL);
		fileButton.setPreferredSize(new Dimension(200, 40));
		fileButton.setFocusPainted(false);
		//fileButton.setBorder(BorderFactory.createLineBorder(Color.RED, 3, false));

		JLabel filler = new JLabel("");
		filler.setPreferredSize(new Dimension(90, 10));
		//filler.setBorder(BorderFactory.createLineBorder(Color.RED, 3, false));

		setDefaultValuesGB();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 10, 0, 0);
		frame.add(labelFileChooser, gbc);

		gbc.gridx = 4;
		gbc.gridwidth = 4;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(0, 0, 0, 10);
		frame.add(fileButton, gbc);

		gbc.gridx = 8;
		gbc.gridwidth = 1;
		frame.add(filler, gbc);

		Logger.info("Successfully created the ImageSelection Panel");
	}
}
