package com.kodilla.library.services;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.ReaderDto;
import com.kodilla.library.exception.ReaderNotFoundException;
import com.kodilla.library.mapper.ReaderMapper;
import com.kodilla.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderDbService {

    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    @Autowired
    public ReaderDbService(ReaderRepository readerRepository, ReaderMapper readerMapper) {
        this.readerRepository = readerRepository;
        this.readerMapper = readerMapper;
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getReader(Long id) throws ReaderNotFoundException {
        return readerRepository.findById(id).orElseThrow(() -> new ReaderNotFoundException("Reader with pointed ID does not exist"));
    }

    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public Reader updateReader(ReaderDto readerDto) throws ReaderNotFoundException {
        readerRepository.findById(readerDto.getId()).orElseThrow(() -> new ReaderNotFoundException("Reader with pointed ID does not exist"));
        return readerRepository.save(readerMapper.mapToReader(readerDto));
    }

    public void deleteReader(Long id) {
        try {
            readerRepository.deleteById(id);
        } catch (ReaderNotFoundException e) {
            throw new ReaderNotFoundException("Reader with pointed ID does not exist");
        }

    }
}
