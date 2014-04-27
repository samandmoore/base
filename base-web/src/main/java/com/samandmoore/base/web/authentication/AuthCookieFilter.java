package com.samandmoore.base.web.authentication;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.io.BaseEncoding;

/**
 * @author Sam Moore
 * @since 4/27/14 1:46 AM
 */
@Component
public class AuthCookieFilter extends HandlerInterceptorAdapter {

    private static final String AUTH_COOKIE_NAME = "_session";

    @Autowired
    private AuthSession authSession;

    @Autowired
    private CurrentUser currentUser;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final boolean allowUnauthenticated = handlerMethod.getMethodAnnotation(Unauthenticated.class) != null;

        final Cookie authCookie = getAuthCookie(request);

        final boolean authenticated = populateAuth(authCookie);

        if (authenticated || allowUnauthenticated) {
            return true;
        }

        return unauthenticated(response);
    }

    private boolean populateAuth(final Cookie authCookie) {

        if (authCookie == null) {
            return false;
        }

        if (Strings.isNullOrEmpty(authCookie.getValue())) {
            return false;
        }

        final String decoded = new String(BaseEncoding.base64Url().decode(authCookie.getValue()), Charsets.UTF_8);

        final Map<String, String> kvps = Splitter.on("&")
                .trimResults()
                .omitEmptyStrings()
                .withKeyValueSeparator(Splitter.on("=").trimResults().omitEmptyStrings())
                .split(decoded);

        for (Map.Entry<String, String> entry : kvps.entrySet()) {
            authSession.put(entry.getKey(), entry.getValue());
        }

        return currentUser.get() != null;
    }

    private Cookie getAuthCookie(final HttpServletRequest request) {

        final Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            return null;
        }

        Cookie authCookie = null;
        for (final Cookie cookie : cookies) {
            if (cookie.getName().equals(AUTH_COOKIE_NAME)) {
                authCookie = cookie;
                break;
            }
        }

        return authCookie;
    }

    private boolean unauthenticated(final HttpServletResponse response) throws IOException {

        response.sendRedirect("/login");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView)
            throws Exception {

        final Cookie cookie = getCookieTemplate();

        final String serialized = authSession.serialize();

        final String encoded = BaseEncoding.base64Url().encode(serialized.getBytes(Charsets.UTF_8));

        cookie.setValue(encoded);

        response.addCookie(cookie);
    }

    public Cookie getCookieTemplate() {

        final Cookie cookie = new Cookie(AUTH_COOKIE_NAME, null);

        cookie.setMaxAge(-1);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        return cookie;
    }
}
