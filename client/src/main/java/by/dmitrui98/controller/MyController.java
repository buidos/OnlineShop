package by.dmitrui98.controller;


import by.dmitrui98.entity.User;
import by.dmitrui98.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/controller")
public class MyController {

    //@Autowired
    //private UserRepository userRepository;

    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    //@ResponseBody
    public User getUser() {


       // List<User> list = userRepository.findAll();
        //System.out.println(list);

        return createUser();
    }



    private User createUser() {
        User user = new User();
        user.setEmail("test@list.com");
        user.setFname("Толян");
        user.setLogin("tester777");
        user.setPassword("111f");
        user.setPhone("+375441234567");
        user.setCreate_at(new Date());

        return user;
    }
}
