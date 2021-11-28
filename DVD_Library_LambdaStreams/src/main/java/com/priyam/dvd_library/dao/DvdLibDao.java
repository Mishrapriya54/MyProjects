package com.priyam.dvd_library.dao;

import com.priyam.dvd_library.DTO.DvdLibDto;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface DvdLibDao {

    public DvdLibDto addDvd(String dvdId, DvdLibDto dto) throws DvdLibException,ParseException;

    public DvdLibDto removeDvd(String dvdId) throws DvdLibException,ParseException;

    //public DvdLibDto editDvd(String dvdId) throws DvdLibException;

    List<DvdLibDto> getAllDvd() throws DvdLibException,ParseException;

    public DvdLibDto getDvdinfo(String title) throws DvdLibException,ParseException;

    public DvdLibDto searchDvd(String title) throws DvdLibException,ParseException;

    public DvdLibDto editDvdFieldTitle(String title, DvdLibDto dto) throws DvdLibException,ParseException;

    public DvdLibDto editDvdFieldReleaseDate(Date releaseDate, DvdLibDto dto) throws DvdLibException,ParseException;

    public DvdLibDto editDvdFieldMRating(String rating, DvdLibDto dto) throws DvdLibException,ParseException;

    public DvdLibDto editDvdFielddirectorName(String name, DvdLibDto dto) throws DvdLibException,ParseException;

    public DvdLibDto editDvdFieldStudioName(String studio, DvdLibDto dto) throws DvdLibException,ParseException;

    public DvdLibDto editDvdFieldUserRating(String rating, DvdLibDto dto) throws DvdLibException,ParseException;
    
   List<DvdLibDto> getAllDvdYear(Date year) throws DvdLibException,ParseException;
    
    List<DvdLibDto> getAllDvdRating(String mpaaRating) throws DvdLibException,ParseException;
    List<DvdLibDto> getAllDvdDirector(String directorName) throws DvdLibException,ParseException;
    List<DvdLibDto> getAllDvdStudio(String studio) throws DvdLibException,ParseException;
    public DvdLibDto getAverageMovieAge() throws DvdLibException;
    public DvdLibDto getOldNewMovie(DvdLibDto dto) throws DvdLibException;

    
}
