package org.eclipse.xtext.xdoc.generator

import com.google.inject.Inject
import java.io.File
import java.nio.ByteBuffer
import java.nio.channels.Channels
import java.util.HashMap
import java.util.HashSet
import java.util.List
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.xdoc.generator.config.Config
import org.eclipse.xtext.xdoc.generator.util.Utils
import org.eclipse.xtext.xdoc.generator.util.XFloat
import org.eclipse.xtext.xdoc.xdoc.AbstractSection
import org.eclipse.xtext.xdoc.xdoc.Anchor
import org.eclipse.xtext.xdoc.xdoc.Chapter
import org.eclipse.xtext.xdoc.xdoc.ChapterRef
import org.eclipse.xtext.xdoc.xdoc.Code
import org.eclipse.xtext.xdoc.xdoc.CodeBlock
import org.eclipse.xtext.xdoc.xdoc.CodeRef
import org.eclipse.xtext.xdoc.xdoc.Document
import org.eclipse.xtext.xdoc.xdoc.Emphasize
import org.eclipse.xtext.xdoc.xdoc.ImageRef
import org.eclipse.xtext.xdoc.xdoc.Item
import org.eclipse.xtext.xdoc.xdoc.LangDef
import org.eclipse.xtext.xdoc.xdoc.Link
import org.eclipse.xtext.xdoc.xdoc.MarkupInCode
import org.eclipse.xtext.xdoc.xdoc.OrderedList
import org.eclipse.xtext.xdoc.xdoc.Part
import org.eclipse.xtext.xdoc.xdoc.PartRef
import org.eclipse.xtext.xdoc.xdoc.Ref
import org.eclipse.xtext.xdoc.xdoc.Section
import org.eclipse.xtext.xdoc.xdoc.Section2
import org.eclipse.xtext.xdoc.xdoc.Section2Ref
import org.eclipse.xtext.xdoc.xdoc.Section3
import org.eclipse.xtext.xdoc.xdoc.Section4
import org.eclipse.xtext.xdoc.xdoc.SectionRef
import org.eclipse.xtext.xdoc.xdoc.Table
import org.eclipse.xtext.xdoc.xdoc.TableData
import org.eclipse.xtext.xdoc.xdoc.TableRow
import org.eclipse.xtext.xdoc.xdoc.TextOrMarkup
import org.eclipse.xtext.xdoc.xdoc.TextPart
import org.eclipse.xtext.xdoc.xdoc.Todo
import org.eclipse.xtext.xdoc.xdoc.UnorderedList

import static extension org.eclipse.xtext.xdoc.generator.util.LatexUtils.*
import static extension org.eclipse.xtext.xdoc.generator.util.StringUtils.*
import static extension org.eclipse.xtext.xbase.lib.IterableExtensions.*
import static extension org.eclipse.xtext.xdoc.generator.util.numeric.XFloatExtensions.*
import javax.imageio.ImageIO
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.xdoc.xdoc.XdocPackage
import java.util.Locale
import org.eclipse.xtext.xdoc.xdoc.TableFormat

class LatexGenerator implements IConfigurableGenerator {

	@Inject extension Utils utils
	@Inject HashMap<String, Object> config
	@Inject HashSet<String> links


	override getConfiguration() {
		config
	}

	override doGenerate(Resource resource, IFileSystemAccess fsa) {
		for(element: resource.allContents.toIterable) {
			if(element instanceof Document) {
				val doc = element as Document				
				fsa.generateFile(
					doc.name.fileName, 
					doc.generate)
			}
		}
	}

	def doGenerate(EObject obj, IFileSystemAccess fsa) {
		val doc = obj as Document; 
		fsa.generateFile(doc.name.fileName, doc.generate)
	}

	def fileName(String name) {
		// System::getProperty("user.dir") + File::separatorChar + 
		//	"src-gen"+ File::separatorChar + 
		name +".tex"
	}
	
	def dispatch CharSequence generate(Document doc) '''
		«doc.preamble»
		«FOR lang: doc.langDefs»
			«lang.generate»
		«ENDFOR»
		«configureTodo»
		
		\usepackage{hyperref}
		
		«doc.authorAndTitle»
		
		\begin{document}
		\maketitle
		\tableofcontents
		«FOR chapter: doc.chapters»
			
			«chapter.generate»
		«ENDFOR»
		«FOR part: doc.parts»
			
			«part.generate»
		«ENDFOR»
		«genListOfLinks»
		
		«IF !(config.get(Config::release) as Boolean)»
			\listoftodos
		«ENDIF»
		\end{document}
	'''

