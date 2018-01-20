package com.eve.controller;

import com.eve.entity.Event;
import com.eve.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

public class MainController {

    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/home/events")
    @ResponseBody
    public ModelAndView showEventsList(Model model){
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events",events);
        return new ModelAndView("eventsPage","eventsModel",model);
    }

}
