/*
 * generated by Xtext
 */
package org.eclipse.xtext.xdoc.ide

import com.google.inject.Guice
import org.eclipse.xtext.util.Modules2
import org.eclipse.xtext.xdoc.XdocRuntimeModule
import org.eclipse.xtext.xdoc.XdocStandaloneSetup

/**
 * Initialization support for running Xtext languages as language servers.
 */
class XdocIdeSetup extends XdocStandaloneSetup {

	override createInjector() {
		Guice.createInjector(Modules2.mixin(new XdocRuntimeModule, new XdocIdeModule))
	}
	
}
