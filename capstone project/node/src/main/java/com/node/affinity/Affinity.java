package com.node.affinity;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import com.node.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class Affinity implements IAffinity{
    //contains all documents and their affinity
    private static Map<Integer, Integer> documentsAffinity = new HashMap<>();
    private static int AffinityNumber = 1;
    private final String affinityFilePath = "src/main/java/com/node/affinity/affinity.json";
    public static int nodeNumber=Integer.parseInt(System.getenv("nodeNumber"));
    public static int numberOfNodes=Integer.parseInt(System.getenv("numOfNodes"));
    private Gson gson = new Gson();

    @Autowired
    FileService fileService;

    public boolean checkDocumentAffinity(int docID) throws IOException {
        //check if this node is the affinity of document
        if (documentsAffinity.size()==0) {
            getAffinityFile();
        }
        if (documentsAffinity.containsKey(docID)) {
            return documentsAffinity.get(docID).equals(nodeNumber);
        }
        return false;
    }

    public void addDocumentAffinity(int docID) {
        //add affinity to affinity's file
        documentsAffinity.put(docID, AffinityNumber);
        AffinityNumber++;
        if (AffinityNumber == (numberOfNodes+1)) {
            AffinityNumber = 1;
        }
        storeAffinityFile();
    }

    public void deleteDocumentAffinity(int docID) {
        documentsAffinity.remove(docID);
        storeAffinityFile();
    }

    private void getAffinityFile() throws IOException {
        String affinityFile = fileService.readFile(affinityFilePath);
        // Convert the JSON string back to a HashMap using Gson
        documentsAffinity = gson.fromJson(affinityFile, new TypeToken<HashMap<Integer, Integer>>() {
        }.getType());
    }

    private void storeAffinityFile() {
        //store the document affinity in file and broadcast this file
        try {
            String json = gson.toJson(documentsAffinity);
            fileService.writeFile(affinityFilePath,json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int GetDocumentAffinity(int id) throws IOException {
        if (documentsAffinity.size()==0) {
            getAffinityFile();
        }
        return documentsAffinity.get(id);
    }

}
