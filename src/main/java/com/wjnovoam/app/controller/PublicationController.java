package com.wjnovoam.app.controller;

import com.wjnovoam.app.dto.PublicationDTO;
import com.wjnovoam.app.dto.PublicationResponse;
import com.wjnovoam.app.service.PublicationService;
import com.wjnovoam.app.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @GetMapping
    public ResponseEntity<PublicationResponse> listPublications(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.NUMBER_OF_PAGE_FOR_DEFAULT, required = false) int numberPage,
            @RequestParam(value = "sizePage", defaultValue = AppConstants.SIZE_OF_PAGE_FOR_DEFAULT, required = false) int sizePage,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_FOR_DEFAULT, required = false) String sortBy, //Ordenar por un campo
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIRECTION_FOR_DEFAULT, required = false) String sortDir) {//Ordenar Ascendente o descendente

        return new ResponseEntity<>(publicationService.getAllPublications(numberPage, sizePage, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> getPublicationForId(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(publicationService.getPublicationsForId(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')") //Se indica que aca solo va poder ejecutar aquel usuario que tenga rol administrador
    @PostMapping
    public ResponseEntity<PublicationDTO> savePublication(@Valid @RequestBody PublicationDTO publicationDTO) {
        return new ResponseEntity<>(publicationService.createPublication(publicationDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PublicationDTO> updatePublication(@Valid @PathVariable(name = "id") Long id, @RequestBody PublicationDTO publicationDTO) {
        return new ResponseEntity<>(publicationService.updatePublication(publicationDTO, id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublication(@PathVariable(name = "id") Long id) {
        publicationService.deletePublicationForId(id);
        return new ResponseEntity<>("Publicacion Eliminada con exito", HttpStatus.OK);
    }
}
