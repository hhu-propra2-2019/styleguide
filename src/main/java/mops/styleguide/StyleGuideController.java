package mops.styleguide;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import mops.styleguide.config.Account;
import mops.styleguide.config.AccountService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
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

    public StyleGuideController(MeterRegistry registry, AccountService accountService) {
        authenticatedAccess = registry.counter("access.authenticated");
        publicAccess = registry.counter("access.public");
        this.accountService = accountService;
    }

    @ModelAttribute("account")
    private Account getAccount(KeycloakAuthenticationToken token) {
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
