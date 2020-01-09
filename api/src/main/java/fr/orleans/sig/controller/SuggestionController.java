package fr.orleans.sig.controller;

import fr.orleans.sig.model.sig.Equipement;
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
                                            @CurrentUser UserPrincipal currentUser){

        User user = userRepository.getOne(currentUser.getId());

        Suggestion suggestion = new Suggestion();

        suggestion.setComment(commentaire);
        suggestion.setCoords(coords);
        suggestion.setUser(user);
        suggestion.setPseudo(user.getUsername());

        suggestionRepository.save(suggestion);

        return ResponseEntity.ok(suggestion);
    }
}
