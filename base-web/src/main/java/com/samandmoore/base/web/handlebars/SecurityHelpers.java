package com.samandmoore.base.web.handlebars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.samandmoore.base.web.authentication.AuthConstants;
import com.samandmoore.base.web.authentication.AuthSession;
import com.samandmoore.base.web.authentication.CurrentUser;

/**
 * @author Sam Moore
 * @since 4/27/14 12:49 AM
 */
@Component
public class SecurityHelpers {

    @Autowired
    private AuthSession authSession;

    @Autowired
    private CurrentUser currentUser;

    public CharSequence csrf_token() {

        return authSession.get(AuthConstants.CSRF_TOKEN_KEY);
    }

    public CharSequence current_user() {

        return (String) currentUser.get();
    }
}
