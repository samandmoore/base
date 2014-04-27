package com.samandmoore.base.web.handlebars;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jknack.handlebars.springmvc.HandlebarsView;

/**
 * @author Sam Moore
 * @since 4/26/14 9:53 PM
 */
public class CustomHandlebarsView extends HandlebarsView {

    private static final String VIEW_USER_KEY = "currentUser";

    @Override
    protected void renderMergedTemplateModel(final Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {

        //model.put(VIEW_USER_KEY, "Sam");

        super.renderMergedTemplateModel(model, request, response);
    }
}
