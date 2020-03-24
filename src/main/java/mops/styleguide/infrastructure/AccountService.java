package mops.styleguide.infrastructure;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public interface AccountService {
    /**
     * Erzeugt ein Account DTO für die Views aus einem Token.
     *
     * @param token
     * @return neues Account DTO
     */
    Account createAccountFromToken(AbstractAuthenticationToken token);
}
