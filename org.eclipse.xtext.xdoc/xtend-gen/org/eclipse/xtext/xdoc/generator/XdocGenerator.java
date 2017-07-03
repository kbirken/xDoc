package org.eclipse.xtext.xdoc.generator;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGenerator2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.xdoc.generator.EclipseHelpGenerator;
import org.eclipse.xtext.xdoc.generator.HtmlGenerator;

@SuppressWarnings("all")
public class XdocGenerator implements IGenerator2 {
  @Inject
  private HtmlGenerator htmlGen;
  
  @Inject
  private EclipseHelpGenerator helpGen;
  
  @Override
  public void doGenerate(final Resource input, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    this.helpGen.doGenerate(input, fsa);
    this.htmlGen.doGenerate(input, fsa);
  }
  
  @Override
  public void afterGenerate(final Resource input, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
  }
  
  @Override
  public void beforeGenerate(final Resource input, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
  }
}
