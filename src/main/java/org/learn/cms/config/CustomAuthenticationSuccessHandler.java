package org.learn.cms.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.cms.user.Providers;
import org.learn.cms.user.UserDTO;
import org.learn.cms.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        log.info("user logging in using google");
        try {
            DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
            String name = user.getAttribute("name");
            String picture = user.getAttribute("picture");
            String email = user.getAttribute("email");
            boolean email_verified = Boolean.TRUE.equals(user.getAttribute("email_verified"));

            UserDTO model = new UserDTO();
            model.setName(name);
            model.setProfilePic(picture);
            model.setEmail(email);
            model.setEmailVerified(email_verified);
            model.setPassword("changeIt");
            model.setUserProviderId(user.getName());
            model.setProvider(Providers.GOOGLE);
            model.setEnabled(true);

            UserDTO ignore = userService.save(model);
            log.info("{} logged in using google service!", email);

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, "/dashboard");
    }
}
