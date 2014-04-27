package com.samandmoore.base.web.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Sam Moore
 * @since 4/27/14 1:44 AM
 */
@Component
public class CurrentUser {

    @Autowired
    private AuthSession authSession;

    public Object get() {

        return authSession.get(AuthConstants.USER_ID_KEY);
    }

    public void set(final Object currentUser) {

        authSession.put(AuthConstants.USER_ID_KEY, currentUser);
    }
}
