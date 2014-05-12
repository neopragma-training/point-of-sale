package pos.register.ui;

import static pos.common.Utils.message;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pos.register.Register;

/**
 * Lays out the GUI for a register and handles user interaction with the GUI.
 * 
 * @author neopragma
 */
public class RegisterUI {
	
	private SaleDetailPanel saleDetailPanel;
	private ProductSelectionPanel productSelectionPanel;
	
	public void init(Register register) throws UnknownHostException, IOException {
        JFrame frame = new JFrame(message("pos.register"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        rootPanel.add(new RegisterStatusPanel(register));
        rootPanel.add(new CashierLoginPanel());
        productSelectionPanel = new ProductSelectionPanel(register);
		rootPanel.add(productSelectionPanel);
		saleDetailPanel = new SaleDetailPanel(this);
		rootPanel.add(saleDetailPanel);
		rootPanel.add(new CardSwipePanel());
		rootPanel.add(new CashDrawerPanel());
        frame.getContentPane().add(rootPanel);
        frame.pack();
        frame.setVisible(true);
    }
	
	public void registerLineItemEventListener(LineItemEventListener listener) {
		productSelectionPanel.addLineItemEventListener(listener);
	}

}
