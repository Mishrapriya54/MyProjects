package com.priyam.flooringMasteryDao;

import com.priyam.flooringMasteryDto.Orders;
import com.priyam.flooringMasteryDto.Products;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlooringMasteryExportDaoImpl implements FlooringMasteryExportDao {

    private Map<Integer, Orders> orders = new HashMap<>();

    public static final String DELIMITER = ",";

    public static String FOLDERPATH;

    FlooringMasteryExportDaoImpl(String testFile) {
        FOLDERPATH = "C:\\Priya_swguild_project\\Repos\\online-java-2021-Mishrapriya54\\Flooring_Mastery\\TestOrdersFiles\\testExport.txt";
    }

    public FlooringMasteryExportDaoImpl() {
        FOLDERPATH = "Orders";
    }

    @Override
    public void exportData() throws PersistenceException, ParseException {
        loadFile();

    }

    private void loadFile() throws PersistenceException, ParseException {
        File files = new File(FOLDERPATH);
        File Dir[] = files.listFiles();
        //List<String> orderFiles=Arrays.asList(files.list());
        Scanner scanner;
        List<String> list = new ArrayList<String>();

        for (File fileName : Dir) {

            try {
                // Create Scanner for reading the file
                scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
            } catch (FileNotFoundException e) {
                throw new PersistenceException(
                        "-_- Could not load data into memory.", e);
            }
            String strfileName = fileName.toString();
            String splittedNameValue[] = strfileName.split("_");
            String orderFileString = splittedNameValue[1];

            //Date dateNew=orderFileString.
            SimpleDateFormat formatter2 = new SimpleDateFormat("MM-dd-yyyy");

            //   Date date2=formatter2.parse(orderFileString); 
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            String strDate = sdf.format(sdf.parse(orderFileString));
            System.out.println(strDate);

            String currentLine;
            Orders currentOrder;

            while (scanner.hasNextLine()) {

                currentLine = scanner.nextLine();

                String str2 = ",";

                //get the index of str2 in str1
                int indexOfSubStr = currentLine.indexOf(str2);

                String NewcurrentLine = currentLine.substring(indexOfSubStr);
                String updatedLine = NewcurrentLine + "," + strDate;
                list.add(updatedLine);
            }

            // close scanner
            scanner.close();
        }
        writeFile(list);
    }

    private void writeFile(List<String> str) throws PersistenceException {

        try {
            String dirName = "..//Flooring_Mastery//BackUp";
            PrintWriter output = null;
            File folder = new File(dirName);
            String fileName = "DataExport.txt";
            File actualFile = new File(folder, fileName);
            output = new PrintWriter(new FileWriter(actualFile, false));
            output.println("SrNo.,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total,OrderDate");

            // output.print(str);
            int i = 1;
            for (String element : str) {

                output.println(i + element );
                i++;
            }
            output.close();
            output.flush();

        } catch (Exception e) {
            System.out.println("Could not create file");
        }
        System.out.println("File has been written for above date orders.");

    }

}
