package com.kodilla.library.controller;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.ReaderDto;
import com.kodilla.library.exception.ReaderNotFoundException;
import com.kodilla.library.mapper.ReaderMapper;
import com.kodilla.library.services.ReaderDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reader")
public class ReaderController {

    private final ReaderDbService readerService;
    private final ReaderMapper readerMapper;

    @Autowired
    public ReaderController(ReaderDbService readerService, ReaderMapper readerMapper) {
        this.readerService = readerService;
        this.readerMapper = readerMapper;
    }

    @GetMapping("/getReaders")
    public List<ReaderDto> getAllReaders() {
        List<Reader> readers = readerService.getAllReaders();
        return readerMapper.mapToReaderDtoList(readers);
    }

    @GetMapping("/getReader/{id}")
    public ReaderDto getReader(@PathVariable Long id) {
        return readerMapper.mapToReaderDto(readerService.getReader(id));
    }

    @PostMapping(value = "/createReader", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        readerService.saveReader(reader);
    }

    @PutMapping("/updateReader")
    public ReaderDto updateReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        Reader saveReader = readerService.saveReader(reader);
        return readerMapper.mapToReaderDto(saveReader);
    }

    @DeleteMapping("/deleteReader/{id}")
    public void deleteReader(@PathVariable Long id) {
        try {
            readerService.deleteReader(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ReaderNotFoundException("Reader with pointed ID does not exist");
        }
    }

}
