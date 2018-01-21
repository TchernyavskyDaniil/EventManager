package com.eve.controller;

import com.eve.entity.Event;
import com.eve.repository.EventRepository;
import com.eve.web.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/home/events")
    public String showEventsList(Model model){
        List<Event> events = eventRepository.findAll();
        List<EventDto> eventDtos = new ArrayList<>();
        for (Event e : events){
            EventDto e1 = new EventDto();
//            e1.setAddress(e.getAddress());
//            e1.setCountry(e.getAddress().getCountry());
//            e1.setDate(e.getDate());
//            e1.setDescription(e.getDescription());
            e1.setName(e.getName());
//            e1.setOwner(e.getOwner().getUsername());
            eventDtos.add(e1);
        }

        model.addAttribute("events",eventDtos);


        return "eventsPage";
    }

}
