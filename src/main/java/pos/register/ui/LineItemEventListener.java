package pos.register.ui;

import java.util.EventListener;

/**
 * When the cashier submits a line item for a transaction, the ProductSelection panel fires 
 * a LineItemEvent to notify interested parties of the action. SaleDetailPanel registers as
 * a LineItemEventListener and consumes these events to build up the invoice for the sale.
 * 
 * @author neopragma
 */
public interface LineItemEventListener extends EventListener {
	
    void lineItemProcessed(LineItemEvent event);

}
