package mops.styleguide.presentation.web;

import mops.styleguide.infrastructure.Account;
import mops.styleguide.infrastructure.KeycloakAccountService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.web.bind.annotation.ModelAttribute;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ModelAttribute("account")
    private Account getAccount(KeycloakAuthenticationToken token, KeycloakAccountService accountService) {
        return token != null ? accountService.createAccountFromToken(token) : null;
    }
}
