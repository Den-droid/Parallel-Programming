package org.example.word_common;

import org.example.file_entities.Document;
import org.example.file_entities.Folder;

import java.util.*;
import java.util.concurrent.RecursiveTask;

class FolderSearchTask extends RecursiveTask<Map<String, List<String>>> {
    private final Folder folder;

    FolderSearchTask(Folder folder) {
        super();
        this.folder = folder;
    }

    @Override
    protected Map<String, List<String>> compute() {
        List<RecursiveTask<Map<String, List<String>>>> forks = new LinkedList<>();
        Map<String, List<String>> res = new HashMap<>();

        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchTask task = new FolderSearchTask(subFolder);
            forks.add(task);
            task.fork();
        }
        for (Document document : folder.getDocuments()) {
            DocumentSearchTask task = new DocumentSearchTask(document);
            forks.add(task);
            task.fork();
        }

        for (RecursiveTask<Map<String, List<String>>> task : forks) {
            Map<String, List<String>> taskRes = task.join();

            for (Map.Entry<String, List<String>> entry : taskRes.entrySet()) {
                List<String> value;
                if (res.containsKey(entry.getKey())) {
                    value = res.get(entry.getKey());
                } else {
                    value = new ArrayList<>();
                }
                value.addAll(entry.getValue());
                res.put(entry.getKey(), value);
            }
        }

        return res;
    }
}
