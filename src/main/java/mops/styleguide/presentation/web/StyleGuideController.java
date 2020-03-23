package mops.styleguide.presentation.web;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import mops.styleguide.infrastructure.Account;
import mops.styleguide.infrastructure.AccountService;
import mops.styleguide.infrastructure.KeycloakAccountService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class StyleGuideController {
    private final Counter authenticatedAccess;
    private final Counter publicAccess;
    private final AccountService accountService;

    @Autowired
    public StyleGuideController(MeterRegistry registry, AccountService accountService) {
        authenticatedAccess = registry.counter("access.authenticated");
        publicAccess = registry.counter("access.public");
        this.accountService = accountService;
    }

    @ModelAttribute("account")
    private Account getAccount(KeycloakAuthenticationToken token, KeycloakAccountService accountService) {
        return token != null ? accountService.createAccountFromToken(token) : null;
    }

    @GetMapping("/")
    public String index(KeycloakAuthenticationToken token, Model model) {
        publicAccess.increment();
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }
}
