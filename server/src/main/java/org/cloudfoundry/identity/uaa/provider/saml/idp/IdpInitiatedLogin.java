/*
 * ****************************************************************************
 *     Cloud Foundry
 *     Copyright (c) [2009-2017] Pivotal Software, Inc. All Rights Reserved.
 *
 *     This product is licensed to you under the Apache License, Version 2.0 (the "License").
 *     You may not use this product except in compliance with the License.
 *
 *     This product includes a number of subcomponents with
 *     separate copyright notices and license terms. Your use of these
 *     subcomponents is subject to the terms and conditions of the
 *     subcomponent's license, as noted in the LICENSE file.
 * ****************************************************************************
 */

package org.cloudfoundry.identity.uaa.provider.saml.idp;

import org.opensaml.common.SAMLException;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.signature.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.context.SAMLMessageContext;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Controller
public class IdpInitiatedLogin {

    private static final Logger log = LoggerFactory.getLogger(IdpInitiatedLogin.class);

    private IdpWebSsoProfile idpWebSsoProfile;
    private MetadataManager metadataManager;
    private SamlServiceProviderConfigurator configurator;

    @RequestMapping("/saml/idp/initiate")
    public void initiate(@RequestParam(value = "sp", required = false) String sp,
                         HttpServletRequest request,
                         HttpServletResponse response)
        throws IOException,
               MessageEncodingException,
               SAMLException, SecurityException, MarshallingException, MetadataProviderException, SignatureException {

        if (!hasText(sp)) {
            //TODO missing SP variable
            throw new RuntimeException("TODO");
        }
        log.debug(String.format("IDP is initiating authentication request to SP[%s]", sp));
        Optional<SamlServiceProviderHolder> holder = configurator.getSamlServiceProviders().stream().filter(serviceProvider -> sp.equals(serviceProvider.getSamlServiceProvider().getEntityId())).findFirst();
        if (!holder.isPresent()) {
            //TODO sp not found
            log.debug(String.format("SP[%s] was not found, aborting saml response", sp));
            throw new RuntimeException("TODO");
        }

        //TODO get the first name ID format in metadata
        String nameId = "urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified";
        idpWebSsoProfile.buildIdpInitiatedAuthnRequest(nameId, sp);
        idpWebSsoProfile.sendResponse(SecurityContextHolder.getContext().getAuthentication(),
                                      getSamlContext(request, response),
                                      getIdpIniatedOptions());
    }

    protected SAMLMessageContext getSamlContext(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    protected IdpWebSSOProfileOptions getIdpIniatedOptions() {
        IdpWebSSOProfileOptions options = new IdpWebSSOProfileOptions();
        options.setAssertionsSigned(false);
        return options;
    }

    public void setIdpWebSsoProfile(IdpWebSsoProfile idpWebSsoProfile) {
        this.idpWebSsoProfile = idpWebSsoProfile;
    }

    public void setMetadataManager(MetadataManager metadataManager) {
        this.metadataManager = metadataManager;
    }

    public void setConfigurator(SamlServiceProviderConfigurator configurator) {
        this.configurator = configurator;
    }
}
