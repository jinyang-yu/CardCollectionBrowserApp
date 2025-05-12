package com.jinyangyu.photocard_collection.persistence;

import com.jinyangyu.photocard_collection.model.CardCollection;
import com.jinyangyu.photocard_collection.model.Photocard;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads CardCollection from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads card collection from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CardCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCardCollection(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses CardCollection from JSON object and returns it
    private CardCollection parseCardCollection(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        CardCollection cc = new CardCollection(name);
        addCards(cc, jsonObject);
        return cc;
    }

    // MODIFIES: CardCollection
    // EFFECTS: parses photocards from JSON object and adds them to CardCollection
    private void addCards(CardCollection cc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cards");
        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addCard(cc, nextCard);
        }
    }

    // MODIFIES: CardCollection
    // EFFECTS: parses a photocard from JSON object and adds it to CardCollection
    private void addCard(CardCollection cc, JSONObject jsonObject) {
        String memberName = jsonObject.getString("memberName");
        String groupName = jsonObject.getString("groupName");
        boolean tradeStatus = jsonObject.getBoolean("tradeStatus");
        Photocard card = new Photocard(memberName, groupName, tradeStatus);
        cc.addCard(card);
    }
}
