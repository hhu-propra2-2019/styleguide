package mops.styleguide.config;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class KeycloakAccountService implements AccountService {
    @Override
    public Account createAccountFromToken(AbstractAuthenticationToken token) {
        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) token;
        KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal) keycloakAuthenticationToken.getPrincipal();
        return new Account(
                keycloakPrincipal.getName(),
                keycloakPrincipal.getKeycloakSecurityContext().getIdToken().getEmail(),
                null,
                keycloakAuthenticationToken.getAccount().getRoles());
    }
}
