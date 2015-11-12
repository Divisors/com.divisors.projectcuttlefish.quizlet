package com.divisors.projectcuttlefish.quizlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.divisors.projectcuttlefish.quizlet.api.Quizlet;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		Quizlet q = new Quizlet("user token".toCharArray());
		q.queryAPI("GET", "users/mailmindlin");
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
