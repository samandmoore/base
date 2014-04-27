package com.samandmoore.base.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Sam Moore
 * @since 4/27/14 12:10 AM
 */
@Controller
public class SessionsController {

    @RequestMapping(value = { "/login" })
    public ModelAndView login() {

        return new ModelAndView("sessions/login");
    }

    @RequestMapping(value = { "/logout" })
    public void logout() {

    }
}
