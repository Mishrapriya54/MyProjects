package com.priyam.dvd_library.dao;

import com.priyam.dvd_library.DTO.DvdLibDto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
public class DvdLibDaoImpl implements DvdLibDao {
    public static final String DVDLIBFILE = "DvdLibFile.txt";
    public static final String DELIMITER = "::";
    private Map<String, DvdLibDto> dvd = new HashMap<>();

    @Override
    public DvdLibDto addDvd(String dvdId, DvdLibDto dto)throws DvdLibException, ParseException {
        loadFile();
        DvdLibDto prevDvd = dvd.put(dvdId, dto);
        writeFile();
        return prevDvd;
    }

    @Override
    public DvdLibDto removeDvd(String dvdId) throws DvdLibException,ParseException {
        loadFile();
        DvdLibDto removeDvd = dvd.remove(dvdId);
        writeFile();
        return removeDvd;
    }

    @Override
    public List<DvdLibDto> getAllDvd() throws DvdLibException,ParseException {
        loadFile();
        return new ArrayList<>(dvd.values());
    }

    @Override
    public DvdLibDto getDvdinfo(String dvdId) throws DvdLibException,ParseException {
        loadFile();
        return dvd.get(dvdId);//have to chk before that it was dvd.get(dvdId)
    }

    @Override
    public DvdLibDto searchDvd(String title) throws DvdLibException,ParseException {
        loadFile();

        for (DvdLibDto lo : dvd.values()) {
            if (title.trim().equalsIgnoreCase(lo.getTitle())) {
                return lo;
            }
        }
        return null;
    }

    @Override
    public DvdLibDto editDvdFieldTitle(String id, DvdLibDto dto) throws DvdLibException,ParseException {
        loadFile();
        dto.setTitle(id);
        dvd.put(dto.getDvdId(), dto);
        writeFile();
        return dto;
    }

    @Override
    public DvdLibDto editDvdFieldReleaseDate(Date releaseDate, DvdLibDto dto) throws DvdLibException,ParseException {
        loadFile();
        dto.setReleaseDate(releaseDate);
        dvd.put(dto.getDvdId(), dto);
        writeFile();
        return dto;
    }

    @Override
    public DvdLibDto editDvdFieldMRating(String rating, DvdLibDto dto) throws DvdLibException,ParseException {
        loadFile();
        dto.setMpaaRating(rating);
        dvd.put(dto.getDvdId(), dto);
        writeFile();
        return dto;
    }

    @Override
    public DvdLibDto editDvdFielddirectorName(String name, DvdLibDto dto) throws DvdLibException,ParseException {
        loadFile();
        dto.setDirectorName(name);
        dvd.put(dto.getDvdId(), dto);
        writeFile();
        return dto;
    }

    @Override
    public DvdLibDto editDvdFieldStudioName(String studio, DvdLibDto dto) throws DvdLibException,ParseException {
        loadFile();
        dto.setStudio(studio);
        dvd.put(dto.getDvdId(), dto);
        writeFile();
        return dto;
    }

    @Override
    public DvdLibDto editDvdFieldUserRating(String rating, DvdLibDto dto) throws DvdLibException,ParseException {
        loadFile();
        dto.setUserRating(rating);
        dvd.put(dto.getDvdId(), dto);
        writeFile();
        return dto;
    }

    private DvdLibDto unmarshallMethod(String dvdAsText) throws ParseException{
        String[] dvdFields = dvdAsText.split(DELIMITER);
        String dvdId = dvdFields[0];
        DvdLibDto dvdFromFile = new DvdLibDto(dvdId);
        dvdFromFile.setTitle(dvdFields[1]);
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        
        Date date = formatter.parse(dvdFields[2]);
        
        
        
        
        dvdFromFile.setReleaseDate(date);
        dvdFromFile.setMpaaRating(dvdFields[3]);
        dvdFromFile.setDirectorName(dvdFields[4]);
        dvdFromFile.setStudio(dvdFields[5]);
        dvdFromFile.setUserRating(dvdFields[6]);
        return dvdFromFile;
    }
    
    private void loadFile() throws DvdLibException, ParseException {
        Scanner scanner;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVDLIBFILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibException(
                    "-_- Could not load file data into memory.", e);
        
        }
        String currentLine;
        DvdLibDto currentDvd;
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a dvd.
            currentDvd = unmarshallMethod(currentLine);
            // use the dvd id as the map key for dvd object.
            // Put currentdvd into the map using dvd id as the key
            dvd.put(currentDvd.getDvdId(), currentDvd);
        }
        // close scanner
        scanner.close();
    }

    private String marshallMethod(DvdLibDto aDvd) {
        String dvdAsText = aDvd.getDvdId() + DELIMITER;
        dvdAsText += aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        dvdAsText += aDvd.getMpaaRating() + DELIMITER;
        dvdAsText += aDvd.getDirectorName() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getUserRating();
        return dvdAsText;
    }

    private void writeFile() throws DvdLibException, ParseException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(DVDLIBFILE));
        } catch (IOException e) {
            throw new DvdLibException(
                    "Could not save DVD data.", e);
        }
        String dvdAsText;
        List<DvdLibDto> dvdList = this.getAllDvd();
        for (DvdLibDto currentDvd : dvdList) {
            // turn a dvd into a String
            dvdAsText = marshallMethod(currentDvd);
            // write the dvd object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public List<DvdLibDto> getAllDvdYear(Date year) throws DvdLibException,ParseException {
 loadFile();
       List<DvdLibDto> allDvds=new ArrayList<>(dvd.values());
     allDvds=allDvds.stream().filter(d->{
     return d.getReleaseDate().before(year);
 }).collect(Collectors.toList());
        return allDvds;
        }
    

    @Override
    public List<DvdLibDto> getAllDvdRating(String mpaaRating) throws DvdLibException,ParseException {
       loadFile();
       ///////////////check***
               List<DvdLibDto> allDvds=new ArrayList<>(dvd.values());
                allDvds=allDvds.stream()
                .filter(d -> d.getMpaaRating().trim().equalsIgnoreCase(mpaaRating))
                .collect(Collectors.toList());
               
              return allDvds;
    }
@Override
    public List<DvdLibDto> getAllDvdDirector(String directorName) throws DvdLibException,ParseException {
      
         loadFile();
       ///////////////check***
      Comparator <DvdLibDto> comparator=Comparator.comparing(DvdLibDto::getMpaaRating);
               List<DvdLibDto> allDvds=new ArrayList<>(dvd.values());
                allDvds=allDvds.stream()
                .filter(d -> d.getDirectorName().trim().equalsIgnoreCase(directorName)).sorted(comparator)
                .collect(Collectors.toList());
               
              return allDvds;
        
    }
     @Override
    public List <DvdLibDto> getAllDvdStudio(String studio) throws DvdLibException,ParseException {
loadFile();

  List<DvdLibDto> allDvds=new ArrayList<>(dvd.values());
                allDvds=allDvds.stream()
                .filter(d -> d.getStudio().trim().equalsIgnoreCase(studio))
                .collect(Collectors.toList());
               
              return allDvds;


    }

    @Override
    public DvdLibDto getAverageMovieAge() throws DvdLibException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DvdLibDto getOldNewMovie(DvdLibDto dto) throws DvdLibException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
