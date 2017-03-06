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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rupp.spring.domain.Activity;
import com.rupp.spring.domain.ResponseList;
import com.rupp.spring.service.ActivityService;

@Controller
@RequestMapping("activities")
public class ActivityController {
	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService service;
    

    //@RequestMapping(value = "/v1", method = RequestMethod.GET)
    @GetMapping("/v1/all")
    @ResponseBody
    public List<Activity> getActivities() {
        logger.debug("====get all categories====");
        return service.list();
    }
    
    @GetMapping("/v1")
    @ResponseBody
    public ResponseList<Activity> getPage(@RequestParam(value="pagesize", defaultValue="10") int pagesize,
            @RequestParam(value = "cursorkey", required = false) String cursorkey) {
        logger.debug("====get page {} , {} ====", pagesize, cursorkey);
        return service.getPage(pagesize, cursorkey);
    }

    //@RequestMapping(value = "/v1/{id}", method = RequestMethod.GET)
    @GetMapping("/v1/{id}")
    public ResponseEntity<Activity> getActivity(@PathVariable("id") Long id) {

        logger.debug("====get category detail with id :[{}] ====", id);
        
        final Activity activity = service.get(id);
        //System.out.println(activity.getId());
        if (activity == null) {
            return new ResponseEntity("No DCategory found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(activity, HttpStatus.OK);
    }

    //@RequestMapping(value = "/v1", method = RequestMethod.POST)
    @PostMapping(value = "/v1")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        logger.debug("====create new category object ====");
        service.create(activity);

        return new ResponseEntity<>(activity, HttpStatus.OK);
    }

    //@RequestMapping(value = "/v1/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/v1/{id}")
    public ResponseEntity deleteActivity(@PathVariable Long id) {
        logger.debug("====delete category detail with id :[{}] ====", id);
        if (null == service.delete(id)) {
            return new ResponseEntity("No DCategory found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);

    }
    //@RequestMapping(value = "/v1/{id}", method = RequestMethod.PUT)
    @PutMapping("/v1/{id}")
    public ResponseEntity updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        logger.debug("====update category detail with id :[{}] ====", id);
        activity = service.update(id, activity);

        if (null == activity) {
            return new ResponseEntity("No Activity found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(activity, HttpStatus.OK);
    }
    @PostMapping("/v1/{id}/json")
    public ResponseEntity updateByJson(@PathVariable Long id, @RequestBody Activity activity) {
        logger.debug("====update category detail with id :[{}] ====", id);
        activity = service.update(id, activity);

        if (null == activity) {
            return new ResponseEntity("No activity found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(activity, HttpStatus.OK);
    }
    @PostMapping("/v1/json")
    public ResponseEntity createByJson(@RequestBody Activity activity) {
        logger.debug("====create new activity object with json====");
        service.create(activity);
        return new ResponseEntity(activity, HttpStatus.OK);
    }
    
    /**
     * <pre>
     * schema api : Content-Type: application/x-www-form-urlencoded 
     * example json value
     * 
     *   {
     *       primaryKeyName: "id",
     *       tableName: "Country",
     *       primaryKeyType: "long",
     *       columns: {
     *           comingSoon: "boolean",
     *           flagImageUrl: "text",
     *           isoCode: "text",
     *           name: "text",
     *           state: "long",
     *           tcsUrl: "text",
     *           createdDate: "datetime"
     *        }
     *   }
     *   </pre>
     * @param request
     */
    @RequestMapping(value = "v1/schema", method = { RequestMethod.GET })
    public ResponseEntity<Map<String, Object>> getschma(HttpServletRequest request) {
        final Map<String, Object> body = new HashMap<String, Object>();
        final Map<String,String> columns = new HashMap<>();
        
        columns.put("name", "text");
        columns.put("createdDate", "datetime");
        
        body.put("columns", columns);
        body.put("tableName", "category");
        body.put("primaryKeyName", "id");
        body.put("primaryKeyType", "long");
        
        return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK);
    }
}
