package br.com.sousinhacode.file.importer.contract;

import br.com.sousinhacode.dto.PersonDTO;

import java.io.InputStream;
import java.util.List;

public interface FileImporter {

    List<PersonDTO> importFile(InputStream inputStream) throws Exception;

}
