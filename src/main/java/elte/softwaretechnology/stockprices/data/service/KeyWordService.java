package elte.softwaretechnology.stockprices.data.service;

import elte.softwaretechnology.stockprices.data.model.KeyWord;
import elte.softwaretechnology.stockprices.data.repository.KeyWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class KeyWordService {
	@Autowired
	private KeyWordRepository repository;

	public KeyWord save(KeyWord keyWord) {
		return repository.save(keyWord);
	}
}
