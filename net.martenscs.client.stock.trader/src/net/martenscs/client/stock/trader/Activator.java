package net.martenscs.client.stock.trader;

import org.eclipse.ui.plugin.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * The main plugin class to be used in the desktop.
 */
public class Activator extends AbstractUIPlugin {

	// The shared instance.
	private static Activator plugin;
	private static BundleContext context;
	private static final Logger logger = LoggerFactory
			.getLogger(Activator.class);

	/**
	 * The constructor.
	 */
	public Activator() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		Activator.context = context;
		logger.info("earlyStartup slf4j SLF4JBridgeHandler..."); //$NON-NLS-1$  

		// route java.util.logging to slf4j
		SLF4JBridgeHandler.install();

		logger.debug("SLF4JBridgeHandler started"); //$NON-NLS-1$

		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(
				"net.martenscs.client.stock.trader", path);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T lookupNoThow(final String clazz, final String filter) {

		T inst = null;
		try {
			ServiceReference[] refs = context.getServiceReferences(clazz,
					filter);
			if (refs == null || refs.length == 0) {

			} else if (refs.length > 1) {

			}
			if (refs != null)
				inst = (T) context.getService(refs[0]);
		} catch (InvalidSyntaxException x) {

		}

		return inst;
	}
}
