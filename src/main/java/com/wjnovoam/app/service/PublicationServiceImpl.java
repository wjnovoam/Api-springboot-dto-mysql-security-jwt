package com.wjnovoam.app.service;

import com.wjnovoam.app.dto.PublicationDTO;
import com.wjnovoam.app.dto.PublicationResponse;
import com.wjnovoam.app.entitys.Publication;
import com.wjnovoam.app.exceptions.ResourceNotFountException;
import com.wjnovoam.app.repository.PublicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public PublicationDTO createPublication(PublicationDTO publicationDTO) {

        Publication publication = mapearEntity(publicationDTO);
        Publication newPublication = publicationRepository.save(publication);
        PublicationDTO publicationResponse  = mapearDTO(newPublication);

        return publicationResponse;
    }

    @Override
    public PublicationResponse getAllPublications(int numberPage, int sizePage, String sortBy, String sortDir) {

        //Sort - para ordenar
        //Si el valor que le estoy pasando es igual a la direccion ascendente
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                    ?Sort.by(sortBy).ascending()
                    :Sort.by(sortBy).descending();

        //Creando paginacion de numeros de paginas a tamaño de pagina y ordenar por un campo
        Pageable pageable = PageRequest.of(numberPage, sizePage, sort);

        /**Paginancion numeroPagina, TamañoPagina y Ordenar Por Atributo
         * Pageable pageable = PageRequest.of(numberPage, sizePage, Sort.by(sortBy));**/

        Page<Publication> publications = publicationRepository.findAll(pageable);

        List<Publication> listPublications  = publications.getContent();


        List<PublicationDTO> content = listPublications.stream().map(publication -> mapearDTO(publication))
                .collect(Collectors.toList());

        PublicationResponse publicationResponse = new PublicationResponse();
        publicationResponse.setContent(content);
        publicationResponse.setNumberPage(publications.getNumber());
        publicationResponse.setNumberSize(publications.getSize());
        publicationResponse.setTotalElements(publications.getTotalElements());
        publicationResponse.setTotalPages(publications.getTotalPages());
        publicationResponse.setLastPage(publications.isLast());

        return publicationResponse;

    }

    @Override
    public PublicationDTO getPublicationsForId(Long id) {

        Publication publication = publicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFountException("Publication", "id", id));

        return mapearDTO(publication);
    }

    @Override
    public PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id) {

        Publication publication = publicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFountException("Publication", "id", id));

        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());

        Publication publicationUpdate = publicationRepository.save(publication);

        return mapearDTO(publicationUpdate);
    }

    @Override
    public void deletePublicationForId(Long id) {

        Publication publication = publicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFountException("Publication", "id", id));

        publicationRepository.delete(publication);
    }

    //Convertir Entidad a DTO
    private PublicationDTO mapearDTO(Publication publication){
        PublicationDTO publicationDTO  = modelMapper.map(publication, PublicationDTO.class);
        return publicationDTO;
    }

    //Convertir DTO a Entidad
    private Publication mapearEntity(PublicationDTO publicationDTO){
        Publication publication  = modelMapper.map(publicationDTO, Publication.class);
        return publication;
    }
}
