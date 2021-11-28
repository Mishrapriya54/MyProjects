package com.priyam.dvd_library.ui;

import com.priyam.dvd_library.DTO.DvdLibDto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DvdLibUserView {
 private DvdLibUserIO io;

    public DvdLibUserView(DvdLibUserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Please select the operation:");
        io.print("1.Add new DVD");
        io.print("2.Delete DVD");
        io.print("3.Edit DVD information");
        io.print("4.List All DVDs");
        io.print("5.Display Information of a DVD");
        io.print("6.Search DVD by title");
        io.print("7.Find All Dvds released until an Year");
        io.print("8.Find Dvds by Rating.");
        io.print("9.Find Dvds by Director.");
        io.print("10.Find Dvds by Studio Name");
        io.print("11.Exit");
     
        return io.readInt("Please select from the above choices.", 1, 11);
    }
    public DvdLibDto addNewDvd() throws ParseException {
        String dvdId = io.readString("Please enter Dvd ID");
        String title = io.readString("Please enter Dvd Title");
        String releaseDate = io.readString("Please enter release Date: in mm-dd-yy");
        //Getting releaseDate in String variable from user and parsing it to Date variable. 
        Date date=new SimpleDateFormat("MM/dd/yyyy").parse(releaseDate);
        String mpaaRating = io.readString("Please enter Rating");
        String directorName = io.readString("Please enter Director Name");
        String studio = io.readString("Please enter Studio name");
        String userRating = io.readString("Please enter User Rating or comment");
        DvdLibDto newDvd = new DvdLibDto(dvdId);
        newDvd.setTitle(title);
        newDvd.setReleaseDate(date);
        newDvd.setMpaaRating(mpaaRating);
        newDvd.setDirectorName(directorName);
        newDvd.setStudio(studio);
        newDvd.setUserRating(userRating);
        return newDvd;
    }
    
    public String displayDVD(DvdLibDto dto) {
        String prevTitle = "";
        if (dto != null) {
            io.print(dto.getDvdId());
            io.print(dto.getTitle());
            ///printDate method in the UserIo
            io.printDate(dto.getReleaseDate());
            io.print(dto.getMpaaRating());
            io.print(dto.getDirectorName());
            io.print(dto.getStudio());
            io.print(dto.getUserRating());
            io.print("");
            prevTitle = dto.getTitle();
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.\n");
        return prevTitle;
    }
    
    public void searchDVD(DvdLibDto searchTitle) {
        if (searchTitle != null) {
            io.print(searchTitle.getDvdId());
            io.print(searchTitle.getTitle());
            io.printDate(searchTitle.getReleaseDate());
            io.print(searchTitle.getMpaaRating());
            io.print(searchTitle.getDirectorName());
            io.print(searchTitle.getStudio());
            io.print(searchTitle.getUserRating());
            io.print("");
        } else {
            io.print("Sorry!!No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
     
    public void displayDvdList(List<DvdLibDto> dvdList) {
        if(dvdList.isEmpty()==true){ io.print("Sorry!!No such DVD.");}
        else{
        for (DvdLibDto newDvd : dvdList) {
            String dvdInfo = String.format("#%s : %s %s %s %s %s %s",newDvd.getDvdId(),newDvd.getTitle(),newDvd.getReleaseDate(),newDvd.getMpaaRating(),newDvd.getDirectorName(),newDvd.getStudio(),newDvd.getUserRating());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    }
    public int editDvdInfo() {
        {   io.print("Please select what you want to edit:");
            io.print("1.Title");
            io.print("2.Release date");
            io.print("3.MPAA rating");
            io.print("4.Director's name");
            io.print("5.Studio");
            io.print("6.User rating or note");
            io.print("7.Done Editing");
            int choice = io.readInt("Please select from the above choices.", 1, 7);
            return choice;
        }
    }
    
/////////////////////////////Display Banner Methods//////////////////////////////////////////////
    public String displayEditTitle() {
        String newTitle = io.readString("Enter New Title");
        return newTitle;
    }

    public Date displayEditReleaseDate() {
        Date newDate = io.readDate("Enter New Release date");
        return newDate;
    }

    public String displayEditMPAARating() {
        String newMRating = io.readString("Enter New Rating");
        return newMRating;
    }

    public String displayEditdirectorName() {
        String newdirectorName = io.readString("Enter New Director Name");
        return newdirectorName;
    }

    public String displayEditStudioName() {
        String newdirectorName = io.readString("Enter New Studio Name");
        return newdirectorName;
    }

    public String displayEditUserRating() {
        String newdirectorName = io.readString("Enter New Comment/Rating");
        return newdirectorName;
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD information successfully added.Press Enter to continue.");
    }

    public void displayEditSuccessBanner() {
        io.readString(
                "New info edited successfully.\n");
    }

    public void displayAllBanner() {
        io.print("===Display All DVDs");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }

    public String displayDvdBanner() {
        return io.readString("===Display DVD===");
    }

    public String getDvdIdChoice() {
        return io.readString("Please enter the DVD ID.");
    }

    public String searchDvdBanner() {
        return io.readString("Searching...\n");
    }

    public String displayEditDVDBanner() {
        return io.readString("Please Enter the DVD Id to edit the information");
    }

    public String getDvdTitleChoice() {
        return io.readString("Please enter the DVD Title");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayCreateDVDBanner() {
        io.print("=== Add new DVD ===");
    }
    
    public String displayDvdStudio() {
        String studio=io.readString("Enter Studio Name");
        return studio;
    }
    
    public String displaySearchYear(){
    String year=io.readString("Please Enter the year in MM-dd-yyyy");
    return year;
    }

    public int repeatAddDvdAction() {
        int i = io.readInt("Press any number to Add another DVD info or Press 0 to return to the Main Menu.");
        return i;
    }

    public int repeatRemoveDvdAction() {
        int i = io.readInt("Press any number to Remove another DVD info or Press 0 to return to the Main Menu.");
        return i;
    }

    public int repeatEditDvdAction() {
        int i = io.readInt("Press any number to Edit another DVD info or Press 0 to return to the Main Menu.");
        return i;
    }
    public void displayRemoveResult(DvdLibDto dvdRecord) {
        if (dvdRecord != null) {
            io.print("DVD successfully removed.");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    public String displaySearchRating(){
    String rating=io.readString("Please Enter the Rating");
    return rating;
    
}
     public String displaySearchDirector(){
    String director=io.readString("Please Enter the Director Name");
    return director;

}
}