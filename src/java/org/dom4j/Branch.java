/*
 * Copyright 2001 (C) MetaStuff, Ltd. All Rights Reserved.
 * 
 * This software is open source. 
 * See the bottom of this file for the licence.
 * 
 * $Id$
 */

package org.dom4j;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;


/** <p><code>Branch</code> interface defines the common behaviour 
  * for Nodes which can contain child nodes (content) such as 
  * XML elements and documents. 
  * This interface allows both elements and documents to be treated in a 
  * polymorphic manner when changing or navigating child nodes (content).</p>
  *
  * @author <a href="mailto:james.strachan@metastuff.com">James Strachan</a>
  * @version $Revision$
  */
public interface Branch extends Node {

    /** Returns the <code>Node</code> at the specified index position.
      *
      * @param index the index of the node to return.
      * @return the <code>Node</code> at the specified position.
      * 
      * @throws IndexOutOfBoundsException if the index is out of range (index
      * 		  &lt; 0 || index &gt;= {@link #getNodeCount}).
      */    
    public Node getNode(int index);
    
    /** Returns the index of the given node if it is a child node of this 
      * branch or -1 if the given node is not a child node.
      *
      * @param node the content child node to find.
      * @return the index of the given node starting at 0 or -1 if the node 
      *     is not a child node of this branch
      */    
    public int indexOf(Node node);
    
    /** Returns the number of <code>Node</code> instances that this branch 
      * contains.
      *
      * @return the number of nodes this branch contains
      */    
    public int getNodeCount();
    
    /** <p>Returns the content nodes of this branch as a backed {@link List}
      * so that the content of this branch may be modified directly using
      * the {@link List} interface.
      * The <code>List</code> is backed by the <code>Branch</code> so that
      * changes to the list are reflected in the branch and vice versa.</p>
      *
      * @return the nodes that this branch contains as a <code>List</code>
      */    
    public List content();    

    /** Returns an iterator through the content nodes of this branch
      * 
      * @return an iterator through the content nodes of this branch
      */
    public Iterator nodeIterator();
    
    /** Sets the contents of this branch as a <code>List</code> of 
      * <code>Node</code> instances.
      *
      * @param content is the list of nodes to use as the content for this 
      *   branch.
      */    
    public void setContent(List content);    
    
    /** Clears the content for this branch, removing any <code>Node</code> 
      * instances this branch may contain.
      */    
    public void clearContent();
    
    /** <p>Returns a list of all the processing instructions in this branch.
      * The list is backed by this branch so that changes to the list will
      * be reflected in the branch but the reverse is not the case.</p>
      *
      * @return a backed list of the processing instructions
      */
    public List processingInstructions();
    
    /** <p>Returns a list of the processing instructions for the given target.
      * The list is backed by this branch so that changes to the list will
      * be reflected in the branch but the reverse is not the case.</p>
      *
      * @return a backed list of the processing instructions
      */
    public List processingInstructions(String target);

    /** @return the processing instruction for the given target
      */
    public ProcessingInstruction processingInstruction(String target);    
    
    /** Sets all the processing instructions for this branch
      */
    public void setProcessingInstructions(List listOfPIs);
    
    
    /** Adds a new <code>Comment</code> node with the given text to this branch.
      *
      * @param comment is the text for the <code>Comment</code> node.
      */    
    public void addComment(String comment);
    
    /** Adds a new <code>Element</code> node with the given name to this branch
      * and returns a reference to the new node.
      *
      * @param name is the name for the <code>Element</code> node.
      * @return the newly added <code>Element</code> node.
      */    
    public Element addElement(String name);
    
    /** Adds a new <code>Element</code> node with the given {@link QName} 
      * to this branch and returns a reference to the new node.
      *
      * @param qname is the qualified name for the <code>Element</code> node.
      * @return the newly added <code>Element</code> node.
      */    
    public Element addElement(QName qname);
    
    /** Adds a new <code>Element</code> node with the given {@link QName} 
      * to this branch and returns a reference to the new node. 
      * This method is used by {@link org.dom4j.io.SAXReader} to allow
      * more optimal or more flexible construction of element instances based 
      * on the available attributes.
      *
      * @param qname is the qualified name for the <code>Element</code> node.
      * @return the newly added <code>Element</code> node.
      */    
    public Element addElement(QName qName, Attributes attributes);
    
