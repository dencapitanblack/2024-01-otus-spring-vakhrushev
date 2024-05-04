package ru.cap.home.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.cap.home.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundEx(EntityNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleEntityNotFoundEx(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", ex.getMessage());
        return modelAndView;
    }

}
