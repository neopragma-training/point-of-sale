package pos.register.ui;

import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import static pos.common.Utils.message;

/**
 * Displays the currency in the register's cash drawer.
 * 
 * @author neopragma
 */
public class CashDrawerPanel extends JPanel {

	private static final long serialVersionUID = -8189078071964728634L;
	private Border raisedBevel = BorderFactory.createRaisedBevelBorder();
	private Border loweredBevel = BorderFactory.createLoweredBevelBorder();
	private Border compoundBorder = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
	private static final String IMAGE_PATH = "images/";
	private static final String IMAGE_FILENAME_PART = "_dollar_bill.jpg";

	public CashDrawerPanel() throws IOException {
		this.setBorder(compoundBorder);
	    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    
	    JPanel titlePanel = new JPanel();
	    titlePanel.setLayout(new FlowLayout());
	    titlePanel.add(new JLabel(message("cash.drawer")));
		titlePanel.add(Box.createRigidArea(new Dimension(0,5)));
		titlePanel.add(Box.createRigidArea(new Dimension(0,5)));
		this.add(titlePanel);
		this.add(imagePanel());
	}
	
	private JPanel imagePanel() {
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));
		imagePanel.add(Box.createRigidArea(new Dimension(15,0)));
		
		JPanel coinWrapper = new JPanel();
		coinWrapper.setLayout(new BoxLayout(coinWrapper, BoxLayout.Y_AXIS));
		coinWrapper.add(coinPanel(IMAGE_PATH + "quarter.jpg"));
		coinWrapper.add(Box.createRigidArea(new Dimension(15,0)));
		coinWrapper.add(coinPanel(IMAGE_PATH + "dime.jpg"));
		coinWrapper.add(Box.createRigidArea(new Dimension(15,0)));
		coinWrapper.add(coinPanel(IMAGE_PATH + "nickel.jpg"));
		coinWrapper.add(Box.createRigidArea(new Dimension(15,0)));
		coinWrapper.add(coinPanel(IMAGE_PATH + "penny.jpg"));
		coinWrapper.add(Box.createRigidArea(new Dimension(15,0)));
		imagePanel.add(coinWrapper);
		imagePanel.add(Box.createRigidArea(new Dimension(15,0)));
		
		for (String denomination : new String[] { "1", "5", "10", "20" }) {
			JPanel wrapperPanel = new JPanel();
			wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
			wrapperPanel.add(new JLabel("10"));
			wrapperPanel.add(imageLabel(IMAGE_PATH + denomination + IMAGE_FILENAME_PART));
			imagePanel.add(wrapperPanel);
			imagePanel.add(Box.createRigidArea(new Dimension(15,0)));
		}
		return imagePanel;
	}
	
	private JLabel imageLabel(String pathToImageFile) {
		JLabel imageLabel = new JLabel();
		try {
			BufferedImage image = ImageIO.read(new File(pathToImageFile));
			imageLabel.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			imageLabel.setText("No image");
		}
        return imageLabel;		
	}
	
	private JPanel coinPanel(String pathToImageFile) {
		JPanel coinPanel = new JPanel();
		coinPanel.setLayout(new BoxLayout(coinPanel, BoxLayout.Y_AXIS));
		coinPanel.add(new JLabel("10"));
		coinPanel.add(imageLabel(pathToImageFile));
		return coinPanel;
	}

}
