package kit.com.moritz.gui;

import kit.com.moritz.util.Constants;
import kit.com.moritz.util.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * The panel that contains the filter selection and all the filter panels
 */
public class FilterPanel extends AbstractPanel {

	private JLabel filterLabel, labelBright, labelDark, labelTolerance, labelBrightVal, labelTolVal;

	private JToggleButton filterToggle;

	private JRadioButton radio1, radio2, radio3, radio4;

	private JSlider brightSlider, toleranceSlider;

	private ButtonGroup filter;

	private JPanel advancedFilterPanel, upperFilterPanel, midFilterPanel, lowerFilterPanel;

	private Color filterColor = null;

	private JButton colorButton;


	public FilterPanel(MainFrame frame) {
		super(frame);
	}

	@Override
	public void addPanel() {
		filterLabel = new JLabel();
		configureLabel(filterLabel, "Filter", FONT_SMALL, Color.WHITE, SCREEN_COLOR);

		filterToggle = new JToggleButton();
		filterToggle.setIcon(new ImageIcon(frame.toggleButton1Off));
		filterToggle.setForeground(Color.WHITE);
		filterToggle.setBackground(SCREEN_COLOR);
		filterToggle.setPreferredSize(new Dimension(200, 30));
		filterToggle.addActionListener(actionEvent -> {
			toggleFilterSelection();
			filterToggle.setIcon(new ImageIcon(filterToggle.isSelected() ? frame.toggleButton1On : frame.toggleButton1Off));
		});

		radio1 = new JRadioButton();
		configureRadioButton(radio1, "Normal-Filter", FONT_SMALL, Color.WHITE, SCREEN_COLOR);
		radio1.addActionListener(actionEvent -> addAdvancedFilterPanel(1));

		radio2 = new JRadioButton();
		configureRadioButton(radio2, "Invert-Filter", FONT_SMALL, Color.WHITE, SCREEN_COLOR);
		radio2.addActionListener(actionEvent -> addAdvancedFilterPanel(2));

		radio3 = new JRadioButton();
		configureRadioButton(radio3, "Include-Filter", FONT_SMALL, Color.WHITE, SCREEN_COLOR);
		radio3.addActionListener(actionEvent -> addAdvancedFilterPanel(3));

		radio4 = new JRadioButton();
		configureRadioButton(radio4, "Exclude-Filter", FONT_SMALL, Color.WHITE, SCREEN_COLOR);
		radio4.addActionListener(actionEvent -> addAdvancedFilterPanel(4));

		filter = new ButtonGroup();
		filter.add(radio1);
		filter.add(radio2);
		filter.add(radio3);
		filter.add(radio4);

		advancedFilterPanel = new JPanel();
		configurePanel(advancedFilterPanel, new BorderLayout(), Constants.WINDOW_WIDTH - Constants.IMAGE_WIDTH - 30, 110, SCREEN_COLOR);
		//advancedFilterPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 3, false));

		toggleFilterSelection();

		setDefaultValuesGB();
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-80, 10, 0, 0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		frame.add(filterLabel, gbc);

		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.gridwidth = 6;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-80, 0, 0, 0);
		gbc.anchor = GridBagConstraints.PAGE_START;
		frame.add(filterToggle, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-110, 50, 0, 0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		frame.add(radio1, gbc);

		gbc.gridx = 4;
		gbc.gridy = 5;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-110, 50, 0, 0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		frame.add(radio2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-140, 50, 0, 0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		frame.add(radio3, gbc);

		gbc.gridx = 4;
		gbc.gridy = 6;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-140, 50, 0, 0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		frame.add(radio4, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 9;
		gbc.gridheight = 5;
		gbc.insets = new Insets(-170, 10, 0, 0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		frame.add(advancedFilterPanel, gbc);

		Logger.info("Successfully created the Filter Panel");
	}

	private void toggleFilterSelection() {
		filter.clearSelection();
		radio1.setEnabled(filterToggle.isSelected());
		radio2.setEnabled(filterToggle.isSelected());
		radio3.setEnabled(filterToggle.isSelected());
		radio4.setEnabled(filterToggle.isSelected());

		if (!filterToggle.isSelected()) {
			advancedFilterPanel.removeAll();
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}

	private void addAdvancedFilterPanel(int filterIndex) {
		advancedFilterPanel.removeAll();

		if (filterIndex == 1 || filterIndex == 2) {
			labelBright = new JLabel();
			configureLabel(labelBright, "Bright", FONT_SMALL, Color.WHITE, SCREEN_COLOR);

			brightSlider = new JSlider(JSlider.HORIZONTAL, -255, 255, 0);
			brightSlider.setPreferredSize(new Dimension(400, 20));
			brightSlider.setForeground(Color.WHITE);
			brightSlider.setBackground(SCREEN_COLOR);

			labelDark = new JLabel();
			configureLabel(labelDark, "Dark", FONT_SMALL, Color.WHITE, SCREEN_COLOR);

			advancedFilterPanel.add(labelBright, BorderLayout.WEST);
			advancedFilterPanel.add(brightSlider, BorderLayout.CENTER);
			advancedFilterPanel.add(labelDark, BorderLayout.EAST);
		} else if (filterIndex == 3 || filterIndex == 4) {
			colorButton = new JButton("COLOR-SELECTION");
			colorButton.addActionListener( e -> {
				frame.setVisible(false);

				Color colorResult = JColorChooser.showDialog(null, "Select a filter color", filterColor != null ? filterColor : colorButton.getBackground());

				if (colorResult != null) {
					filterColor = colorResult;
					colorButton.setBackground(colorResult);
				}
				frame.setVisible(true);
			});
			colorButton.setForeground(Color.WHITE);
			colorButton.setBackground(filterColor != null ? filterColor : SCREEN_COLOR);
			colorButton.setFont(FONT_SMALL);
			colorButton.setPreferredSize(new Dimension(200, 40));
			colorButton.setFocusPainted(false);

			labelTolerance = new JLabel();
			configureLabel(labelTolerance, "Tolerance", FONT_SMALL, Color.WHITE, SCREEN_COLOR);
			labelTolerance.setPreferredSize(new Dimension(150, 40));

			toleranceSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
			toleranceSlider.setPreferredSize(new Dimension(200, 20));
			toleranceSlider.addChangeListener(e -> labelTolVal.setText(toleranceSlider.getValue() + " "));
			toleranceSlider.setForeground(Color.WHITE);
			toleranceSlider.setBackground(SCREEN_COLOR);

			labelTolVal = new JLabel();
			configureLabel(labelTolVal, "0 ", FONT_SMALL, Color.WHITE, SCREEN_COLOR);
			labelTolVal.setPreferredSize(new Dimension(80, 40));
			labelTolVal.setHorizontalAlignment(SwingConstants.RIGHT);

			labelBright = new JLabel();
			if (filterIndex == 3)
				configureLabel(labelBright, "Darkness", FONT_SMALL, Color.WHITE, SCREEN_COLOR);
			else
				configureLabel(labelBright, "Brightness", FONT_SMALL, Color.WHITE, SCREEN_COLOR);
			labelBright.setPreferredSize(new Dimension(150, 40));

			if (filterIndex == 3)
				brightSlider = new JSlider(JSlider.HORIZONTAL, 0, 127, 0);
			else
				brightSlider = new JSlider(JSlider.HORIZONTAL, 127, 255, 255);
			brightSlider.setPreferredSize(new Dimension(100, 20));
			brightSlider.addChangeListener(e -> labelBrightVal.setText(brightSlider.getValue() + " "));
			brightSlider.setForeground(Color.WHITE);
			brightSlider.setBackground(SCREEN_COLOR);

			labelBrightVal = new JLabel();
			if (filterIndex == 3)
				configureLabel(labelBrightVal, "0 ", FONT_SMALL, Color.WHITE, SCREEN_COLOR);
			else
				configureLabel(labelBrightVal, "255 ", FONT_SMALL, Color.WHITE, SCREEN_COLOR);
			labelBrightVal.setPreferredSize(new Dimension(80, 40));
			labelBrightVal.setHorizontalAlignment(SwingConstants.RIGHT);

			upperFilterPanel = new JPanel();
			upperFilterPanel.setLayout(new BorderLayout());
			upperFilterPanel.setBackground(SCREEN_COLOR);

			midFilterPanel = new JPanel();
			midFilterPanel.setLayout(new BorderLayout());
			midFilterPanel.setBackground(SCREEN_COLOR);

			lowerFilterPanel = new JPanel();
			lowerFilterPanel.setLayout(new BorderLayout());
			lowerFilterPanel.setBackground(SCREEN_COLOR);

			upperFilterPanel.add(colorButton, BorderLayout.CENTER);

			midFilterPanel.add(labelTolerance, BorderLayout.WEST);
			midFilterPanel.add(toleranceSlider, BorderLayout.CENTER);
			midFilterPanel.add(labelTolVal, BorderLayout.EAST);

			lowerFilterPanel.add(labelBright, BorderLayout.WEST);
			lowerFilterPanel.add(brightSlider, BorderLayout.CENTER);
			lowerFilterPanel.add(labelBrightVal, BorderLayout.EAST);

			advancedFilterPanel.add(upperFilterPanel, BorderLayout.NORTH);
			advancedFilterPanel.add(midFilterPanel, BorderLayout.CENTER);
			advancedFilterPanel.add(lowerFilterPanel, BorderLayout.SOUTH);
		}

		SwingUtilities.updateComponentTreeUI(frame);
	}

	public void setFilterIndex() {
		if (radio1.isSelected())
			frame.asciiConverter.setFilterIndex(1);
		else if (radio2.isSelected())
			frame.asciiConverter.setFilterIndex(2);
		else if (radio3.isSelected())
			frame.asciiConverter.setFilterIndex(3);
		else if (radio4.isSelected())
			frame.asciiConverter.setFilterIndex(4);
		else
			frame.asciiConverter.setFilterIndex(0);
	}

	public void setBrightness() {
		if (radio1.isSelected() || radio2.isSelected())
			frame.asciiConverter.setBrightness(Math.negateExact(brightSlider.getValue()));
		else if (radio3.isSelected() || radio4.isSelected())
			frame.asciiConverter.setBrightness(brightSlider.getValue());
		else
			frame.asciiConverter.setBrightness(0);
	}

	public void setFilterColor() {
		if (radio3.isSelected() || radio4.isSelected())
			frame.asciiConverter.setFilterColor(filterColor);
		else
			frame.asciiConverter.setFilterColor(null);
	}

	public void setTolerance() {
		if (radio3.isSelected() || radio4.isSelected())
			frame.asciiConverter.setTolerance(toleranceSlider.getValue());
		else
			frame.asciiConverter.setTolerance(0);
	}

}
