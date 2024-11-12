package com.dna.sequence.DnaSequence.service.impl;

import com.dna.sequence.DnaSequence.dto.RecordDnaSequenceDto;
import com.dna.sequence.DnaSequence.model.RecordDna;
import com.dna.sequence.DnaSequence.repository.IDnaSequenceRepository;
import com.dna.sequence.DnaSequence.service.IDnaSequenceService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.dna.sequence.DnaSequence.constants.DnaSequenceConstant.*;

@Service
public class DnaSequenceServiceImpl implements IDnaSequenceService {

    private final IDnaSequenceRepository dnaSequenceRepository;

    public DnaSequenceServiceImpl(IDnaSequenceRepository dnaSequenceRepository) {
        this.dnaSequenceRepository = dnaSequenceRepository;
    }

    @Override
    public boolean isMutant(String[] dna){
        validateDnaSequence(dna);
        char[][] matrix = convertToMatrix(dna);

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = matrix[r][c];
                if (c + 3 < cols && ch == matrix[r][c+1] && ch == matrix[r][c+2] && ch == matrix[r][c+3]) {
                    return true;
                }
                if (r + 3 < rows && ch == matrix[r+1][c] && ch == matrix[r+2][c] && ch == matrix[r+3][c]) {
                    return true;
                }
                if (r + 3 < rows && c + 3 < cols && ch == matrix[r+1][c+1] && ch == matrix[r+2][c+2] && ch == matrix[r+3][c+3]) {
                    return true;
                }
                if (r + 3 < rows && c - 3 >= 0 && ch == matrix[r+1][c-1] && ch == matrix[r+2][c-2] && ch == matrix[r+3][c-3]) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void saveRecord(String[] dna, boolean isMutant) {
        RecordDna record = new RecordDna();
        record.setDnaSequence(Arrays.toString(dna));

        if(isMutant){
            record.setMutant(true);
            record.setHuman(false);
        } else {
            record.setMutant(false);
            record.setHuman(true);
        }

        dnaSequenceRepository.save(record);
    }

    @Override
    public RecordDnaSequenceDto countMutantsAndHumans() {

        Long countMutantDna = dnaSequenceRepository.countMutantDna();
        Long countHumanDna = dnaSequenceRepository.countHumanDna();

        float ratio = 0.0f;
        if (countHumanDna != 0) {
            ratio = (float) countMutantDna / countHumanDna;
        }

        return new RecordDnaSequenceDto(Math.toIntExact(countMutantDna), Math.toIntExact(countHumanDna), ratio);
    }

    private static char[][] convertToMatrix(String[] dna) {
        int size = dna.length;
        char[][] matrix = new char[size][size];

        for (int i = 0; i < size; i++) {
            matrix[i] = dna[i].toCharArray();
        }
        return matrix;
    }

    private static void validateDnaSequence(String[] dna){
        Arrays.stream(dna)
            .flatMapToInt(s -> s.chars().map(c -> Character.toUpperCase((char) c)))
            .forEach(c -> {
                if (!isValidDnaChar((char) c)) {
                    throw new IllegalArgumentException("DNA sequence invalid. character not allowed: " + (char) c);
                }
            });
    }

    private static boolean isValidDnaChar(char c) {
        return Arrays.stream(DNA_BASE)
                .anyMatch(validChar -> validChar.equalsIgnoreCase(Character.toString(c)));
    }

}
