package com.eve.controller;

import com.eve.entity.Event;
import com.eve.entity.User;
import com.eve.repository.EventRepository;
import com.eve.repository.UserRepository;
import com.eve.util.DateUtil;
import com.eve.web.dto.EventDto;
import com.eve.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home/events")
    public String showEventsList(Model model){
        List<Event> events = eventRepository.findAll();
        List<EventDto> eventDtos = new ArrayList<>();
        for (Event e : events){
            EventDto e1 = new EventDto(e);
            eventDtos.add(e1);
        }

        model.addAttribute("events",eventDtos);


        return "eventsPage";
    }

    @GetMapping("/home/events/info")
    public String getEventInfo(@ModelAttribute("id") Long eventId,Model model){
        Event e = eventRepository.findById(eventId);
        EventDto e1 = new EventDto(e);
        model.addAttribute("event",e1);
        return "eventInfo";
    }

    @GetMapping("/home/events/new")
    public String showRegistrationForm(Model model) {
        EventDto eventDto = new EventDto();
        model.addAttribute("event", eventDto);
        return "eventForm";
    }
    @Transactional
    @PostMapping("/home/events/add")
    public String addNewEvent(@ModelAttribute("event") EventDto event, Model model){
        if (eventRepository.findByName(event.getName())!=null){
            model.addAttribute("message","Event with this name already exists");
            return "errorPage";
        }
        Event e = new Event();
        e.setName(event.getName());
        User owner = userRepository.findByEmail(event.getOwner());
        if (owner==null){
            model.addAttribute("message","This email not found");
            return "errorPage";
        }
        e.setAddress(event.getAddress());
        try {
            e.setDate(DateUtil.SDF.parse(event.getDate()));
        } catch (ParseException e1) {
            e1.printStackTrace();
            model.addAttribute("message","Error parsing date");
            return "errorPage";
        }
        e.setDescription(event.getDescription());
        eventRepository.save(e);
        model.addAttribute("message","Event " + e.getName() + " created");
        return "index";
    }
}
