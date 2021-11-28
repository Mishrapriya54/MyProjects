package com.priyam.dvd_library.DTO;

import java.time.LocalDate;
import java.util.Date;

public class DvdLibDto {
    private String dvdId;
    private String title;
    private Date releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userRating;

    public DvdLibDto(String dvdId) {
        this.dvdId = dvdId;
    }
    
    public String getDvdId() {
        return dvdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

}
