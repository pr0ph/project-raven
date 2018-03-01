package com.raven.utils;

import com.raven.model.City;
import com.raven.model.Post;
import com.raven.model.Role;
import com.raven.model.User;
import com.raven.repository.RoleRepository;
import com.raven.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class InitializerBean {

    public InitializerBean(RoleRepository roleRepository) {

        User johnDoe = new User("johndoe@raven.com", "John", null, "Doe", City.BDP, "pass");
        User maryJane = new User("maryjane@raven.com", "Mary", "Rose", "Jane", City.WRS, "pass");
        User billSmith = new User("billsmith@raven.com", "Bill", null, "Smith", City.KRK, "pass");

        johnDoe.addPost(new Post("A very good content for this post."));
        johnDoe.addPost(new Post("A bit worse content here."));
        johnDoe.addPost(new Post("Generating some cool content."));
        maryJane.addPost(new Post("I like to write for nothing."));
        maryJane.addPost(new Post("Typing in vain, what a pleasure."));

        Role userRole = new Role("USER");
        Role adminRole = new Role("ADMIN");

        johnDoe.addRoles(userRole, adminRole);
        maryJane.addRoles(userRole, adminRole);
        billSmith.addRoles(userRole);

        // Everything persisted through cascades
        roleRepository.save(adminRole);
        roleRepository.save(userRole);

    }

}