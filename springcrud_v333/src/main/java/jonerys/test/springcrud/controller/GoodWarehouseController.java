package jonerys.test.springcrud.controller;

import jonerys.test.springcrud.model.Good;
import jonerys.test.springcrud.model.Warehouse;
import jonerys.test.springcrud.service.GoodService;
import jonerys.test.springcrud.service.UserService;
import jonerys.test.springcrud.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
public class GoodWarehouseController {

    private WarehouseService ws;
    private GoodService gs;

    @Autowired
    public GoodWarehouseController(WarehouseService ws, GoodService gs){
        this.ws = ws;
        this.gs = gs;
    }

    @GetMapping("/getdata")
    public Warehouse findAllInThisWarehouse(){
        Warehouse warehouse = ws.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        return warehouse;
    }

    @RequestMapping(value = "/addgtowh", method = RequestMethod.POST)
    public void addToWarehouse(@RequestBody String data){
        Good good = gs.findByName(data);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Warehouse warehouse = ws.findByName(username);
        warehouse.addGood(good);
        ws.save(warehouse);
    }

    @RequestMapping(value = "/updateginwh", method = RequestMethod.POST)
    public void updInWarehouse(@RequestBody String data){
        String temp = null;
        try {
            temp = URLDecoder.decode(data, StandardCharsets.UTF_8.toString());
        } catch (Exception e){}
        String oldGoodName = temp.split(";", 2)[0];
        String newGoodName = temp.split(";", 2)[1];
        Good oldGood = gs.findByName(oldGoodName);
        Good newGood = gs.findByName(newGoodName);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Warehouse warehouse = ws.findByName(username);
        warehouse.deleteGood(oldGood);
        warehouse.addGood(newGood);
        ws.save(warehouse);
    }

    @RequestMapping(value = "/deletegfromwh", method = RequestMethod.POST)
    public void delFromWarehouse(@RequestBody String data){
        Good good = gs.findByName(data);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Warehouse warehouse = ws.findByName(username);
        warehouse.deleteGood(good);
        ws.save(warehouse);
    }
}
