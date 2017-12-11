package org.springframework.samples.petclinic.system;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class WelcomeController {

    @Autowired
    ConsulDiscoveryProperties consulDiscoveryProperties;

    @GetMapping("/")
    public ModelAndView welcome() {
        ModelAndView mav = new ModelAndView("welcome");
        mav.addObject("host", consulDiscoveryProperties.getIpAddress());
        return mav;
    }
}
