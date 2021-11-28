package com.priyam.dvd_library.controller;

import com.priyam.dvd_library.DTO.DvdLibDto;
import com.priyam.dvd_library.dao.DvdLibDao;
import com.priyam.dvd_library.dao.DvdLibException;
import com.priyam.dvd_library.ui.DvdLibUserView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DvdLibController{
//local private variables for view and dao class    
private DvdLibUserView view ;
private DvdLibDao dao;
//dependency injection
public DvdLibController(DvdLibDao dao, DvdLibUserView view) {
    this.dao = dao;
    this.view = view;
}
//called from main method 
public void execute() {
    boolean keepGoing = true;
    int menuSelection;
    try{
    while (keepGoing) {    
    //calling method to get user choice and save it in menuSelection variable    
    menuSelection = getMenuSelection();
    switch (menuSelection) {
    case 1:addDvd();
           break;
    case 2:removeDvd();
           break;
    case 3:editDvd();
           break;
    case 4:listAllDvd();
           break;
    case 5:displayDvd();
           break;
    case 6:searchDvd();
           break;
    case 7:searchDvdYear();
           break;
    case 8:searchDvdRating();
    break;
    case 9:searchDvdDirector();
    break;
    case 10:searchDvdStudio();
    break;
    case 11: keepGoing = false;
           break;
    default:unknownCommand();
        }
    }
    //calling method for exit.
    exitMessage();
        }catch (DvdLibException | ParseException e) {
        view.displayErrorMessage(e.getMessage());
                                    }
                       }




    //////**************************////////////
    private void addDvd()throws DvdLibException, ParseException {
    boolean keepGoing=true;
    while(keepGoing){
    view.displayCreateDVDBanner();
    DvdLibDto newDvd = view.addNewDvd();
    dao.addDvd(newDvd.getDvdId(), newDvd);
    view.displayCreateSuccessBanner();
    int i= view.repeatAddDvdAction();
       if(i==0){keepGoing=false;}
        }
}
    //////***************************//////////
    private void editDvd()throws DvdLibException, ParseException {
    boolean keepEditing=true;
    String dvdId=view.displayEditDVDBanner();
    DvdLibDto dto = dao.getDvdinfo(dvdId);
    view.displayDVD(dto);
    while(keepEditing){
    int editChoice=view.editDvdInfo();
    switch(editChoice){
        case 1:  String newTitle=view.displayEditTitle();
                 DvdLibDto editedTitle=dao.editDvdFieldTitle(newTitle, dto);
                 view.displayDVD(editedTitle);
                 view.displayEditSuccessBanner();
                 break;
        case 2:  Date newReleasedate=view.displayEditReleaseDate();
                 DvdLibDto editedReleaseDate=dao.editDvdFieldReleaseDate(newReleasedate, dto);
                 view.displayDVD(editedReleaseDate);
                 view.displayEditSuccessBanner();
                 break;
        case 3:  String newMPAARating=view.displayEditMPAARating();
                 DvdLibDto editedRating=dao.editDvdFieldMRating(newMPAARating, dto);
                 view.displayDVD(editedRating);
                 view.displayEditSuccessBanner();
                 break;                                
        case 4:  String directorName=view.displayEditdirectorName();
                 DvdLibDto editedName=dao.editDvdFielddirectorName(directorName, dto);
                 view.displayDVD(editedName);
                 view.displayEditSuccessBanner();
                 break;        
        case 5:  String studio=view.displayEditStudioName();
                 DvdLibDto editedStudio=dao.editDvdFielddirectorName(studio, dto);
                 view.displayDVD(editedStudio);
                 view.displayEditSuccessBanner();
                 break;   
        case 6:  String userRating=view.displayEditUserRating();
                 DvdLibDto editedUserRating=dao.editDvdFieldUserRating(userRating, dto);
                 view.displayDVD(editedUserRating);
                 view.displayEditSuccessBanner();
                 break;   
        case 7:  keepEditing=false;   
                 }
                 }
     }
    
////////********/////////////////////////////////////
    private void removeDvd() throws DvdLibException, ParseException{
    boolean keepRunning=true;
    while(keepRunning){
    view.displayRemoveDVDBanner ();
    String dvdId = view.getDvdIdChoice();
    DvdLibDto removedDvd = dao.removeDvd(dvdId);
    view.displayRemoveResult(removedDvd);
     int i= view.repeatRemoveDvdAction();
       if(i==0){keepRunning=false;}
        }
}
////////************///////////////////////////////
    private void searchDvd()throws DvdLibException, ParseException{
String readTitle=view.getDvdTitleChoice();
view.searchDvdBanner();
DvdLibDto searchTitle=dao.searchDvd(readTitle);
if(searchTitle!=null){view.searchDVD(searchTitle);}
}    
    
////////************//////////////////////////////
private void displayDvd()throws DvdLibException, ParseException{
String dvdId = view.getDvdIdChoice();
view. displayDvdBanner();
DvdLibDto dto = dao.getDvdinfo(dvdId);
        view.displayDVD(dto);
}
/////////***************////////////////////////
private void unknownCommand() {
view.displayUnknownCommandBanner();
}

/////////***************/////////////////////////
private void exitMessage() {
    view.displayExitBanner();
}
//////////*************/////////////////////////    
private int getMenuSelection() {
return view.printMenuAndGetSelection();
}
/////////**************///////////////////////
private void listAllDvd()throws DvdLibException, ParseException{
    view.displayAllBanner();
    List<DvdLibDto> dvdList=dao.getAllDvd();
    view.displayDvdList(dvdList);
    }

    private void searchDvdYear() throws DvdLibException, ParseException{
String year=view.displaySearchYear();

Date parseYear=new SimpleDateFormat("MM/dd/yyyy").parse(year);
List <DvdLibDto> movieYear=dao.getAllDvdYear(parseYear);
view.displayDvdList(movieYear);
}
    


    

    private void searchDvdRating() throws DvdLibException, ParseException {
String rating=view.displaySearchRating();
List<DvdLibDto> ratingList=dao.getAllDvdRating(rating);
view.displayDvdList(ratingList);
    }

    private void searchDvdDirector() throws DvdLibException, ParseException {
    
    String directorName=view.displaySearchDirector();
List<DvdLibDto> directorList=dao.getAllDvdDirector(directorName);
view.displayDvdList(directorList);
    
    }

    private void searchDvdStudio() throws DvdLibException, ParseException {
        
       String studioName=view.displayDvdStudio();
        List<DvdLibDto> studioList=dao.getAllDvdStudio(studioName);
        view.displayDvdList(studioList);

    }
    
    
    
}