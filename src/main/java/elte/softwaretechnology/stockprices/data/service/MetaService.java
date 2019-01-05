package elte.softwaretechnology.stockprices.data.service;

import elte.softwaretechnology.stockprices.data.model.KeyWord;
import elte.softwaretechnology.stockprices.data.model.Meta;
import elte.softwaretechnology.stockprices.data.repository.KeyWordRepository;
import elte.softwaretechnology.stockprices.data.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class MetaService {
    @Autowired
    private MetaRepository repository;

    public Meta save(Meta meta) {
        return repository.save(meta);
    }
}
