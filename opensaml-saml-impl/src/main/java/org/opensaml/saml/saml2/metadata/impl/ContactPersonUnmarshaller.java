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

package org.opensaml.saml.saml2.metadata.impl;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.saml.common.AbstractSAMLObjectUnmarshaller;
import org.opensaml.saml.saml2.metadata.Extensions;
import org.opensaml.saml.saml2.metadata.Company;
import org.opensaml.saml.saml2.metadata.ContactPerson;
import org.opensaml.saml.saml2.metadata.ContactPersonTypeEnumeration;
import org.opensaml.saml.saml2.metadata.EmailAddress;
import org.opensaml.saml.saml2.metadata.GivenName;
import org.opensaml.saml.saml2.metadata.SurName;
import org.opensaml.saml.saml2.metadata.TelephoneNumber;
import org.w3c.dom.Attr;

/**
 * A thread-safe Unmarshaller for {@link org.opensaml.saml.saml2.metadata.ContactPerson} objects.
 */
public class ContactPersonUnmarshaller extends AbstractSAMLObjectUnmarshaller {

    /** {@inheritDoc} */
    protected void processChildElement(final XMLObject parentSAMLObject, final XMLObject childSAMLObject)
            throws UnmarshallingException {
        final ContactPerson person = (ContactPerson) parentSAMLObject;

        if (childSAMLObject instanceof Extensions) {
            person.setExtensions((Extensions) childSAMLObject);
        } else if (childSAMLObject instanceof Company) {
            person.setCompany((Company) childSAMLObject);
        } else if (childSAMLObject instanceof GivenName) {
            person.setGivenName((GivenName) childSAMLObject);
        } else if (childSAMLObject instanceof SurName) {
            person.setSurName((SurName) childSAMLObject);
        } else if (childSAMLObject instanceof EmailAddress) {
            person.getEmailAddresses().add((EmailAddress) childSAMLObject);
        } else if (childSAMLObject instanceof TelephoneNumber) {
            person.getTelephoneNumbers().add((TelephoneNumber) childSAMLObject);
        } else {
            super.processChildElement(parentSAMLObject, childSAMLObject);
        }
    }

    /** {@inheritDoc} */
    protected void processAttribute(final XMLObject samlObject, final Attr attribute) throws UnmarshallingException {
        final ContactPerson person = (ContactPerson) samlObject;

        if (attribute.getNamespaceURI() == null) {
            if (attribute.getLocalName().equals(ContactPerson.CONTACT_TYPE_ATTRIB_NAME)) {
                if (ContactPersonTypeEnumeration.TECHNICAL.toString().equals(attribute.getValue())) {
                    person.setType(ContactPersonTypeEnumeration.TECHNICAL);
                } else if (ContactPersonTypeEnumeration.SUPPORT.toString().equals(attribute.getValue())) {
                    person.setType(ContactPersonTypeEnumeration.SUPPORT);
                } else if (ContactPersonTypeEnumeration.ADMINISTRATIVE.toString().equals(attribute.getValue())) {
                    person.setType(ContactPersonTypeEnumeration.ADMINISTRATIVE);
                } else if (ContactPersonTypeEnumeration.BILLING.toString().equals(attribute.getValue())) {
                    person.setType(ContactPersonTypeEnumeration.BILLING);
                } else if (ContactPersonTypeEnumeration.OTHER.toString().equals(attribute.getValue())) {
                    person.setType(ContactPersonTypeEnumeration.OTHER);
                } else {
                    super.processAttribute(samlObject, attribute);
                }
            } else {
                super.processAttribute(samlObject, attribute);
            }
        } else {
            processUnknownAttribute(person, attribute);
        }
    }
}