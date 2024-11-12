package com.dna.sequence.DnaSequence.repository;

import com.dna.sequence.DnaSequence.model.RecordDna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDnaSequenceRepository extends JpaRepository<RecordDna, Integer> {

    @Query("SELECT COUNT(r) FROM RecordDna r WHERE r.isMutant = true")
    Long countMutantDna();

    @Query("SELECT COUNT(r) FROM RecordDna r WHERE r.isHuman = true")
    Long countHumanDna();

}
