package th.ac.ku.eatfoodwithyouspringbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import th.ac.ku.eatfoodwithyouspringbackend.response.PhotoResponse;
import th.ac.ku.eatfoodwithyouspringbackend.response.ResponseHandler;
import th.ac.ku.eatfoodwithyouspringbackend.service.storage.FileSystemStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
public class PhotoController {
    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @GetMapping("/photo/{type}/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String type, @PathVariable String filename, HttpServletRequest request){
        Resource resource = fileSystemStorageService.loadAsResource(filename,type);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw new RuntimeException("not found file");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/photo/{type}")
    public ResponseEntity<Object> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                   @PathVariable("type") String type) {

        String filename = fileSystemStorageService.store(file,type);
        String fileURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/photo/").path(type).path(filename).toUriString();

//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return ResponseHandler.generateResponse("Upload photo success!!", HttpStatus.OK,new PhotoResponse(filename,fileURL,file.getContentType(), file.getSize()));
    }
}
