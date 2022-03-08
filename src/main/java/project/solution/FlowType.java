package project.solution;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FlowType {
    String Execution;
    LinkedHashMap<String, Object> Activities;
    String path;

    FlowType(LinkedHashMap<String, Object> flow, String path) {
        //System.out.println("flowtype");
        this.path = path;
        Execution = (String) flow.get("Execution");
        Activities = (LinkedHashMap<String, Object>) flow.get("Activities");


        Set<String> ks = Activities.keySet();
        if (Execution.equals("Sequential")) {
            for (String s : ks) {
                //System.out.println((LinkedHashMap<String,Object>)Activities.get(s));
                new WorkFlow((LinkedHashMap<String, Object>) Activities.get(s), path + "." + s);

            }
        } else {
            ExecutorService executor = Executors.newFixedThreadPool(ks.size());
            List<Callable<Void>> tasks = new ArrayList<>();
            for (String s : ks) {
                tasks.add(() -> {
                    new WorkFlow((LinkedHashMap<String, Object>) Activities.get(s), path + "." + s);
                    return null;
                });
            }

            try {
                executor.invokeAll(tasks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executor.shutdown();
        }

    }
}
