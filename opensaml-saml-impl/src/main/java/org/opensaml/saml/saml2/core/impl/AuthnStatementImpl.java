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

/**
 * 
 */

package org.opensaml.saml.saml2.core.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.saml.common.AbstractSAMLObject;
import org.opensaml.saml.saml2.core.AuthnContext;
import org.opensaml.saml.saml2.core.AuthnStatement;
import org.opensaml.saml.saml2.core.SubjectLocality;

/**
 * A concrete implementation of {@link org.opensaml.saml.saml2.core.AuthnStatement}.
 */
public class AuthnStatementImpl extends AbstractSAMLObject implements AuthnStatement {

    /** Subject Locality of the Authentication Statement. */
    private SubjectLocality subjectLocality;

    /** Authentication Context of the Authentication Statement. */
    private AuthnContext authnContext;

    /** Time of the authentication. */
    private Instant authnInstant;

    /** Index of the session. */
    private String sessionIndex;

    /** Time at which the session ends. */
    private Instant sessionNotOnOrAfter;

    /**
     * Constructor.
     * 
     * @param namespaceURI the namespace the element is in
     * @param elementLocalName the local name of the XML element this Object represents
     * @param namespacePrefix the prefix for the given namespace
     */
    protected AuthnStatementImpl(final String namespaceURI, final String elementLocalName,
            final String namespacePrefix) {
        super(namespaceURI, elementLocalName, namespacePrefix);
    }

    /** {@inheritDoc} */
    public SubjectLocality getSubjectLocality() {
        return subjectLocality;
    }

    /** {@inheritDoc} */
    public void setSubjectLocality(final SubjectLocality newSubjectLocality) {
        this.subjectLocality = prepareForAssignment(this.subjectLocality, newSubjectLocality);
    }

    /** {@inheritDoc} */
    public AuthnContext getAuthnContext() {
        return authnContext;
    }

    /** {@inheritDoc} */
    public void setAuthnContext(final AuthnContext newAuthnContext) {
        this.authnContext = prepareForAssignment(this.authnContext, newAuthnContext);
    }

    /** {@inheritDoc} */
    public Instant getAuthnInstant() {
        return authnInstant;
    }

    /** {@inheritDoc} */
    public void setAuthnInstant(final Instant newAuthnInstant) {
        this.authnInstant = prepareForAssignment(this.authnInstant, newAuthnInstant);
    }

    /** {@inheritDoc} */
    public String getSessionIndex() {
        return sessionIndex;
    }

    /** {@inheritDoc} */
    public void setSessionIndex(final String newSessionIndex) {
        this.sessionIndex = prepareForAssignment(this.sessionIndex, newSessionIndex);
    }

    /** {@inheritDoc} */
    public Instant getSessionNotOnOrAfter() {
        return sessionNotOnOrAfter;
    }

    /** {@inheritDoc} */
    public void setSessionNotOnOrAfter(final Instant newSessionNotOnOrAfter) {
        this.sessionNotOnOrAfter = prepareForAssignment(this.sessionNotOnOrAfter, newSessionNotOnOrAfter);
    }

    /** {@inheritDoc} */
    public List<XMLObject> getOrderedChildren() {
        final ArrayList<XMLObject> children = new ArrayList<>();

        children.add(subjectLocality);
        children.add(authnContext);

        return Collections.unmodifiableList(children);
    }
}