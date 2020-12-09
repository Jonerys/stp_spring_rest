package jonerys.test.springcrud.controller;

import jonerys.test.springcrud.model.Good;
import jonerys.test.springcrud.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class GoodController {

    private GoodService gms;

    @Autowired
    public GoodController(GoodService gms){
        this.gms = gms;
    }

    @GetMapping("/getgoodslist")
    public List<Good> findAll(){
        return gms.findAll();
    }

    @RequestMapping(value = "/addgood", method = RequestMethod.POST)
    public void createGood(@RequestBody String data){
        Good good = new Good();
        good.setName(data);
        gms.save(good);
    }

    @RequestMapping(value = "/updategood", method = RequestMethod.POST)
    public void updateGood(@RequestBody String data){
        String temp = null;
        try {
            temp = URLDecoder.decode(data, StandardCharsets.UTF_8.toString());
        } catch (Exception e){}
        String id = temp.split(";", 2)[0];
        String name = temp.split(";", 2)[1];
        Good good = gms.findById(Integer.parseInt(id));
        good.setName(name);
        gms.save(good);
    }

    @RequestMapping(value = "/deletegood", method = RequestMethod.POST)
    public void deleteGood(@RequestBody String data){
        gms.deleteById(Integer.parseInt(data));
    }
}
