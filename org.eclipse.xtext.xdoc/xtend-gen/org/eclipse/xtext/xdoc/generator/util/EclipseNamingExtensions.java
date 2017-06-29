package org.eclipse.xtext.xdoc.generator.util;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xdoc.xdoc.AbstractSection;
import org.eclipse.xtext.xdoc.xdoc.Chapter;
import org.eclipse.xtext.xdoc.xdoc.ChapterRef;
import org.eclipse.xtext.xdoc.xdoc.Document;
import org.eclipse.xtext.xdoc.xdoc.Identifiable;
import org.eclipse.xtext.xdoc.xdoc.Part;
import org.eclipse.xtext.xdoc.xdoc.PartRef;
import org.eclipse.xtext.xdoc.xdoc.Section2Ref;
import org.eclipse.xtext.xdoc.xdoc.SectionRef;
import org.eclipse.xtext.xdoc.xdoc.XdocFile;

@SuppressWarnings("all")
public class EclipseNamingExtensions {
  public String getLocalId(final Identifiable identifiable) {
    String _switchResult = null;
    boolean _matched = false;
    if (identifiable instanceof ChapterRef) {
      _matched=true;
      _switchResult = this.getLocalId(((ChapterRef)identifiable).getChapter());
    }
    if (!_matched) {
      if (identifiable instanceof SectionRef) {
        _matched=true;
        _switchResult = this.getLocalId(((SectionRef)identifiable).getSection());
      }
    }
    if (!_matched) {
      if (identifiable instanceof Section2Ref) {
        _matched=true;
        _switchResult = this.getLocalId(((Section2Ref)identifiable).getSection2());
      }
    }
    if (!_matched) {
      {
        String _name = identifiable.getName();
        boolean _notEquals = (!Objects.equal(_name, null));
        if (_notEquals) {
          return URI.encodeFragment(identifiable.getName(), false);
        }
        EObject _eContainer = identifiable.eContainer();
        final AbstractSection parent = ((AbstractSection) _eContainer);
        boolean _equals = Objects.equal(parent, null);
        if (_equals) {
          return "0";
        } else {
          String _localId = this.getLocalId(parent);
          String _plus = (_localId + "_");
          int _indexOf = parent.eContents().indexOf(identifiable);
          return (_plus + Integer.valueOf(_indexOf));
        }
      }
    }
    return _switchResult;
  }
  
  public String getFullURL(final Identifiable identifiable) {
    String _switchResult = null;
    boolean _matched = false;
    if (identifiable instanceof PartRef) {
      _matched=true;
      _switchResult = this.getFullURL(((PartRef)identifiable).getPart());
    }
    if (!_matched) {
      if (identifiable instanceof ChapterRef) {
        _matched=true;
        _switchResult = this.getFullURL(((ChapterRef)identifiable).getChapter());
      }
    }
    if (!_matched) {
      if (identifiable instanceof SectionRef) {
        _matched=true;
        _switchResult = this.getFullURL(((SectionRef)identifiable).getSection());
      }
    }
    if (!_matched) {
      if (identifiable instanceof Section2Ref) {
        _matched=true;
        _switchResult = this.getFullURL(((Section2Ref)identifiable).getSection2());
      }
    }
    if (!_matched) {
      if (identifiable instanceof Chapter) {
        _matched=true;
        String _lastSegment = ((Chapter)identifiable).eResource().getURI().trimFileExtension().lastSegment();
        String _xifexpression = null;
        EObject _eContainer = ((Chapter)identifiable).eContainer();
        if ((_eContainer instanceof Part)) {
          String _xifexpression_1 = null;
          EObject _eContainer_1 = ((Chapter)identifiable).eContainer().eContainer();
          if ((_eContainer_1 instanceof Document)) {
            int _indexOf = ((Chapter)identifiable).eContainer().eContainer().eContents().indexOf(((Chapter)identifiable).eContainer());
            _xifexpression_1 = ("_" + Integer.valueOf(_indexOf));
          } else {
            _xifexpression_1 = "";
          }
          String _plus = (_xifexpression_1 + "_");
          int _indexOf_1 = ((Chapter)identifiable).eContainer().eContents().indexOf(identifiable);
          _xifexpression = (_plus + Integer.valueOf(_indexOf_1));
        } else {
          String _xifexpression_2 = null;
          EObject _eContainer_2 = ((Chapter)identifiable).eContainer();
          if ((_eContainer_2 instanceof Document)) {
            int _indexOf_2 = ((Chapter)identifiable).eContainer().eContents().indexOf(identifiable);
            _xifexpression_2 = ("_" + Integer.valueOf(_indexOf_2));
          } else {
            _xifexpression_2 = "";
          }
          _xifexpression = _xifexpression_2;
        }
        String _plus_1 = (_lastSegment + _xifexpression);
        _switchResult = (_plus_1 + ".html");
      }
    }
    if (!_matched) {
      EObject _eContainer = identifiable.eContainer();
      if ((_eContainer instanceof XdocFile)) {
        _matched=true;
        _switchResult = this.getResourceURL(identifiable);
      }
    }
    if (!_matched) {
      if (identifiable instanceof Part) {
        _matched=true;
        String _lastSegment = ((Part)identifiable).eResource().getURI().trimFileExtension().lastSegment();
        String _plus = (_lastSegment + "_");
        int _indexOf = ((Part)identifiable).eContainer().eContents().indexOf(identifiable);
        String _plus_1 = (_plus + Integer.valueOf(_indexOf));
        _switchResult = (_plus_1 + ".html");
      }
    }
    if (!_matched) {
      String _resourceURL = this.getResourceURL(identifiable);
      String _plus = (_resourceURL + "#");
      String _localId = this.getLocalId(identifiable);
      String _plus_1 = (_plus + _localId);
      _switchResult = (_plus_1 + ".html");
    }
    return _switchResult;
  }
  
  public String getResourceURL(final Identifiable identifiable) {
    String _switchResult = null;
    boolean _matched = false;
    if (identifiable instanceof PartRef) {
      _matched=true;
      _switchResult = this.getResourceURL(((PartRef)identifiable).getPart());
    }
    if (!_matched) {
      if (identifiable instanceof ChapterRef) {
        _matched=true;
        _switchResult = this.getResourceURL(((ChapterRef)identifiable).getChapter());
      }
    }
    if (!_matched) {
      if (identifiable instanceof SectionRef) {
        _matched=true;
        _switchResult = this.getResourceURL(((SectionRef)identifiable).getSection());
      }
    }
    if (!_matched) {
      if (identifiable instanceof Section2Ref) {
        _matched=true;
        _switchResult = this.getResourceURL(((Section2Ref)identifiable).getSection2());
      }
    }
    if (!_matched) {
      String _lastSegment = identifiable.eResource().getURI().trimFileExtension().lastSegment();
      return (_lastSegment + ".html");
    }
    return _switchResult;
  }
  
  public String getFullPHPURL(final Identifiable identifiable) {
    return this.getFullURL(identifiable).replace(".html", ".php");
  }
}
