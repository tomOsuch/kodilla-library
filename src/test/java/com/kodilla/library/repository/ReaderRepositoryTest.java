package com.kodilla.library.repository;

import com.kodilla.library.domain.Reader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReaderRepositoryTest {

    @Autowired
    private ReaderRepository readerRepository;

    @Test
    public void testReaderFindAll() {
        //Given
        Reader reader = new Reader("Jan", "Kowalski", new Date());
        readerRepository.save(reader);
        //When
        List<Reader> readers = readerRepository.findAll();
        //Then
        assertEquals(1, readers.size());
        //CleanUp
        readerRepository.deleteAll();
    }

    @Test
    public void testReaderFindById() {
        //Given
        Reader reader = new Reader("Jan", "Kowalski", new Date());
        readerRepository.save(reader);
        Long id = reader.getId();
        //When
        Optional<Reader> resultFindReader = readerRepository.findById(id);
        //Then
        assertTrue(resultFindReader.isPresent());
        //CleanUp
        readerRepository.deleteAll();
    }

    @Test
    public void testReaderDeleteById() {
        //Given
        Reader reader = new Reader("Jan", "Kowalski", new Date());
        readerRepository.save(reader);
        Long id = reader.getId();
        //When
        readerRepository.deleteById(id);
        Optional<Reader> findReader = readerRepository.findById(id);
        //Then
        assertFalse(findReader.isPresent());
        //CleanUp
        readerRepository.deleteAll();
    }
}
