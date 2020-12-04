package jonerys.test.springcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortalController {

    @RequestMapping(value="/goodsinwarehouse")
    public String goodsInWarehouse(){
        return "goodsinwarehouse";
    }

    @RequestMapping(value="/goodslist")
    public String goodsList(){
        return "goodslist";
    }

    @RequestMapping(value="/warehouseslist")
    public String warehousesList(){
        return "warehouseslist";
    }

}