	def genListOfLinks() {
		if(!links.empty)
			'''
				\chapter{List of External Links}
				«FOR link: links»
					\noindent\url{«link»}
				«ENDFOR»
			'''
	}

	def preamble(Document doc) '''
		\documentclass[a4paper,10pt]{scrreprt}
		
		\typearea{12}
		
		\usepackage[T1]{fontenc}
		\usepackage{ae,aecompl,aeguill} 
		\usepackage[latin1]{inputenc}
		\usepackage{listings}
		\usepackage{booktabs}
		\usepackage[american]{babel}
		
		\usepackage{todonotes}
		
		«IF doc.titlepic != null»
			\usepackage{titlepic}
		«ENDIF»
		
		\makeatletter
		\renewcommand\subsection{\medskip\@startsection{subsection}{2}{\z@}%
		  {-.25ex\@plus -.1ex \@minus -.2ex}%
		  {1.5ex \@plus .2ex \@minus -.4ex}%
		  {\ifnum \scr@compatibility>\@nameuse{scr@v@2.96}\relax
		    \setlength{\parfillskip}{\z@ plus 1fil}\fi
		    \raggedsection\normalfont\sectfont\nobreak\size@subsection
		  }%
		}
		\renewcommand\paragraph{\@startsection{paragraph}{4}{\z@}%
		  {-3.25ex\@plus -1ex \@minus -.2ex}%
		  {1.5ex \@plus .2ex}%
		  {\sffamily\normalsize\bfseries}}
		\makeatother
		
		\definecolor{listingsbg}{HTML}{E7E7E7}
		\definecolor{kwcolor}{HTML}{7F0055}
		\definecolor{strcolor}{HTML}{2A00FF}
		\definecolor{cocolor}{HTML}{3F7F5F}

		\lstset{tabsize=4, basicstyle=\sffamily\small, keywordstyle=\bfseries\color{kwcolor}, commentstyle=\color{cocolor},
		stringstyle=\color{strcolor}, columns=[r]fullflexible, escapechar={ß}, frame=single, %framexleftmargin=-5pt, framexrightmargin=-5pt, 
		xleftmargin=5pt, xrightmargin=5pt, rulecolor=\color{lightgray}, showstringspaces=false, backgroundcolor=\color{listingsbg!70}}
		
		\newlength{\XdocItemIndent}
		\newlength{\XdocTEffectiveWidth}
		
	'''

	// TODO: migrate to generate
	def configureTodo() {
		'''
		«IF (config.get(Config::release) as Boolean)»
			\renewcommand{\todo}[1]{}
		«ENDIF»
		'''
	}

	def dispatch CharSequence generate(LangDef lang) '''
		\lstdefinelanguage{«lang.name»}
		  {morekeywords={«lang.keywords.join(", ")»},
		    sensitive=true,
		    morecomment=[l]{//},
		    morecomment=[s]{/*}{*/},
		    morestring=[b]",
		    morestring=[b]',
		  }
	'''

	def authorAndTitle(Document doc) {
		'''
		«IF doc.authors != null && !doc.authors.contents.empty»
			\author{«FOR o : doc.authors.contents»«o.genText»«ENDFOR»}
		«ENDIF»

		«IF doc.titlepic != null»
			\titlepic{\includegraphics{«copyTitlepic(doc)»}}
		«ENDIF»
		
		«IF doc.title != null»
			\title{«FOR o : doc.title?.contents»«o.genText»«ENDFOR»}
		«ENDIF»
		'''
	}

	/**
	 * genContent
	 * 
	 * Generates the content for the single structures.
	 * 
	 * FIXME: name for sectionrefs not correctly read
	 */
	def dispatch CharSequence generate(AbstractSection sec){
		'''
		«switch (sec){
			Part :
				'''\part{«sec.title.genNonParContent»}'''
			Chapter :			
				'''\chapter{«sec.title.genNonParContent»}'''
			Section : 
				'''\section{«sec.title.genNonParContent»}'''
			Section2:
				'''\subsection{«sec.title.genNonParContent»}'''
			Section3:
				'''\subsubsection{«sec.title.genNonParContent»}'''	
			Section4:
				'''\paragraph{«sec.title.genNonParContent»}'''	
		}»
		«switch (sec) {
			Part:
				sec.genLabel
			Chapter:
				sec.genLabel
			Section:
				sec.genLabel
			default:
				sec.title.genLabel				
		}»
		«sec.genContent»
		'''
	}

	def dispatch CharSequence genContent(Chapter chap){
		'''
		«FOR c : chap.contents»«c.genContent»«ENDFOR»
		«FOR sub : chap.subSections»«sub.generate»«ENDFOR»
		'''
	}

