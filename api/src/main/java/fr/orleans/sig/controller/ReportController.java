package fr.orleans.sig.controller;

import fr.orleans.sig.exception.ResourceNotFoundException;
import fr.orleans.sig.model.geo.Banc;
import fr.orleans.sig.model.sig.Equipement;
import fr.orleans.sig.model.sig.Report;
import fr.orleans.sig.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReportController {
    @Autowired
    private BancRepository bancRepository;
    @Autowired
    private CorbeillesRepository corbeillesRepository;
    @Autowired
    private SanitairesRepostiory sanitairesRepostiory;
    @Autowired
    private DechetsRepository dechetsRepository;
    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("/reports/")
    public ResponseEntity<?> getReports(@RequestParam(value = "eq")Equipement equipement,
                                        @RequestParam(value = "id") Long id){
        List<Report> reports = reportRepository.findByIdEquipementAndAndType(id, equipement);

        return ResponseEntity.ok(reports);
    }

    @PostMapping(value = "/reports")
    public ResponseEntity<?> addReports(@RequestParam(value = "comment") String commentaire,
                                        @RequestParam(value = "eq") Equipement equipement,
                                        @RequestParam(value = "id_eq") int id){

        switch (equipement){
            case BANC:{
                if (!bancRepository.existsById(id)){
                    throw new ResourceNotFoundException("equipement", "id", id);
                }
                break;
            }
        }

        Report report = new Report();
        report.setComment(commentaire);
        report.setType(equipement);
        report.setIdEquipement((long) id);

        reportRepository.save(report);

        return ResponseEntity.ok(report);
    }
}
