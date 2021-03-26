package com.kodilla.library.mapper;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.ReaderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ReaderMapperTestSuite {

    @Autowired
    private ReaderMapper readerMapper;

    @Test
    public void testReaderMapToReaderDtoList() {
        //Given
        List<Reader> readers = List.of(new Reader(1L, "Test_FirstName", "Test_LastName"));
        //When
        List<ReaderDto> readerDtoList = readerMapper.mapToReaderDtoList(readers);
        //Then
        assertEquals(readers.size(), readerDtoList.size());
        assertEquals(readerDtoList.get(0).getFirstName(), readers.get(0).getFirstName());
        assertEquals(readers.get(0).getId(), readerDtoList.get(0).getId());
        assertEquals(readers.get(0).getLastName(), readerDtoList.get(0).getLastName());
    }
}