	def dispatch CharSequence genContent(Part part){
		'''
		
		«FOR c : part.contents»
			«c.genContent»
		«ENDFOR»
		«FOR sub : part.chapters»«sub.generate»«ENDFOR»
		'''
	}

	def dispatch CharSequence genContent(Section sec){
		'''
		«FOR c : sec.contents»
			«c.genContent»
		«ENDFOR»
		«FOR sub : sec.subSections»
			«sub.generate»
		«ENDFOR»
		'''
	}

	def dispatch CharSequence genContent(Section2 sec){
		'''
		«sec.genLabel»
		«FOR c : sec.contents»
			«c.genContent»
		«ENDFOR»
		«FOR sub : sec.subSections»
			«sub.generate»
		«ENDFOR»
		'''
	}

	def dispatch CharSequence genContent(Section3 sec){
		'''
		«sec.genLabel»
		«FOR c : sec.contents»
			«c.genContent»
		«ENDFOR»
		«FOR sub : sec.subSections»
			«sub.generate»
		«ENDFOR»
		'''
	}

	def dispatch CharSequence genContent(Section4 sec){
		'''
		«sec.genLabel»
		«FOR c : sec.contents»
			«c.genContent»
		«ENDFOR»
		'''
	}

	def dispatch CharSequence genContent(TextOrMarkup tom){
		'''
			«FOR e : tom.contents»«e.genText»«ENDFOR»
			
		'''
	}

	/**
	 * genNonParContent
	 */
	def genNonParContent(TextOrMarkup tom){
		'''«FOR e : tom.contents»«e.genText»«ENDFOR»'''
	}

	/**
	 * genLabel
	 * Generates a label
	 */
	def dispatch genLabel(Part part) '''
		«IF part.name != null»
			\label{«part.name?.toString»}
		«ENDIF»
	'''
	

	def dispatch genLabel(PartRef part) '''
		«IF part.part.name != null»
			\label{«part.part.name?.toString»}
		«ENDIF»
	'''

	def dispatch genLabel(ChapterRef cRef) '''
		«IF cRef.chapter.name != null»
			\label{«cRef.chapter.name?.toString»}
		«ENDIF»
	'''
	

	def dispatch genLabel(Chapter chap) '''
		«IF chap.name != null»
			\label{«chap.name?.toString»}
		«ENDIF»
	'''


	def dispatch genLabel(Section sec) '''
		«IF sec.name != null»
			\label{«sec.name?.toString»}
		«ENDIF»
	'''

	def dispatch genLabel(SectionRef sRef) '''
		«IF sRef.section.name != null»
			\label{«sRef.section.name?.toString»}
		«ENDIF»
	'''

	def dispatch genLabel(Section2 sec) '''
		«IF sec.name != null»
			\label{«sec.name?.toString»}
		«ENDIF»
	'''

	def dispatch genLabel(Section2Ref sRef) '''
		«IF sRef.section2.name != null»
			\label{«sRef.section2.name?.toString»}
		«ENDIF»
	'''

	def dispatch genLabel(Section3 sec) '''
		«IF sec.name != null»
			\label{«sec.name?.toString»}
		«ENDIF»
	'''

	def dispatch genLabel(Section4 sec) '''
		«IF sec.name != null»
			\label{«sec.name?.toString»}
		«ENDIF»
	'''

	def dispatch genLabel(TextOrMarkup tom){
		''''''
	}

	 def genURL(String str){
	 	'''\noindent\url{«str»}'''
	 }

	def dispatch CharSequence genText(Table tab) '''
		
		«IF tab.name != null»
			\begin{table}
		«ENDIF»
		\setlength{\XdocTEffectiveWidth}{\textwidth}
		\addtolength{\XdocTEffectiveWidth}{-«tab.rows.head.data.size*2».0\tabcolsep}
		\noindent\begin{tabular}{«tab.genColumns»}
		«IF tab.format?.professional»
			\toprule
		«ENDIF»
		«tab.rows.map([e | e.genText]).join»
		«IF tab.format?.professional»
			\bottomrule
		«ENDIF»
		\end{tabular}
		«IF tab.name != null»
			«IF tab.caption != null && ! tab.caption.matches("^\\s*$")»
				\caption{«tab.caption»}
			«ELSE»
				\caption{}
			«ENDIF»
			\label{«tab.name?.toString»}
			\end{table}
		«ENDIF»
	'''

	def dispatch CharSequence genText(TableRow row){
		val txt = row.data.map([e|e.genText]).join(" & ") + "\\\\\n"
		if (row.sep)
			txt + "\\midrule\n"
		else
			txt
	}

