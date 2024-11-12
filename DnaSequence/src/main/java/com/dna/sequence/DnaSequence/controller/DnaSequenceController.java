package com.dna.sequence.DnaSequence.controller;

import com.dna.sequence.DnaSequence.dto.DnaDto;
import com.dna.sequence.DnaSequence.service.IDnaSequenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static  com.dna.sequence.DnaSequence.constants.DnaSequenceConstant.*;

@RestController
@RequestMapping("/api/v1")
public class DnaSequenceController {

    private final IDnaSequenceService dnaSequenceService;

    public DnaSequenceController(IDnaSequenceService dnaSequenceService) {
        this.dnaSequenceService = dnaSequenceService;
    }

    @PostMapping("/mutant")
    public ResponseEntity<?> dnaSequence(@RequestBody DnaDto dnaDto){
        boolean isMutant = dnaSequenceService.isMutant(dnaDto.dna());
        dnaSequenceService.saveRecord(dnaDto.dna(), isMutant);

        if (isMutant){
            return new ResponseEntity<>(Map.of(IS_MUTANT, true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of(IS_MUTANT, false), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/stats")
    public ResponseEntity<?> getRecordDnaSequence()  {
        return new ResponseEntity<>(dnaSequenceService.countMutantsAndHumans(), HttpStatus.OK);
    }

}
