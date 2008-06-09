/*
 * Copyright 2008 Members of the EGEE Collaboration.
 * Copyright 2008 University Corporation for Advanced Internet Development, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.opensaml.ws.wssecurity.impl;


import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.schema.XSInteger;
import org.opensaml.xml.util.XMLHelper;
import org.w3c.dom.Element;

/**
 * IterationMarshaller
 * 
 * @author Valery Tschopp &lt;tschopp@switch.ch&gt;
 * @version $Revision$
 */
public class IterationMarshaller extends AbstractWSSecurityObjectMarshaller {

    /**
     * Default constructor
     */
    public IterationMarshaller() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.opensaml.xml.io.AbstractXMLObjectMarshaller#marshallElementContent(org.opensaml.xml.XMLObject,
     *      org.w3c.dom.Element)
     */
    @Override
    protected void marshallElementContent(XMLObject xmlObject,
            Element domElement) throws MarshallingException {
        XSInteger iteration= (XSInteger) xmlObject;
        Integer value= iteration.getValue();
        XMLHelper.appendTextContent(domElement, value.toString());
    }

}