	def dispatch CharSequence genText(TableData tData){
		tData.contents.map([e|e.genContent]).join
	}

	def dispatch CharSequence genText(TextPart part){
		part.text.unescapeXdocChars.escapeLatexChars
	}

	def dispatch CharSequence genText(OrderedList ol) '''
		\setlength{\XdocItemIndent}{\textwidth}
		\begin{enumerate}
		\addtolength{\XdocItemIndent}{-2.5em}
		«ol.items.map([e | e.genText]).join»
		\end{enumerate}
		\addtolength{\XdocItemIndent}{2.5em}
	'''

	def dispatch CharSequence genText(UnorderedList ul){
		'''
		\setlength{\XdocItemIndent}{\textwidth}
		\begin{itemize}
		\addtolength{\XdocItemIndent}{-2.5em}
		«ul.items.map([e | e.genText]).join»
		\end{itemize}
		\addtolength{\XdocItemIndent}{2.5em}
		'''
	}

	def dispatch CharSequence genText(Item item){
		'''
		\item \begin{minipage}[t]{\XdocItemIndent}«IF item.contents.head.block»\vspace*{-\baselineskip}«ENDIF»
		«item.contents.map([e|e.genContent]).join»
		\end{minipage}
		'''	
	}

	def dispatch CharSequence genText(Emphasize em){
		'''\textit{«em.contents.map([e|e.genNonParContent]).join»}'''
	}

	def dispatch CharSequence genText(Ref ref){
		'''«IF ref.contents.isEmpty»\autoref{«ref.ref.name»}«ELSE»\hyperref[«ref.ref.name»]{«ref.contents.map([e|e.genNonParContent]).join»~(§\ref*{«ref.ref.name»})}«ENDIF»'''
	}

	def dispatch CharSequence genText(Anchor anchor) {
		'''\phantomsection\label{«anchor.name»}'''
	}

	def dispatch CharSequence genText(Link link){
		links.add(link.url)
		val label = link.text.replace("_", "\\_")
		'''\href{«link.url»}{«label»}'''
	}

	def dispatch CharSequence genText(CodeBlock block) {
		block.specialGenCode
	}

	def dispatch CharSequence genText(CodeRef codeRef){
		'''\protect\lstinline°«codeRef.element.qualifiedName.unescapeXdocChars.escapeLatexChars»°'''
	}

	def dispatch CharSequence genText(ImageRef imgRef){
		'''
		\begin{figure}[!ht]
		\centering
		\includegraphics[width=«imgRef.widthFactor»\textwidth]{«copy(imgRef)»}
		«IF imgRef.caption != null && ! imgRef.caption.matches("^\\s*$")»
			\caption{«imgRef.caption»}
			«IF imgRef.name != null»
				\label{«imgRef.name?.toString»}
			«ENDIF»
		«ENDIF»
		\end{figure}
		'''
	}

	def private getWidthFactor(ImageRef imgRef) {
		if (imgRef.fraction==null)
			""
		else {
			val f = Double::parseDouble(imgRef.fraction)
			if (f>0.0 && f<=1.0)
				String::format(Locale::ENGLISH, "%.2f", f)
			else
				""
		}		
	}

	def String copyTitlepic(Document doc) {
		copy(doc.eResource, doc.titlepic)
	}
	
	def String copy(ImageRef imgRef) {
		copy(imgRef.eResource, imgRef.path)
	}
	
	def String copy(Resource res, String imgPath) {
		try{
			val buffer = ByteBuffer::allocateDirect(16 * 1024);
			val uri = res.URI
			val uriConverter = res.resourceSet.URIConverter
			val absoluteLocalPath = URI::createFileURI(new File("").absolutePath+"/")
			val relativeImageURI = URI::createFileURI(imgPath)
			val inPath = relativeImageURI.resolve(uri).deresolve(absoluteLocalPath)
			val inSegments = inPath.segmentsList.subList(1, inPath.segmentCount)
			val pathInDocument = inSegments.join("/")
			val outPath = URI::createFileURI(config.get(Config::outletPath).toString + "/" + pathInDocument)
			val inChannel = Channels::newChannel(uriConverter.createInputStream(inPath))
			val outChannel = Channels::newChannel(res.resourceSet.URIConverter.createOutputStream(outPath))
			while (inChannel.read(buffer) != -1) {
				buffer.flip();
				outChannel.write(buffer);
				buffer.compact();
			}
			buffer.flip();
			while (buffer.hasRemaining()) {
				outChannel.write(buffer);
			}
			outChannel.close()
			return pathInDocument
		} catch (Exception e) {
			throw new RuntimeException(e)
		}
	}

