package com.samandmoore.base.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.samandmoore.base.web.authentication.CurrentUser;
import com.samandmoore.base.web.authentication.Unauthenticated;

/**
 * @author Sam Moore
 * @since 4/27/14 12:10 AM
 */
@Controller
public class SessionsController {

    @Autowired
    private CurrentUser currentUser;

    @Unauthenticated
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {

        return new ModelAndView("sessions/login");
    }

    @Unauthenticated
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String create(@RequestParam String username, @RequestParam String password) {

        if (username.equals("user") && password.equals("password")) {
            currentUser.set("Sam Identifier");

            return "redirect:/";
        }

        return "redirect:/login";
    }

    @RequestMapping(value = { "/logout" })
    public void logout() {

        currentUser.set(null);
    }
}
