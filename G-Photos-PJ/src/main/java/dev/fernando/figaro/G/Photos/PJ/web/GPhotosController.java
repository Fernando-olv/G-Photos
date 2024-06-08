package dev.fernando.figaro.G.Photos.PJ.web;

import dev.fernando.figaro.G.Photos.PJ.model.Photo;
import dev.fernando.figaro.G.Photos.PJ.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class GPhotosController {

    private final PhotoService photoService;

    public GPhotosController(PhotoService photoService){
        this.photoService = photoService;
    }

    //private List<Photo> db = List.of(new Photo("1","hello.jpg"));

    @GetMapping("/")
    public String hello(){
        return "Hello world!";
    }

    @GetMapping("/photos")
        public Collection<Photo> get() {
            return photoService.get();

    }

    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = photoService.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;

    }


    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable String id) {
        Photo photo = photoService.remove(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/photos")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return photoService.save(file.getOriginalFilename(),file.getContentType(), file.getBytes());
    }

}
