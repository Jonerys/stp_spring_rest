package jonerys.test.springcrud.service;

import jonerys.test.springcrud.model.Good;
import jonerys.test.springcrud.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {
    private final GoodRepository goodRepository;

    @Autowired
    public GoodService(GoodRepository gmr){
        goodRepository = gmr;
    }

    public Good findById(Integer id){
        return goodRepository.getOne(id);
    }

    public Good findByName(String name){
        return goodRepository.findByName(name);
    }

    public List<Good> findAll(){
        return goodRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Good save(Good gme){
        return goodRepository.save(gme);
    }

    public void updateById(Integer id, String name) {
        goodRepository.update(id, name);
    }

    public void deleteById(Integer id) {
        goodRepository.deleteById(id);
    }


}
