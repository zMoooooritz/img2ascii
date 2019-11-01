package kit.com.moritz.gui;

import kit.com.moritz.util.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * The panel that contains the header
 */
public class HeaderPanel extends AbstractPanel {

	private JLabel headerLabel;

	public HeaderPanel(MainFrame frame) {
		super(frame);
	}

	@Override
	public void addPanel() {
		headerLabel = new JLabel();

		headerLabel.setText("IMG   2   ASCII");
		headerLabel.setBorder(emptyBorder);
		headerLabel.setFont(FONT_MIDDLE);
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//headerLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 3, false));

		setDefaultValuesGB();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 13;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		frame.add(headerLabel, gbc);

		Logger.info("Successfully created the Header Panel");
	}
}
