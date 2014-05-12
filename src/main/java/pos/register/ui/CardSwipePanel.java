package pos.register.ui;

import static pos.common.Utils.message;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Simulates a card swipe.
 * 
 * @author neopragma
 */
public class CardSwipePanel extends JPanel {

	private static final long serialVersionUID = 7190948028476562625L;
	private Border raisedBevel = BorderFactory.createRaisedBevelBorder();
	private Border loweredBevel = BorderFactory.createLoweredBevelBorder();
	private Border compoundBorder = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
	
	public CardSwipePanel() throws IOException {
        this.setBorder(compoundBorder);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel(message("card.swipe")));
        titlePanel.add(Box.createRigidArea(new Dimension(0,5)));
        this.add(titlePanel);
        
        JPanel swipePanel = new JPanel();
        swipePanel.setLayout(new BoxLayout(swipePanel, BoxLayout.X_AXIS));
        
        final JTextField cardNumber = new JTextField();
        cardNumber.setPreferredSize(new Dimension(120,24));
        cardNumber.setEditable(true);
        cardNumber.setToolTipText(message("enter.card.number"));
        swipePanel.add(cardNumber);
        
        final JButton cardSwipeButton = new JButton();
        cardSwipeButton.setText(message("swipe"));
        cardSwipeButton.setToolTipText(message("card.swipe"));
        cardSwipeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Simulating card swipe: " + cardNumber.getText());
			}
        	
        });
        swipePanel.add(cardSwipeButton);
        this.add(swipePanel);
	}

}
