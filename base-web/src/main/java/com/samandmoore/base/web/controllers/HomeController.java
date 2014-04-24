package com.samandmoore.base.web.controllers;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.samandmoore.base.core.SomeDomainModel;

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

    @RequestMapping(value = "/model", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity model() {

        final SomeDomainModel someDomainModel = new SomeDomainModel();

        someDomainModel.setId(1);
        someDomainModel.setName("Benji");

        someDomainModel.setCreatedAt(DateTime.now());

        return new ResponseEntity<SomeDomainModel>(someDomainModel, HttpStatus.OK);
    }
}
