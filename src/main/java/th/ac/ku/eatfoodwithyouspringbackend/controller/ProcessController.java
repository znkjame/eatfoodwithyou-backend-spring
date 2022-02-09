package th.ac.ku.eatfoodwithyouspringbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Process;
import th.ac.ku.eatfoodwithyouspringbackend.service.ProcessService;

import java.util.List;

@RestController
@RequestMapping("/api/processes")
public class ProcessController {
    @Autowired
    private ProcessService processService;

    @GetMapping
    public List<Process> findAll(){
        return processService.findAll();
    }

    @GetMapping("{id}")
    public Process findById(@PathVariable("id") int id){
        return processService.findById(id);
    }

    @PostMapping
    public Process create(@RequestBody Process process){
        return processService.create(process);
    }

    @PutMapping("{id}")
    public Process update(@PathVariable("id") int id,@RequestBody Process process){
        return processService.update(id,process);
    }

    @DeleteMapping("{id}")
    public Process delete(@PathVariable("id") int id){
        return processService.delete(id);
    }
}
