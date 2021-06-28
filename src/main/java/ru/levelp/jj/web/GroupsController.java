package ru.levelp.jj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.levelp.jj.dao.GroupDAO;
import ru.levelp.jj.dao.UsersDAO;
import ru.levelp.jj.model.Group;
import ru.levelp.jj.model.User;

import java.util.List;

@RestController
public class GroupsController {
    @Autowired
    private GroupDAO groups;

    @Autowired
    private UsersDAO users;

    @GetMapping("/api/groups/findByName")
    public Group findByName(@RequestParam String groupName) {
        return groups.findByName(groupName);
    }

    @GetMapping("/api/groups/all")
    public List<Group> findAll() {
        return groups.findAll();
    }

    @PostMapping("/api/groups/create")
    public Group create(@RequestParam String name) {
        return groups.create(name);
    }

    @PostMapping("/api/group/{id}/add-user/{login}")
    public Group addUser(@PathVariable int id, @PathVariable String login) {
        Group group = groups.findById(id).orElseThrow();
        User user = users.findByLogin(login);
        group.getUsers().add(user);
        user.setGroup(group);
        return group;
    }
}