    /** Adds a processing instruction for the given target
      *
      * @param target is the target of the processing instruction
      * @text is the textual data (key/value pairs) of the processing instruction
      */
    public ProcessingInstruction addProcessingInstruction(String target, String text);
    
    /** Adds a processing instruction for the given target
      *
      * @param target is the target of the processing instruction
      * @data is a Map of the key / value pairs of the processing instruction
      */
    public ProcessingInstruction addProcessingInstruction(String target, Map data);

    /** Removes the processing instruction for the given target if it exists
      *
      * @return true if a processing instruction was removed else false
      */
    public boolean removeProcessingInstruction(String target);

    
    
    /** Adds the given <code>Node</code> or throws {@link IllegalAddException} 
      * if the given node is not of a valid type. This is a polymorphic method 
      * which will call the typesafe method for the node type such as 
      * add(Element) or add(Comment).
      *
      * @param node is the given node to add
      */    
    public void add(Node node);
    
    /** Adds the given <code>Comment</code> to this branch.
      * If the given node already has a parent defined then an
      * <code>InvalidAddNodeException</code> will be thrown.
      *
      * @param comment is the comment to be added
      */
    public void add(Comment comment);
    
    /** Adds the given <code>Element</code> to this branch.
      * If the given node already has a parent defined then an
      * <code>InvalidAddNodeException</code> will be thrown.
      *
      * @param element is the element to be added
      */
    public void add(Element element);
    
    /** Adds the given <code>ProcessingInstruction</code> to this branch.
      * If the given node already has a parent defined then an
      * <code>InvalidAddNodeException</code> will be thrown.
      *
      * @param pi is the processing instruction to be added
      */
    public void add(ProcessingInstruction pi);
        
    /** Removes the given <code>Node</code> if the node is in this branch.
      * This is a polymorphic method which will call the typesafe method 
      * for the node type such as remove(Element) or remove(Comment).
      *
      * @param node is the given node to be removed
      * @return true if the node was removed
      */    
    public boolean remove(Node node);
    
    /** Removes the given <code>Comment</code> from this branch.
      *
      * @param comment is the comment to be removed
      * @return true if the comment was removed
      */
    public boolean remove(Comment comment);
    
    /** Removes the given <code>Element</code> from this branch.
      *
      * @param element is the element to be removed
      * @return true if the element was removed
      */
    public boolean remove(Element element);
    
    /** Removes the given <code>ProcessingInstruction</code> from this branch.
      *
      * @param pi is the processing instruction to be removed
      * @return true if the processing instruction was removed
      */
    public boolean remove(ProcessingInstruction pi);
}




/*
 * Redistribution and use of this software and associated documentation
 * ("Software"), with or without modification, are permitted provided
 * that the following conditions are met:
 *
 * 1. Redistributions of source code must retain copyright
 *    statements and notices.  Redistributions must also contain a
 *    copy of this document.
 *
 * 2. Redistributions in binary form must reproduce the
 *    above copyright notice, this list of conditions and the
 *    following disclaimer in the documentation and/or other
 *    materials provided with the distribution.
 *
 * 3. The name "DOM4J" must not be used to endorse or promote
 *    products derived from this Software without prior written
 *    permission of MetaStuff, Ltd.  For written permission,
 *    please contact dom4j-info@metastuff.com.
 *
 * 4. Products derived from this Software may not be called "DOM4J"
 *    nor may "DOM4J" appear in their names without prior written
 *    permission of MetaStuff, Ltd. DOM4J is a registered
 *    trademark of MetaStuff, Ltd.
 *
 * 5. Due credit should be given to the DOM4J Project
 *    (http://dom4j.org/).
 *
 * THIS SOFTWARE IS PROVIDED BY METASTUFF, LTD. AND CONTRIBUTORS
 * ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 * METASTUFF, LTD. OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Copyright 2001 (C) MetaStuff, Ltd. All Rights Reserved.
 *
 * $Id$
 */
