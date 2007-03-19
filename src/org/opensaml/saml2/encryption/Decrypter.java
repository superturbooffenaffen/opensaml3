/*
 * Copyright [2006] [University Corporation for Advanced Internet Development, Inc.]
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

package org.opensaml.saml2.encryption;

import org.apache.log4j.Logger;
import org.opensaml.common.SAMLObject;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.EncryptedAssertion;
import org.opensaml.saml2.core.EncryptedAttribute;
import org.opensaml.saml2.core.EncryptedElementType;
import org.opensaml.saml2.core.EncryptedID;
import org.opensaml.saml2.core.NewEncryptedID;
import org.opensaml.saml2.core.NewID;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.encryption.DecryptionException;
import org.opensaml.xml.security.keyinfo.KeyInfoCredentialResolver;

/**
 * Class which implements SAML2-specific options for {@link org.opensaml.saml2.core.EncryptedElementType} objects.
 */
public class Decrypter {
    
    /** Key resolver. */
    private KeyInfoCredentialResolver kekResolver;
    
    /** Recipient identifier, typically a SAML entity ID URI. */
    private String recipient;

    /** Class logger. */
    private Logger log = Logger.getLogger(Decrypter.class);
    
    /**
     * Constructor.
     *
     * @param newKEKResolver the key encryption key resolver to use
     * @param newRecipient the EncryptedKey recipient attribute to use in key resolution
     */
    public Decrypter(KeyInfoCredentialResolver newKEKResolver, String newRecipient) {
        this.kekResolver = newKEKResolver;
        this.recipient = newRecipient;
    }
    
    /**
     * Decrypt the specified EncryptedAssertion.
     * 
     * @param encryptedAssertion the EncryptedAssertion to decrypt
     * @return an Assertion 
     * @throws DecryptionException thrown when decryption generates an error
     */
    public Assertion decrypt(EncryptedAssertion encryptedAssertion) throws DecryptionException {
        SAMLObject samlObject = decryptData(encryptedAssertion);
        if (! (samlObject instanceof Assertion)) {
            throw new DecryptionException("Decrypted SAMLObject was not an instance of Assertion");
        }
        return (Assertion) samlObject;
    }

    /**
     * Decrypt the specified EncryptedAttribute.
     * 
     * @param encryptedAttribute the EncryptedAttribute to decrypt
     * @return an Attribute
     * @throws DecryptionException thrown when decryption generates an error
     */
    public Attribute decrypt(EncryptedAttribute encryptedAttribute) throws DecryptionException {
        SAMLObject samlObject = decryptData(encryptedAttribute);
        if (! (samlObject instanceof Attribute)) {
            throw new DecryptionException("Decrypted SAMLObject was not an instance of Attribute");
        }
        return (Attribute) samlObject;
    }
    
    /**
     * Decrypt the specified EncryptedID.
     * 
     * Note that an EncryptedID can contain a NameID, an Assertion
     * or a BaseID.  It is up to the caller to determine the type of
     * the resulting SAMLObject.
     * 
     * @param encryptedID the EncryptedID to decrypt
     * @return an XMLObject
     * @throws DecryptionException thrown when decryption generates an error
     */
    public SAMLObject decrypt(EncryptedID encryptedID) throws DecryptionException {
        return decryptData(encryptedID);
    }


    /**
     * Decrypt the specified NewEncryptedID.
     * 
     * @param newEncryptedID the NewEncryptedID to decrypt
     * @return a NewID
     * @throws DecryptionException thrown when decryption generates an error
     */
    public NewID decrypt(NewEncryptedID newEncryptedID) throws DecryptionException {
        SAMLObject samlObject = decryptData(newEncryptedID);
        if (! (samlObject instanceof NewID)) {
            throw new DecryptionException("Decrypted SAMLObject was not an instance of NewID");
        }
        return (NewID) samlObject;
    }
    
    /**
     * Decrypt the specified instance of EncryptedElementType, and return it as an instance 
     * of the specified QName.
     * 
     * 
     * @param encElement the EncryptedElementType to decrypt
     * @return the decrypted SAMLObject
     * @throws DecryptionException thrown when decryption generates an error
     */
    private SAMLObject decryptData(EncryptedElementType encElement) throws DecryptionException {
        
        if (encElement.getEncryptedData() == null) {
            throw new DecryptionException("Element had no EncryptedData child");
        }
        
        //TODO need to re-eval and rewrite this based on new credential based resolvers
        //EncryptedElementTypeKeyResolver keyResolver = 
         //   new EncryptedElementTypeKeyResolver(encElement, recipient);
        
        //TODO these args aren't correct
        org.opensaml.xml.encryption.Decrypter decrypter = 
            new org.opensaml.xml.encryption.Decrypter(kekResolver, kekResolver);
        
        XMLObject xmlObject = null;
        try {
            xmlObject = decrypter.decryptData(encElement.getEncryptedData());
        } catch (DecryptionException e) {
            log.error("SAML Decrypter encountered an error decrypting element content", e);
            throw e; 
        }
        
        if (! (xmlObject instanceof SAMLObject)) {
            throw new DecryptionException("Decrypted XMLObject was not an instance of SAMLObject");
        }
        
        return (SAMLObject) xmlObject;
    }

}
