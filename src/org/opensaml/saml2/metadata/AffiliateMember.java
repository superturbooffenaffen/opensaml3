/*
 * Copyright [2005] [University Corporation for Advanced Internet Development, Inc.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.saml2.metadata;

import javax.xml.namespace.QName;

import org.opensaml.common.SAMLObject;
import org.opensaml.common.util.xml.XMLConstants;

/**
 * SAML 2.0 Metadata AffiliateMember
 */
public interface AffiliateMember extends SAMLObject {
    /** Element name, no namespace */
    public final static String LOCAL_NAME = "AffiliateMember";
    
    /** QName for this element */
    public final static QName QNAME = new QName(XMLConstants.SAML20MD_NS, LOCAL_NAME, XMLConstants.SAML20MD_PREFIX);
    
    /**
     * Gets the member's entity ID.
     * 
     * @return the member's ID
     */
    public String getID();
    
    /**
     * Sets the member's entity ID.
     * 
     * @param memberID the member's ID
     * 
     * @throws IllegalArgumentException thrown if the ID is over 1024 characters long
     */
    public void setID(String memberID) throws IllegalArgumentException;
}
