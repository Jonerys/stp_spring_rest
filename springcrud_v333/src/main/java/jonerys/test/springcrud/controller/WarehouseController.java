package jonerys.test.springcrud.controller;


import jonerys.test.springcrud.model.Good;
import jonerys.test.springcrud.model.Role;
import jonerys.test.springcrud.model.User;
import jonerys.test.springcrud.model.Warehouse;
import jonerys.test.springcrud.service.UserService;
import jonerys.test.springcrud.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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


    @RequestMapping(value = "/addwarehouse", method = RequestMethod.POST)
    public void createWarehouse(@RequestBody String data){
        String temp = null;
        try {
            temp = URLDecoder.decode(data, StandardCharsets.UTF_8.toString()).split("&", 2)[1];
        } catch (Exception e){}
        String name = temp.split("=", 2)[1];
        Warehouse warehouse = new Warehouse();
        warehouse.setName(name);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        User user = new User();
        user.setLogin(name);
        user.setPassword(passwordEncoder.encode("123"));
        user.setRole(Role.USER);
        us.save(user);
        ws.save(warehouse);
    }

    @RequestMapping(value = "/updatewarehouse", method = RequestMethod.POST)
    public void updateWarehouseForm(@RequestBody String data){
        String temp = null;
        try {
            temp = URLDecoder.decode(data, StandardCharsets.UTF_8.toString());
        } catch (Exception e){}
        String id = temp.split("&", 2)[0].split("=", 2)[1];
        String name = temp.split("&", 2)[1].split("=", 2)[1];
        Warehouse warehouse = ws.findById(Integer.parseInt(id));
        String oldName = warehouse.getName();
        warehouse.setName(name);
        ws.save(warehouse);
        User user = us.findByLogin(oldName);
        user.setLogin(name);
        us.save(user);
    }


    @RequestMapping(value = "/deletewarehouse", method = RequestMethod.POST)
    public void deleteWarehouse(@RequestBody String data){
        String temp = data.split("&", 2)[0];
        String id = temp.split("=", 2)[1];
        us.deleteByLogin(ws.findById(Integer.parseInt(id)).getName());
        ws.deleteById(Integer.parseInt(id));
    }
}
