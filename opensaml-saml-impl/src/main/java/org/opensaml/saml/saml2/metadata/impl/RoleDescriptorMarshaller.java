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

package org.opensaml.saml.saml2.metadata.impl;

import java.util.List;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.MarshallingException;
import org.opensaml.saml.common.AbstractSAMLObjectMarshaller;
import org.opensaml.saml.config.SAMLConfigurationSupport;
import org.opensaml.saml.saml2.common.CacheableSAMLObject;
import org.opensaml.saml.saml2.common.TimeBoundSAMLObject;
import org.opensaml.saml.saml2.metadata.RoleDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

/**
 * A thread safe Marshaller for {@link org.opensaml.saml.saml2.metadata.RoleDescriptor} objects.
 */
public abstract class RoleDescriptorMarshaller extends AbstractSAMLObjectMarshaller {

    /** Class logger. */
    private final Logger log = LoggerFactory.getLogger(RoleDescriptorMarshaller.class);

    /** {@inheritDoc} */
    protected void marshallAttributes(final XMLObject samlElement, final Element domElement)
            throws MarshallingException {
        final RoleDescriptor roleDescriptor = (RoleDescriptor) samlElement;

        // Set the ID attribute
        if (roleDescriptor.getID() != null) {
            log.trace("Writing ID attribute to RoleDescriptor DOM element");
            domElement.setAttributeNS(null, RoleDescriptor.ID_ATTRIB_NAME, roleDescriptor.getID());
            domElement.setIdAttributeNS(null, RoleDescriptor.ID_ATTRIB_NAME, true);
        }

        // Set the validUntil attribute
        if (roleDescriptor.getValidUntil() != null) {
            log.trace("Writing validUntil attribute to RoleDescriptor DOM element");
            final String validUntilStr =
                    SAMLConfigurationSupport.getSAMLDateFormatter().format(roleDescriptor.getValidUntil());
            domElement.setAttributeNS(null, TimeBoundSAMLObject.VALID_UNTIL_ATTRIB_NAME, validUntilStr);
        }

        // Set the cacheDuration attribute
        if (roleDescriptor.getCacheDuration() != null) {
            log.trace("Writing cacheDuration attribute to EntitiesDescriptor DOM element");
            domElement.setAttributeNS(null, CacheableSAMLObject.CACHE_DURATION_ATTRIB_NAME,
                    roleDescriptor.getCacheDuration().toString());
        }

        // Set the protocolSupportEnumeration attribute
        final List<String> supportedProtocols = roleDescriptor.getSupportedProtocols();
        if (supportedProtocols != null && supportedProtocols.size() > 0) {
            log.trace("Writing protocolSupportEnumberation attribute to RoleDescriptor DOM element");

            final StringBuilder builder = new StringBuilder();
            for (final String protocol : supportedProtocols) {
                builder.append(protocol);
                builder.append(" ");
            }

            domElement.setAttributeNS(null, RoleDescriptor.PROTOCOL_ENUMERATION_ATTRIB_NAME, builder.toString().trim());
        }

        // Set errorURL attribute
        if (roleDescriptor.getErrorURL() != null) {
            log.trace("Writing errorURL attribute to RoleDescriptor DOM element");
            domElement.setAttributeNS(null, RoleDescriptor.ERROR_URL_ATTRIB_NAME, roleDescriptor.getErrorURL());
        }

        marshallUnknownAttributes(roleDescriptor, domElement);
    }
}