package bcd.lobinho.repository;

import bcd.lobinho.model.ProblemasSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "problema-saude", path = "problema-saude")
public interface ProblemaSaudeRepository extends JpaRepository<ProblemasSaude, Integer> {
}
