package com.node.affinity;

import java.io.IOException;

public interface IAffinity {
    boolean checkDocumentAffinity(int docID) throws IOException;
    void addDocumentAffinity(int docID);
    void deleteDocumentAffinity(int docID);
    int GetDocumentAffinity(int id) throws IOException;
}
