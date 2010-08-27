/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.xtext.xdoc.xdoc.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.xdoc.xdoc.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.xtext.xdoc.xdoc.XdocPackage
 * @generated
 */
public class XdocSwitch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static XdocPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XdocSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = XdocPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  public T doSwitch(EObject theEObject)
  {
    return doSwitch(theEObject.eClass(), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(EClass theEClass, EObject theEObject)
  {
    if (theEClass.eContainer() == modelPackage)
    {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    }
    else
    {
      List<EClass> eSuperTypes = theEClass.getESuperTypes();
      return
        eSuperTypes.isEmpty() ?
          defaultCase(theEObject) :
          doSwitch(eSuperTypes.get(0), theEObject);
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case XdocPackage.DOCUMENT:
      {
        Document document = (Document)theEObject;
        T result = caseDocument(document);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.CHAPTER:
      {
        Chapter chapter = (Chapter)theEObject;
        T result = caseChapter(chapter);
        if (result == null) result = caseIdentifiable(chapter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.SECTION:
      {
        Section section = (Section)theEObject;
        T result = caseSection(section);
        if (result == null) result = caseIdentifiable(section);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.SUB_SECTION:
      {
        SubSection subSection = (SubSection)theEObject;
        T result = caseSubSection(subSection);
        if (result == null) result = caseIdentifiable(subSection);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.SECTION3:
      {
        Section3 section3 = (Section3)theEObject;
        T result = caseSection3(section3);
        if (result == null) result = caseIdentifiable(section3);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.SECTION4:
      {
        Section4 section4 = (Section4)theEObject;
        T result = caseSection4(section4);
        if (result == null) result = caseIdentifiable(section4);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.IDENTIFIABLE:
      {
        Identifiable identifiable = (Identifiable)theEObject;
        T result = caseIdentifiable(identifiable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.TEXT_OR_MARKUP:
      {
        TextOrMarkup textOrMarkup = (TextOrMarkup)theEObject;
        T result = caseTextOrMarkup(textOrMarkup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.TEXT_PART:
      {
        TextPart textPart = (TextPart)theEObject;
        T result = caseTextPart(textPart);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.MARK_UP:
      {
        MarkUp markUp = (MarkUp)theEObject;
        T result = caseMarkUp(markUp);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.EMPHASIZE:
      {
        Emphasize emphasize = (Emphasize)theEObject;
        T result = caseEmphasize(emphasize);
        if (result == null) result = caseMarkUp(emphasize);
        if (result == null) result = caseMarkupInCode(emphasize);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.ANCHOR:
      {
        Anchor anchor = (Anchor)theEObject;
        T result = caseAnchor(anchor);
        if (result == null) result = caseIdentifiable(anchor);
        if (result == null) result = caseMarkUp(anchor);
        if (result == null) result = caseMarkupInCode(anchor);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.REF:
      {
        Ref ref = (Ref)theEObject;
        T result = caseRef(ref);
        if (result == null) result = caseMarkUp(ref);
        if (result == null) result = caseMarkupInCode(ref);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.ORDERED_LIST:
      {
        OrderedList orderedList = (OrderedList)theEObject;
        T result = caseOrderedList(orderedList);
        if (result == null) result = caseMarkUp(orderedList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.UNORDERED_LIST:
      {
        UnorderedList unorderedList = (UnorderedList)theEObject;
        T result = caseUnorderedList(unorderedList);
        if (result == null) result = caseMarkUp(unorderedList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.ITEM:
      {
        Item item = (Item)theEObject;
        T result = caseItem(item);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.CODE_REF:
      {
        CodeRef codeRef = (CodeRef)theEObject;
        T result = caseCodeRef(codeRef);
        if (result == null) result = caseMarkUp(codeRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.LINK:
      {
        Link link = (Link)theEObject;
        T result = caseLink(link);
        if (result == null) result = caseMarkUp(link);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.IMAGE_REF:
      {
        ImageRef imageRef = (ImageRef)theEObject;
        T result = caseImageRef(imageRef);
        if (result == null) result = caseMarkUp(imageRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.CODE_BLOCK:
      {
        CodeBlock codeBlock = (CodeBlock)theEObject;
        T result = caseCodeBlock(codeBlock);
        if (result == null) result = caseMarkUp(codeBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.CODE:
      {
        Code code = (Code)theEObject;
        T result = caseCode(code);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XdocPackage.MARKUP_IN_CODE:
      {
        MarkupInCode markupInCode = (MarkupInCode)theEObject;
        T result = caseMarkupInCode(markupInCode);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Document</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Document</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDocument(Document object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Chapter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Chapter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseChapter(Chapter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Section</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Section</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSection(Section object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Sub Section</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Sub Section</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSubSection(SubSection object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Section3</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Section3</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSection3(Section3 object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Section4</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Section4</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSection4(Section4 object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Identifiable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Identifiable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIdentifiable(Identifiable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Text Or Markup</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Text Or Markup</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTextOrMarkup(TextOrMarkup object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Text Part</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Text Part</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTextPart(TextPart object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mark Up</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mark Up</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMarkUp(MarkUp object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Emphasize</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Emphasize</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEmphasize(Emphasize object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Anchor</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Anchor</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAnchor(Anchor object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRef(Ref object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ordered List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ordered List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrderedList(OrderedList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unordered List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unordered List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnorderedList(UnorderedList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Item</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Item</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseItem(Item object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Code Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Code Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCodeRef(CodeRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Link</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLink(Link object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Image Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Image Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseImageRef(ImageRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Code Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Code Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCodeBlock(CodeBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Code</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Code</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCode(Code object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Markup In Code</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Markup In Code</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMarkupInCode(MarkupInCode object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public T defaultCase(EObject object)
  {
    return null;
  }

} //XdocSwitch