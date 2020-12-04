package jonerys.test.springcrud.controller;


import jonerys.test.springcrud.model.Good;
import jonerys.test.springcrud.model.Role;
import jonerys.test.springcrud.model.User;
import jonerys.test.springcrud.model.Warehouse;
import jonerys.test.springcrud.service.UserService;
import jonerys.test.springcrud.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WarehouseController {

    private WarehouseService ws;
    private UserService us;

    @Autowired
    public WarehouseController(WarehouseService ws, UserService us){

        this.ws = ws;
        this.us = us;
    }

    @GetMapping("/getwarehouseslist")
    public List<Warehouse> findAll(){
        return ws.findAll();
    }



    @PostMapping("/warehouses-create")
    public String createWarehouse(@ModelAttribute("warehouse") Warehouse we){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        User user = new User();
        user.setLogin(we.getName());
        user.setPassword(passwordEncoder.encode("123"));
        user.setRole(Role.USER);
        us.save(user);
        ws.save(we);
        return "redirect:/warehouses";
    }

    @GetMapping("/warehouses-update/{id}")
    public String updateWarehouseForm(@PathVariable("id") String id, Model model){
        model.addAttribute("warehouse", ws.findById(Integer.parseInt(id)));
        return "warehouses-update";
    }

    @PostMapping("/warehouses-update")
    public String updateWarehouse(@ModelAttribute("warehouse") Warehouse we){
        User user = us.findByLogin(ws.findById(we.getId()).getName());
        user.setLogin(we.getName());
        us.save(user);
        ws.save(we);
        return "redirect:/warehouses";
    }

    @GetMapping("/warehouses-delete/{id}")
    public String deleteWarehouse(@PathVariable("id") String id){
        us.deleteByLogin(ws.findById(Integer.parseInt(id)).getName());
        ws.deleteById(Integer.parseInt(id));
        return "redirect:/warehouses";
    }
}
