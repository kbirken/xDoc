package org.eclipse.xtext.xdoc.generator

import com.google.inject.Inject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGenerator2
import org.eclipse.xtext.generator.IGeneratorContext

class XdocGenerator implements IGenerator2 {
	@Inject HtmlGenerator htmlGen
	@Inject EclipseHelpGenerator helpGen
	
	override doGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
		helpGen.doGenerate(input, fsa)
		htmlGen.doGenerate(input, fsa)
	}
	
	override afterGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
	}
	
	override beforeGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
	}
	
}
