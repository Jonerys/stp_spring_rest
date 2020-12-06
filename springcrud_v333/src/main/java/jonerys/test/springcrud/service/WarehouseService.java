package jonerys.test.springcrud.service;

import jonerys.test.springcrud.model.Warehouse;
import jonerys.test.springcrud.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository wr){
        warehouseRepository = wr;
    }

    public Integer findIdByName(String name) { return warehouseRepository.findIdByName(name); }

    public Warehouse findByName(String name) { return warehouseRepository.findByName(name); }

    public Warehouse findById(Integer id){
        return warehouseRepository.getOne(id);
    }

    public List<Warehouse> findAll(){
        return warehouseRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Warehouse save(Warehouse we){
        return warehouseRepository.save(we);
    }

    public void updateById(Integer id, String name) {
        warehouseRepository.update(id, name);
    }

    public void deleteById(Integer id) {
        warehouseRepository.deleteById(id);
    }
}
