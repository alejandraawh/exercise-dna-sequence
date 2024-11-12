package com.dna.sequence.DnaSequence.service;

import com.dna.sequence.DnaSequence.dto.RecordDnaSequenceDto;

public interface IDnaSequenceService {

    public boolean isMutant(String [] dna);
    public void saveRecord(String[]dna, boolean isMutant);
    public RecordDnaSequenceDto countMutantsAndHumans();

}
