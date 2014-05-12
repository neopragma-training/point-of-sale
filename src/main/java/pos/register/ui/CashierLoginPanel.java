package pos.register.ui;

import static pos.common.Utils.message;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Allows an employee to sign in to the register as a cashier so the register can be used
 * to transact sales.
 * 
 * @author neopragma
 */
public class CashierLoginPanel extends JPanel {
	
	private static final long serialVersionUID = 9093123756452078069L;
	Border raisedBevel = BorderFactory.createRaisedBevelBorder();
	Border loweredBevel = BorderFactory.createLoweredBevelBorder();
	Border compoundBorder = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
	
	public CashierLoginPanel() throws IOException {
        this.setBorder(compoundBorder);
        
        this.add(new JLabel(message("cashier.login")));
        
        JTextField employeeId = new JTextField();
        employeeId.setPreferredSize(new Dimension(120,24));
        employeeId.setEditable(true);
        employeeId.setToolTipText(message("enter.employee.id"));
        this.add(employeeId);
        
        JButton cashierLoginButton = new JButton();
        cashierLoginButton.setText(message("submit"));
        this.add(cashierLoginButton);
	}

}
