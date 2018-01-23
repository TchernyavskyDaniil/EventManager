package com.eve.controller;

import com.eve.entity.Address;
import com.eve.entity.Event;
import com.eve.entity.User;
import com.eve.repository.AddressRepository;
import com.eve.repository.EventRepository;
import com.eve.repository.UserRepository;
import com.eve.util.DateUtil;
import com.eve.web.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;


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

    @GetMapping("/home/events/delete")
    public String deleteEvent(@ModelAttribute("id") Long eventId,Model model){
        Event e = eventRepository.findById(eventId);
        if (e==null){
            model.addAttribute("message","Event not found");
            return showEventsList(model);
        }

        eventRepository.delete(e);
        return showEventsList(model);
    }

    @Transactional
    @PostMapping("/home/events/add")
    public String addNewEvent(@ModelAttribute("event") EventDto event, Model model){
        if (eventRepository.findByName(event.getName())!=null){
            model.addAttribute("message","Event with this name already exists");
            return showEventsList(model);
        }

        Event e = new Event();
        e.setName(event.getName());
        User owner = userRepository.findByEmail(event.getOwner());
        if (owner==null){
            model.addAttribute("message","This email not found");
            return showEventsList(model);
        }

        Address address = addressRepository.findByCountryAndCityAndStreetAndName(
          event.getCountry(),event.getCity(),event.getStreet(),event.getName());
        Set<Event> ev = new HashSet<>();

        if (address!=null){
            ev = address.getEvents();
        }else {
            address = new Address();
            address.setName(event.getName());
            address.setCountry(event.getCountry());
            address.setCity(event.getCity());
            address.setStreet(event.getStreet());
            address.setX(event.getX());
            address.setY(event.getY());
        }

        e.setAddress(address);
        ev.add(e);
        addressRepository.save(address);

        try {
            e.setDate(DateUtil.SDF.parse(event.getDate()));
        } catch (ParseException e1) {
            e1.printStackTrace();
            model.addAttribute("message","Error parsing date");
            return "errorPage";
        }
        e.setDescription(event.getDescription());
        Set<Event> events = owner.getEvents();
        events.add(e);
        userRepository.save(owner);
        e.setOwner(owner);
        eventRepository.save(e);
        model.addAttribute("message","Event " + e.getName() + " created");

        return showEventsList(model);
    }
}
