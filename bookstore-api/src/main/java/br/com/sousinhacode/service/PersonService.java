package br.com.sousinhacode.service;

import br.com.sousinhacode.dto.PersonDTO;
import br.com.sousinhacode.exception.ResourceNotFoundException;
import br.com.sousinhacode.model.Person;
import br.com.sousinhacode.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.sousinhacode.mapper.ObjectMapper.parseListObject;
import static br.com.sousinhacode.mapper.ObjectMapper.parseObject;

@Service
public class PersonService {

    private Logger LOGGER = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;

    public List<PersonDTO> findAll() {
        LOGGER.info("Finding all People!");

        return parseListObject(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        LOGGER.info("Finding one Person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        LOGGER.info("Creating one Person!");

        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        LOGGER.info("Updating one Person!");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        LOGGER.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}