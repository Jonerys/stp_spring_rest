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

@RestController
public class GoodWarehouseController {

    private WarehouseService ws;
    private GoodService gs;
    private UserService us;

    @Autowired
    public GoodWarehouseController(WarehouseService ws, GoodService gs, UserService us){

        this.ws = ws;
        this.gs = gs;
        this.us = us;
    }

    @GetMapping("/getdata2")
    public Good fu(){
        Good good = gs.findById(1);
        return good;
    }

    @GetMapping("/getdata")
    public Warehouse findAllInThisWarehouse(){
        Warehouse warehouse = ws.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        return warehouse;
    }

    @GetMapping("/gw-create")
    public String createGoodForm(Model model){
        model.addAttribute("goods", gs.findAll());
        return "gw-create";
    }

    @PostMapping("/gw-create")
    public String createGoodswh(@RequestParam("good") String goodName){
        Good good = gs.findByName(goodName);
        Warehouse warehouse = ws.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        warehouse.addGood(good);
        ws.save(warehouse);
        return "redirect:/goods-warehouses";
    }
}
