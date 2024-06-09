package dev.fernando.figaro.G.Photos.PJ.service;

import dev.fernando.figaro.G.Photos.PJ.model.Photo;
import dev.fernando.figaro.G.Photos.PJ.repository.PhotosRepository;
import org.springframework.stereotype.Service;


//@Component
@Service

public class PhotoService {

   private final PhotosRepository photosRepository;

    public PhotoService(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }


    public Iterable<Photo> get() {
        return photosRepository.findAll();
    }

    public Photo get(Integer id) {
        return photosRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        photosRepository.deleteById(id);
    }

    public Photo save(String filename, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setContentType(contentType);
        photo.setFilename(filename);
        photo.setData(data);
        photosRepository.save(photo);
        return photo;

    }
}
