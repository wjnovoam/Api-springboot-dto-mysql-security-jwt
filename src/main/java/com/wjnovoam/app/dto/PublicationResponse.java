package com.wjnovoam.app.dto;

import java.util.List;

public class PublicationResponse {
    private List<PublicationDTO> content;
    private int numberPage;
    private int numberSize;
    private Long totalElements;
    private int totalPages;
    private boolean lastPage;

    public PublicationResponse() {
    }

    public List<PublicationDTO> getContent() {
        return content;
    }

    public void setContent(List<PublicationDTO> content) {
        this.content = content;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    public int getNumberSize() {
        return numberSize;
    }

    public void setNumberSize(int numberSize) {
        this.numberSize = numberSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
}
