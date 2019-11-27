package fr.orleans.sig.controller;

import fr.orleans.sig.model.geo.Arbres;
import fr.orleans.sig.model.sig.Image;
import fr.orleans.sig.model.sig.Saison;
import fr.orleans.sig.repository.ArbresRepository;
import fr.orleans.sig.repository.ImageRepository;
import fr.orleans.sig.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ArbreController {
    @Autowired
    private ArbresRepository arbresRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/tree")
    public ResponseEntity<Arbres> arbreById(@RequestParam(value = "id") int id) {
        Optional<Arbres> arbres = arbresRepository.findById(id);
        return ResponseEntity.ok(arbres.get());
    }

    @GetMapping("/tree/images/")
    public ResponseEntity<?> imagesByArbre(@RequestParam(value = "id") int id){
        Optional<Arbres> arbre = arbresRepository.findById(id);

        List<Image> images = imageRepository.findImageByArbre(arbre.get());

        return ResponseEntity.ok(images);
    }

    @PostMapping("/tree/image/")
    public ResponseEntity<?> addImage(@RequestParam(value = "id") int id,
                                      @RequestParam("file") MultipartFile file,
                                      @Valid @RequestParam("saison")Saison saison){
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        Image image = new Image();
        image.setLocation(fileName);
        image.setSaison(saison);
        image.setUrl(fileDownloadUri);
        Optional<Arbres> arbres = arbresRepository.findById(id);
        image.setArbre(arbres.get());

        imageRepository.save(image);

        return ResponseEntity.ok(image);
    }

}
