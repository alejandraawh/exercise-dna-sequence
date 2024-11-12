package com.dna.sequence.DnaSequence.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record RecordDnaSequenceDto(
        @JsonProperty("count_mutant_dna") Integer countMutantDna,
        @JsonProperty("count_human_dna") Integer countHumanDna,
        @JsonProperty("ratio") Float ratio
) implements Serializable {
}