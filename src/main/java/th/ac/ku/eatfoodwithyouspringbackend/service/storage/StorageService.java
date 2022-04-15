package th.ac.ku.eatfoodwithyouspringbackend.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();

    String store(MultipartFile file, String type);

    Stream<Path> loadAll();

    Path load(String filename,String type);

    Resource loadAsResource(String filename,String type);

    void deleteAll();
}
