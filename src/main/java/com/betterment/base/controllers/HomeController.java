package com.betterment.base.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Sam Moore
 * @since 4/19/14 10:19 PM
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {

        return new ModelAndView("home/index");
    }

    @RequestMapping(value = "/throw", method = RequestMethod.GET)
    public ModelAndView thrower() {

        throw new IllegalStateException("bah, bad state, bro.");
    }
}
