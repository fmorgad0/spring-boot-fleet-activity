package com.examples.springbootfleetactivity.utils;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class TestUtils {

    public static void insertDocumentsFromMongoExtendedJsonFile(MongoTemplate mongoTemplate, Path path, String collectionName) {

        try {

            List<Document> documents = new ArrayList<>();
            Files.readAllLines(path).forEach(l -> documents.add(Document.parse(l)));

            mongoTemplate.getCollection(collectionName).insertMany(documents);

            System.out.println(documents.size() + " documents loaded for " + collectionName + " collection.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
