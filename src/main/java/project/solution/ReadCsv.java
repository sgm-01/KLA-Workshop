package project.solution;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
//import org.apache.commons.*;


public class ReadCsv {
    public ArrayList<ArrayList<String>> readCsv() {
        ArrayList<ArrayList<String>> data=new ArrayList<>();
        try {

            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader("C:\\Users\\gopika\\Desktop\\KLA\\DataSet\\Milestone2\\Milestone2A_DataInput1.csv");

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;


            String s="1";
            Integer n=Integer.parseInt(s);
            //System.out.println(n);
            // we are going to read data line by line
            int i=0;
            while ((nextRecord = csvReader.readNext()) != null) {
                data.add(new ArrayList<>());
                for (String cell : nextRecord) {

                    data.get(i).add(cell);
                }
                i++;
                //System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<data.size();i++){
            for(int j=0;j<data.get(i).size();j++){
                System.out.print(data.get(i).get(j)+",");
            }
            System.out.println();
        }
        return data;
    }



}