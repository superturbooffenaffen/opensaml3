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

import java.net.URI;
import java.util.Collection;

import javax.xml.namespace.QName;

import org.opensaml.common.IllegalAddException;
import org.opensaml.common.SAMLObject;
import org.opensaml.common.SignableObject;
import org.opensaml.common.ValidatingObject;
import org.opensaml.common.util.UnmodifiableOrderedSet;
import org.opensaml.common.util.xml.XMLConstants;
import org.opensaml.saml2.common.CacheableSAMLObject;
import org.opensaml.saml2.common.ExtensionsExtensibleSAMLObject;
import org.opensaml.saml2.common.TimeBoundSAMLObject;

/**
 * SAML 2.0 Metadata AffiliationDescriptorType
 */
public interface AffiliationDescriptor extends SAMLObject, SignableObject, TimeBoundSAMLObject, CacheableSAMLObject, ExtensionsExtensibleSAMLObject, ValidatingObject, KeyDescriptorDescriptorComp{
	
	/** Element name, no namespace */
	public final static String LOCAL_NAME = "AffiliationDescriptor";
	
	/** QName for this element */
	public final static QName QNAME = new QName(XMLConstants.SAML20MD_NS, LOCAL_NAME, XMLConstants.SAML20MD_PREFIX);
	
	/** "affiliationOwnerID" attribute's local name */
	public final static String OWNER_ID_ATTRIB_NAME = "affiliationOwnerID";
	
	/** "affiliationOwnerID" attribute's QName */
	public final static QName OWNER_ID_ATTRIB_QNAME = new QName(XMLConstants.SAML20MD_NS, OWNER_ID_ATTRIB_NAME, XMLConstants.SAML20MD_PREFIX);

	/**
	 * Gets the ID of the owner of this affiliation.  The owner may, or may not, be a memeber of the affiliation.
	 * 
	 * @return the ID of the owner of this affiliation
	 */
	public String getOwnerID();

	/**
	 * Sets the ID of the owner of this affiliation.
	 * 
	 * @param ownerID the ID of the owner of this affiliation
	 */
	public void setOwnerID(String ownerID);

	/**
	 * Checks to see if a given entity is a member of this affiliation.
	 * 
	 * @param id the entity's id
	 * 
	 * @return true if the entity is a member, false if not
	 */
	public boolean isMember(String id);
	
    /**
     * Gets an immutable list of the members of this affiliation.  Member IDs are URIs of no more than 1024 characters.
     * 
     * @return a list of URIs
     */
	public UnmodifiableOrderedSet<AffiliateMember> getMembers();
	
    /**
     * Adds a member to this affiliation.
     * 
     * @param member the member to add
     * 
     * @throws IllegalAddException thrown if the given member is already a child of another SAMLObject
     */
    public void addMember(AffiliateMember member) throws IllegalAddException;
    
	/**
	 * Convience method for adding a member to this affiliation.  This is the same as creating a new 
     * {@link AffiliateMember} object, calling {@link AffiliateMember#setID(String)}, and then adding 
     * it to this descriptor with {@link #addMember(AffiliateMember)}.
	 * 
	 * @param memberID member's ID
     * 
     * @throws IllegalArgumentException thrown if the given memberID is over 1024 characters long
	 */
	public void addMember(String memberID) throws IllegalArgumentException;
	
	/**
	 * Removes the given member from this affiliation.
	 * 
	 * @param member the member to remove
	 */
    public void removeMember(AffiliateMember member);
    
    /**
     * Convience method for removing a member from this affiliation.  This is the same as calling {@link #removeMember(AffiliateMember)}
     * where the given {@link AffiliateMember} has the given member ID;
     * 
     * @param memberID the ID of the member to remove
     */
	public void removeMember(String memberID);
    
	/**
	 * Removes the given list of member {@link URI}s from this affiliation.
	 * 
	 * @param members the list of members to be removed
	 */
    public void removeMemebers(Collection<AffiliateMember> members);
	
	/**
	 * Removes all the members from this Affiliation.
	 */
	public void removeAllMembers();
}