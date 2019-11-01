package kit.com.moritz.gui;

import kit.com.moritz.util.Constants;
import kit.com.moritz.util.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * The panel that contains all the elements to control the block size
 */
public class BlockSizePanel extends AbstractPanel {
	
	private JLabel labelBlockSize, currentWidth, currentHeight, labelWidth, labelHeight;
	
	private JSlider sliderWidth, sliderHeight;

	private JToggleButton toggleBlockSize;

	public BlockSizePanel(MainFrame frame) {
		super(frame);
	}

	@Override
	public void addPanel() {
		labelBlockSize = new JLabel();
		configureLabel(labelBlockSize, "Block-Size", FONT_SMALL, Color.WHITE, SCREEN_COLOR);

		toggleBlockSize = new JToggleButton();
		toggleBlockSize.setIcon(new ImageIcon(frame.toggleButton2Off));
		toggleBlockSize.setForeground(Color.WHITE);
		toggleBlockSize.setBackground(SCREEN_COLOR);
		toggleBlockSize.setPreferredSize(new Dimension(25, 75));
		toggleBlockSize.addActionListener(actionEvent -> {
			toggleBlockSize.setIcon(new ImageIcon(toggleBlockSize.isSelected() ? frame.toggleButton2On : frame.toggleButton2Off));
			sliderWidth.setEnabled(toggleBlockSize.isSelected());
			sliderHeight.setEnabled(toggleBlockSize.isSelected());
			sliderWidth.setValue(toggleBlockSize.isSelected() ? Constants.DEFAULT_BLOCK_WIDTH : 1);
			sliderHeight.setValue(toggleBlockSize.isSelected() ? Constants.DEFAULT_BLOCK_HEIGHT : 1);
			currentWidth.setText(toggleBlockSize.isSelected() ? sliderWidth.getValue() + "" : "AUTO");
			currentHeight.setText(toggleBlockSize.isSelected() ? sliderHeight.getValue() + "" : "AUTO");
		});

		labelWidth = new JLabel();
		configureLabel(labelWidth, "Width: ", FONT_SMALL, Color.WHITE, SCREEN_COLOR);

		sliderWidth = new JSlider(JSlider.HORIZONTAL, 1, 50, 1);
		sliderWidth.addChangeListener(e -> currentWidth.setText(sliderWidth.getValue() + ""));
		sliderWidth.setForeground(Color.WHITE);
		sliderWidth.setBackground(SCREEN_COLOR);
		sliderWidth.setEnabled(false);

		currentWidth = new JLabel();
		configureLabel(currentWidth, "AUTO", FONT_SMALL, Color.WHITE, SCREEN_COLOR);

		labelHeight = new JLabel();
		configureLabel(labelHeight, "Height: ", FONT_SMALL, Color.WHITE, SCREEN_COLOR);

		sliderHeight = new JSlider(JSlider.HORIZONTAL, 1, 50, 1);
		sliderHeight.addChangeListener(e -> currentHeight.setText(sliderHeight.getValue() + ""));
		sliderHeight.setForeground(Color.WHITE);
		sliderHeight.setBackground(SCREEN_COLOR);
		sliderHeight.setEnabled(false);

		currentHeight = new JLabel();
		configureLabel(currentHeight, "AUTO", FONT_SMALL, Color.WHITE, SCREEN_COLOR);

		setDefaultValuesGB();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-40, 10, 0, 0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		frame.add(labelBlockSize, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.insets = new Insets(-40, 0, 0, 0);
		frame.add(toggleBlockSize, gbc);

		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-40, 0, 0, 0);
		frame.add(labelWidth, gbc);

		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-60, 0, 0, 0);
		frame.add(labelHeight, gbc);

		gbc.gridx = 4;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-30, 0, 0, 0);
		frame.add(sliderWidth, gbc);

		gbc.gridx = 4;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-50, 0, 0, 0);
		frame.add(sliderHeight, gbc);

		gbc.gridx = 8;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-40, 0, 0, 0);
		frame.add(currentWidth, gbc);

		gbc.gridx = 8;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-60, 0, 0, 0);
		frame.add(currentHeight, gbc);

		Logger.info("Successfully created the BlockSize Panel");
	}

	public void setBlockSizeValues() {
		if (toggleBlockSize.isSelected())
			frame.asciiConverter.setBlockSizeValues(new Dimension(sliderWidth.getValue(), sliderHeight.getValue()));
		else
			frame.asciiConverter.setBlockSizeValues(new Dimension(Constants.DEFAULT_BLOCK_WIDTH, Constants.DEFAULT_BLOCK_HEIGHT));
	}
}
