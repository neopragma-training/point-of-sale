package pos.register.ui;

import static pos.common.Utils.message;

import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import pos.register.Register;

/**
 * Displays the current operating status of the register.
 * 
 * @author neopragma
 */
public class RegisterStatusPanel extends JPanel {

	private static final long serialVersionUID = -8699399772769135204L;
	private Border raisedBevel = BorderFactory.createRaisedBevelBorder();
	private Border loweredBevel = BorderFactory.createLoweredBevelBorder();
	private Border compoundBorder = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
	private String cashierDisplayName = null;

	public RegisterStatusPanel(Register register) throws IOException {
        this.setBorder(compoundBorder);
        this.setLayout(new FlowLayout());

        JPanel registerIdPanel = standardPanel();
        registerIdPanel.add(new JLabel(message("register", register.getRegisterIdentifier())));
        this.add(registerIdPanel);
        
        JPanel registerStatusPanel = standardPanel();
        registerStatusPanel.add(new JLabel(message("status", message(register.getStatus().toString().toLowerCase()))));
        this.add(registerStatusPanel);
        
        JPanel cashierIdPanel = standardPanel();
        cashierIdPanel.add(new JLabel(
        	cashierDisplayName == null ? message("cashier", message("none")) : cashierDisplayName));
        this.add(cashierIdPanel);
	}	

	private JPanel standardPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(compoundBorder);
        return panel;
	}
}
