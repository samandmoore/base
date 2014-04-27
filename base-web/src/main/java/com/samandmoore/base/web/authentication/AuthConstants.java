package com.samandmoore.base.web.authentication;

/**
 * @author Sam Moore
 * @since 4/27/14 1:42 AM
 */
public final class AuthConstants {

    public static final String CSRF_TOKEN_KEY = AuthConstants.class.getName() + ".CSRF_TOKEN";
    public static final String USER_ID_KEY = AuthConstants.class.getName() + ".USER_ID";

    private AuthConstants() {

    }
}
