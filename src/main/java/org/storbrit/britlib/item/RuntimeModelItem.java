package org.storbrit.britlib.item;

import net.devtech.arrp.json.models.JModel;

/**
 * An item which has a model in the runtime resource pack ({@link JModel}).
 */
public interface RuntimeModelItem {
	/**
	 * Gets the item's model.
	 *
	 * @return the item's model
	 */
	JModel getModel();
}
