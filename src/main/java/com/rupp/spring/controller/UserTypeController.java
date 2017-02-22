package com.rupp.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rupp.spring.domain.UserType;
import com.rupp.spring.domain.ResponseList;
import com.rupp.spring.service.UserTypeService;

@Controller
@RequestMapping("usertypes")
public class UserTypeController {
    private static final Logger logger = LoggerFactory.getLogger(UserTypeController.class);

    @Autowired
    private UserTypeService service;
    

    //@RequestMapping(value = "/v1", method = RequestMethod.GET)
    @GetMapping("/v1/all")
    @ResponseBody
    public List<UserType> getUserTypes() {
        logger.debug("====get all user types====");
        return service.list();
    }
    
    @GetMapping("/v1")
    @ResponseBody
    public ResponseList<UserType> getPage(@RequestParam(value="pagesize", defaultValue="10") int pagesize,
            @RequestParam(value = "cursorkey", required = false) String cursorkey) {
        logger.debug("====get page {} , {} ====", pagesize, cursorkey);
        return service.getPage(pagesize, cursorkey);
    }

    //@RequestMapping(value = "/v1/{id}", method = RequestMethod.GET)
    @GetMapping("/v1/{id}")
    public ResponseEntity<UserType> getUserType(@PathVariable("id") Long id) {

        logger.debug("====get user type detail with id :[{}] ====", id);
        
        final UserType userType = service.get(id);
        if (userType == null) {
            return new ResponseEntity("No UserType found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userType, HttpStatus.OK);
    }
}
