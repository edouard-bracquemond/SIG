package fr.orleans.sig.repository;

import fr.orleans.sig.model.sig.Equipement;
import fr.orleans.sig.model.sig.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    public List<Report> findByIdEquipementAndAndType(Long id, Equipement equipement);
}
