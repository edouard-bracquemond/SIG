package fr.orleans.sig.controller;

import com.vividsolutions.jts.io.ParseException;
import fr.orleans.sig.model.sig.Suggestion;
import fr.orleans.sig.model.user.User;
import fr.orleans.sig.repository.SuggestionRepository;
import fr.orleans.sig.repository.UserRepository;
import fr.orleans.sig.security.CurrentUser;
import fr.orleans.sig.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SuggestionController {
    @Autowired
    private SuggestionRepository suggestionRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/suggestions")
    public ResponseEntity<?> getSuggestions(){
        List<Suggestion> suggestionList = suggestionRepository.findAll();
        return ResponseEntity.ok(suggestionList);
    }

    @PostMapping("/suggestions")
    public ResponseEntity<?> addSuggestions(@RequestParam(value = "comment") String commentaire,
                                            @RequestParam(value = "coords") String coords,
                                            @CurrentUser UserPrincipal currentUser) throws ParseException {

        User user = userRepository.getOne(currentUser.getId());

        Suggestion suggestion = new Suggestion();

        suggestion.setComment(commentaire);
        suggestion.setCoords(coords);
        suggestion.setUser(user);
        suggestion.setPseudo(user.getUsername());

        suggestionRepository.save(suggestion);

        String [] s = coords.split(",");
        System.out.println(s[0]+" "+s[1]);

        Double longi = Double.valueOf(s[0]);
        Double lati = Double.valueOf(s[1]);

        System.out.println(lati+" "+longi);

        if (!suggestionRepository.translateToGeom(longi, lati).isEmpty()){
            suggestionRepository.addGeom(longi,lati, suggestion.getId());
        }else{
            suggestionRepository.delete(suggestion);
            return ResponseEntity.status(400).body("Oulala on reste dans l'agglo");
        }

        return ResponseEntity.ok(suggestion);
    }
}
