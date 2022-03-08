package project.solution;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FlowType {
    FlowType(LinkedHashMap<String, Object> flow, String path) {
        String execution = (String) flow.get("Execution");
        LinkedHashMap<String, Object> activities = (LinkedHashMap<String, Object>) flow.get("Activities");

        Set<String> ks = activities.keySet();
        if (execution.equals("Sequential")) {
            for (String s : ks) {
                new WorkFlow((LinkedHashMap<String, Object>) activities.get(s), path + "." + s);
            }
        } else {
            ExecutorService executor = Executors.newFixedThreadPool(ks.size());
            List<Callable<Void>> tasks = new ArrayList<>();
            for (String s : ks) {
                tasks.add(() -> {
                    new WorkFlow((LinkedHashMap<String, Object>) activities.get(s), path + "." + s);
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