	def dispatch CharSequence genText(Todo todo){
		if(!config.get(Config::release) as Boolean)
			'''\todo[inline]{«todo.text.unescapeXdocChars.escapeLatexChars»}'''
		else
			''''''
	}

	def dispatch CharSequence genText(MarkupInCode mic){
		''''''
	}

	def dispatch CharSequence genText(String str){
		str
	}

	def dispatch CharSequence genText(EObject o){
		//throw new UnsupportedOperationException("genText")
		''''''
	}

	/*
	 * TODO: block.language != null  should be  language != null  
	 */
	 def specialGenCode(CodeBlock block) {
		if(block.inline)
			if(block.containerTypeOf(XdocPackage.Literals::TABLE)) {
	 			'''\protect\lstinline«block.language?.langSpec»°«block.contents.map[genCode].join»°'''
	 		} else {
	 			'''\protect\lstinline«block.language?.langSpec»{«block.contents.map[genCode].join»}'''
	 		}
	 	else
			'''
				
				\begin{lstlisting}«block.language?.langSpec»
				«block.removeIndent.contents.map([e|e.genCode]).join»
				\end{lstlisting}
			'''
	}

	def boolean containerTypeOf(EObject obj, EClass c) {
		if(obj.eClass == c) {
			true
		} else if(obj.eContainer.eClass == XdocPackage$Literals::XDOC_FILE) {
			false
		} else {
			obj.eContainer.containerTypeOf(c)
		}
	}

	// see Bug 345934
	def langSpec(LangDef lang) {
		'''«IF lang != null»[language=«lang.name»]«ENDIF»'''
	}

	def dispatch genCode(Code code){
		code.contents.unescapeXdocChars.prepareListingsString
	}

	def dispatch genCode(MarkupInCode mic){
		'''«lstEscapeToTex()»«mic.genText»«lstEscapeToTex()»'''
	}

	def dispatch genCode(Object o){
		''''''	  	
	}

	def genColumns(Table tab){
		if (tab.format!=null) {
			val verticalSeps = ! tab.format.professional
			if (tab.format.columns.empty)
				tab.rows.head.data.genColumns(verticalSeps)
			else
				tab.format.genColumns(verticalSeps)
			
		} else 
			tab.rows.head.data.genColumns(true)
	}

	// compute specific column widths based on tf[] settings 
	def genColumns(TableFormat tabFormat, boolean verticalSeps){
		val relWidths = tabFormat.columns.map[Integer::parseInt(relativeWidth)]
		val n = relWidths.reduce[a,b|a+b]
		val widths = relWidths.map[String::format(Locale::ENGLISH, "%.2f", (it as float) / n)]
		val vs = if (verticalSeps) "|" else ""
		'''«IF !widths.empty»«vs»«FOR w : widths»p{«w»\XdocTEffectiveWidth}«vs»«ENDFOR»«ENDIF»'''
	}

	// compute equally spaced columns from example table row
	def genColumns(List<TableData> tabData, boolean verticalSeps){
		val colFract = new XFloat(1)/new XFloat(tabData.size)
		val vs = if (verticalSeps) "|" else ""
		'''«IF !tabData.empty»«vs»«FOR td: tabData»p{«colFract»\XdocTEffectiveWidth}«vs»«ENDFOR»«ENDIF»'''
	}

	def String calcStyle(ImageRef ref) {
		val uri = ref.eResource.getURI
		val inPath = URI::createURI(uri.trimSegments(1).toString + "/" + ref.path)
		val img = ImageIO::createImageInputStream(new File(inPath.toFileString()));
		val imageReaders = ImageIO::getImageReaders(img);
		val ir = imageReaders.next()
		ir.input = img

		val imageMetadata = ir.getImageMetadata(0)
		val n = imageMetadata.getAsTree("javax_imageio_1.0")
		var ppmm = new XFloat(Float::parseFloat("2.835"))
		var cn = n.getFirstChild
		while(cn != null) {
			if(cn.nodeName == "Dimension") {
				var ccn = cn.getFirstChild
				while(ccn != null){
					if(ccn.getNodeName().equals("HorizontalPixelSize")){
						ppmm = 1/ new XFloat(Float::parseFloat(ccn.getAttributes().item(0).getNodeValue()))
					}
					ccn = ccn.getNextSibling
				}
			}
			cn = cn.getNextSibling()
		}
		if(ir.getWidth(0) / ppmm > 140){
			"width=\\textwidth";
		} else
			"";
	}
}
