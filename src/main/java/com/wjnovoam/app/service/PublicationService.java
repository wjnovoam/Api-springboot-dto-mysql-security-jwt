package com.wjnovoam.app.service;

import com.wjnovoam.app.dto.PublicationDTO;
import com.wjnovoam.app.dto.PublicationResponse;

public interface PublicationService {

    PublicationDTO createPublication(PublicationDTO publicationDTO);

    PublicationResponse getAllPublications(int numberPage, int sizePage, String sortBy, String sortDir);

    PublicationDTO getPublicationsForId(Long id);

    PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id);

    void deletePublicationForId(Long id);
}
