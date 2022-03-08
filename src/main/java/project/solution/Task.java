package project.solution;

import java.util.ArrayList;

public class Task {

    void TimeFunction(int time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    ArrayList<Object> DataLoad(String filename){
        ArrayList<ArrayList<String>>data =new ReadCsv().readCsv();
        ArrayList<Object> result=new ArrayList<>();
        result.add(data);
        result.add(data.size());
        return result;
    }
}
