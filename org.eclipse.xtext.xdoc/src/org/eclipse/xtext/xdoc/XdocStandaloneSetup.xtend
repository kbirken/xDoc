/*
 * generated by Xtext
 */
package org.eclipse.xtext.xdoc

import com.google.inject.Injector
import org.eclipse.emf.ecore.EPackage.Registry
import org.eclipse.xtext.xdoc.xdoc.XdocPackage
import org.eclipse.emf.ecore.EPackage

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class XdocStandaloneSetup extends XdocStandaloneSetupGenerated {

	def static void doSetup() {
		new XdocStandaloneSetup().createInjectorAndDoEMFRegistration()
	}

	override void register(Injector injector) {
		val registry = injector.getInstance(typeof(EPackage.Registry))
		registry.put(XdocPackage.eINSTANCE.getNsURI(), XdocPackage.eINSTANCE)
		super.register(injector)
	}
}