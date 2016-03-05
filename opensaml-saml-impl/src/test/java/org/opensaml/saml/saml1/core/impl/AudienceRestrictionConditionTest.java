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

package org.opensaml.saml.saml1.core.impl;

import org.testng.annotations.Test;
import org.testng.Assert;
import javax.xml.namespace.QName;

import org.opensaml.core.xml.XMLObjectProviderBaseTestCase;
import org.opensaml.saml.common.xml.SAMLConstants;
import org.opensaml.saml.saml1.core.Audience;
import org.opensaml.saml.saml1.core.AudienceRestrictionCondition;

/**
 * Test class for data.org.opensaml.saml1.AudienceRestrictionCondition
 */
public class AudienceRestrictionConditionTest extends XMLObjectProviderBaseTestCase {

    /** name used to generate objects */
    private final QName qname;

    /**
     * Constructor
     */
    public AudienceRestrictionConditionTest() {
        singleElementFile = "/org/opensaml/saml/saml1/impl/singleAudienceRestrictionCondition.xml";
        childElementsFile = "/org/opensaml/saml/saml1/impl/AudienceRestrictionConditionWithChildren.xml";
        qname = new QName(SAMLConstants.SAML1_NS, AudienceRestrictionCondition.DEFAULT_ELEMENT_LOCAL_NAME, SAMLConstants.SAML1_PREFIX);
    }

    /** {@inheritDoc} */

    @Test
    public void testSingleElementUnmarshall() {
        AudienceRestrictionCondition audienceRestrictionCondition;

        audienceRestrictionCondition = (AudienceRestrictionCondition) unmarshallElement(singleElementFile);

        Assert.assertEquals(audienceRestrictionCondition.getAudiences().size(), 0, "Count of child Audience elements !=0");
    }

    /** {@inheritDoc} */

    @Test
    public void testChildElementsUnmarshall() {

        AudienceRestrictionCondition audienceRestrictionCondition;

        audienceRestrictionCondition = (AudienceRestrictionCondition) unmarshallElement(childElementsFile);

        Assert.assertEquals(audienceRestrictionCondition.getAudiences().size(), 2, "Count of child Audience elements");

    }

    /** {@inheritDoc} */

    @Test
    public void testSingleElementMarshall() {
        assertXMLEquals(expectedDOM, buildXMLObject(qname));
    }

    /** {@inheritDoc} */

    @Test
    public void testChildElementsMarshall() {
        AudienceRestrictionCondition audienceRestrictionCondition;

        audienceRestrictionCondition = (AudienceRestrictionCondition) buildXMLObject(qname);

        QName audienceName = new QName(SAMLConstants.SAML1_NS, Audience.DEFAULT_ELEMENT_LOCAL_NAME, SAMLConstants.SAML1_PREFIX);
        audienceRestrictionCondition.getAudiences().add((Audience) buildXMLObject(audienceName));
        audienceRestrictionCondition.getAudiences().add((Audience) buildXMLObject(audienceName));

        assertXMLEquals(expectedChildElementsDOM, audienceRestrictionCondition);
    }

}
