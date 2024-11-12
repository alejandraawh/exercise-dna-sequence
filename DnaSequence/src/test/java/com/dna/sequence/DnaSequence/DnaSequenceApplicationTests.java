package com.dna.sequence.DnaSequence;

import com.dna.sequence.DnaSequence.dto.RecordDnaSequenceDto;
import com.dna.sequence.DnaSequence.model.RecordDna;
import com.dna.sequence.DnaSequence.repository.IDnaSequenceRepository;
import com.dna.sequence.DnaSequence.service.impl.DnaSequenceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DnaSequenceApplicationTests {

	@Mock
	private IDnaSequenceRepository dnaSequenceRepository;

	@InjectMocks
	private DnaSequenceServiceImpl dnaSequenceService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testIsMutant_whenMutant() {
		String[] dna = {
				"ATGCGA",
				"CAGTGC",
				"TTATGT",
				"AGAAGG",
				"CCCCTA",
				"TCACTG"
		};

		boolean result = dnaSequenceService.isMutant(dna);
		assertTrue(result, "The DNA sequence should be from a mutant.");
	}

	@Test
	void testIsMutant_whenNotMutant() {
		String[] dna = {
				"ATGCGA",
				"CAGTGC",
				"TTATGT",
				"AGAGCG",
				"CCCTTA",
				"TCACTG"
		};

		boolean result = dnaSequenceService.isMutant(dna);
		assertFalse(result, "The DNA sequence should be from a human.");
	}

	@Test
	void testSaveRecord_whenMutant() {
		RecordDna record = new RecordDna();
		String[] dna = {
				"ATGCGA",
				"CAGTGC",
				"TTATGT",
				"AGAAGG",
				"CCCCTA",
				"TCACTG"
		};

		record.setDnaSequence(Arrays.toString(dna));
		record.setMutant(true);
		record.setHuman(false);

		dnaSequenceRepository.save(record);
		verify(dnaSequenceRepository, times(1)).save(any(RecordDna.class));
	}

	@Test
	void testSaveRecord_whenHuman() {
		RecordDna record = new RecordDna();
		String[] dna = {
				"ATGCGA",
				"CAGTGC",
				"TTATGT",
				"AGAAGG",
				"CCCCTA",
				"TCACTG"
		};

		record.setDnaSequence(Arrays.toString(dna));
		record.setMutant(false);
		record.setHuman(true);

		dnaSequenceRepository.save(record);
		verify(dnaSequenceRepository, times(1)).save(any(RecordDna.class));
	}

	@Test
	void testCountMutantsAndHumans() {
		when(dnaSequenceRepository.countMutantDna()).thenReturn(5L);
		when(dnaSequenceRepository.countHumanDna()).thenReturn(10L);


		RecordDnaSequenceDto result = dnaSequenceService.countMutantsAndHumans();

		assertEquals(5, result.countMutantDna(), "Mutant count should be 5");
		assertEquals(10, result.countHumanDna(), "Human count should be 10");
		assertEquals(0.5f, result.ratio(), "The ratio should be 0.5");
	}
}
