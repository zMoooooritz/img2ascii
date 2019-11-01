package kit.com.moritz.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class AbstractPanel {
	protected GridBagConstraints gbc = new GridBagConstraints();

	protected static final Font FONT_SMALL = new Font("Arial Black", Font.PLAIN, 25);
	protected static final Font FONT_MIDDLE = new Font("Arial Black", Font.PLAIN, 32);
	protected static final Font FONT_BIG = new Font("Arial Black", Font.PLAIN, 40);

	protected static final Color SCREEN_COLOR = new Color(38, 38, 38);

	protected Border emptyBorder = BorderFactory.createEmptyBorder();

	protected MainFrame frame;

	public AbstractPanel(MainFrame frame) {
		this.frame = frame;
		addPanel();
	}

	public abstract void addPanel();

	protected void setDefaultValuesGB() {
		gbc.gridx = GridBagConstraints.RELATIVE;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.ipadx = 0;
		gbc.ipady = 0;
	}

	protected void configurePanel(JPanel panel, LayoutManager layout, int width, int height, Color backColor) {
		if (layout != null) panel.setLayout(layout);
		panel.setPreferredSize(new Dimension(width, height));
		panel.setForeground(Color.WHITE);
		panel.setBackground(backColor);
	}

	protected void configureLabel(JLabel label, String text, Font font, Color foreColor, Color backColor) {
		label.setText(text);
		label.setFont(font);
		label.setForeground(foreColor);
		label.setBackground(backColor);
	}

	protected void configureRadioButton(JRadioButton radio, String text, Font font, Color foreColor, Color backColor) {
		radio.setText(text);
		radio.setFont(font);
		radio.setForeground(foreColor);
		radio.setBackground(backColor);
		radio.setFocusPainted(false);
	}
}