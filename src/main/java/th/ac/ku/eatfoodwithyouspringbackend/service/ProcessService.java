package th.ac.ku.eatfoodwithyouspringbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.eatfoodwithyouspringbackend.model.foodrecipes.Process;
import th.ac.ku.eatfoodwithyouspringbackend.respository.ProcessRepository;

import java.util.List;

@Service
public class ProcessService {
    @Autowired
    private ProcessRepository processRepository;

    public List<Process> findAll(){
        return processRepository.findAll();
    }

    public Process findById(int id){
        return processRepository.findById(id).get();
    }

    public Process create(Process process){
        return processRepository.save(process);
    }

    public Process update(int id, Process modifyProcess){
        Process process = processRepository.findById(id).get();
        if(modifyProcess.getProcess() != null)
            process.setProcess(modifyProcess.getProcess());
        processRepository.save(process);
        return process;
    }

    public Process delete(int id){
        Process process = processRepository.findById(id).get();
        processRepository.delete(process);
        return process;
    }
}
