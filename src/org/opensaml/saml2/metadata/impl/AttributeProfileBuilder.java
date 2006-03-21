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

/**
 * 
 */

package org.opensaml.saml2.metadata.impl;

import org.opensaml.saml2.metadata.AttributeProfile;
import org.opensaml.xml.XMLObjectBuilder;
import org.w3c.dom.Element;

/**
 * Builder for {@link org.opensaml.saml2.metadata.impl.AttributeProfileImpl}s
 */
public class AttributeProfileBuilder implements XMLObjectBuilder {

    /**
     * Constructor
     */
    public AttributeProfileBuilder() {

    }

    /*
     * @see org.opensaml.xml.XMLObjectBuilder#buildObject()
     */
    public AttributeProfile buildObject() {
        return new AttributeProfileImpl();
    }

    /*
     * @see org.opensaml.xml.XMLObjectBuilder#buildObject(org.w3c.dom.Element)
     */
    public AttributeProfile buildObject(Element element) {
        return new AttributeProfileImpl();
    }
}