/*
 * Licensed to the University Corporation for Advanced Internet Development,
 * Inc. (UCAID) under one or more contributor license agreements.  See the
 * NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The UCAID licenses this file to You under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.saml.saml1.core.impl;

import org.opensaml.saml.common.AbstractSAMLObjectBuilder;
import org.opensaml.saml.common.xml.SAMLConstants;
import org.opensaml.saml.saml1.core.ConfirmationMethod;

/**
 * Builder of {@link org.opensaml.saml.saml1.core.impl.ConfirmationMethodImpl} objects.
 */
public class ConfirmationMethodBuilder extends AbstractSAMLObjectBuilder<ConfirmationMethod> {

    /**
     * Constructor.
     */
    public ConfirmationMethodBuilder() {

    }

    /** {@inheritDoc} */
    public ConfirmationMethod buildObject() {
        return buildObject(SAMLConstants.SAML1_NS, ConfirmationMethod.DEFAULT_ELEMENT_LOCAL_NAME,
                SAMLConstants.SAML1_PREFIX);
    }

    /** {@inheritDoc} */
    public ConfirmationMethod buildObject(final String namespaceURI, final String localName,
            final String namespacePrefix) {
        return new ConfirmationMethodImpl(namespaceURI, localName, namespacePrefix);
    }
}