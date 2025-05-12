package com.jinyangyu.photocard_collection.service;

import com.jinyangyu.photocard_collection.model.Photocard;
import com.jinyangyu.photocard_collection.model.CardCollection;
import com.jinyangyu.photocard_collection.persistence.JsonReader;
import com.jinyangyu.photocard_collection.persistence.JsonWriter;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class CardService {
    private CardCollection collection;
    private final JsonReader reader = new JsonReader("data/cards.json");
    private final JsonWriter writer = new JsonWriter("data/cards.json");

    @PostConstruct
    public void init() {
        try {
            collection = reader.read();
        } catch (IOException e) {
            collection = new CardCollection("My Collection");
        }
    }

    public List<Photocard> getAll() {
        return collection.getCards();
    }

    public void add(Photocard card) {
        collection.addCard(card);
        save();
    }

    public void remove(int idx) {
        collection.removeCard(idx);
        save();
    }

    public List<Photocard> getTradeables() {
        return collection.createTradeCards().getCards();
    }

    private void save() {
        try {
            writer.open();
            writer.write(collection);
            writer.close();
        } catch (Exception e) {
            // handle write error
        }
    }
}
