package com.jinyangyu.photocard_collection.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jinyangyu.photocard_collection.persistence.Writable;

// Represents a collection of K-pop photocards. It uses an ArrayList to store 
// instances of Photocard objects. This class provides basic functionality 
// to manage the collection, including adding and removing photocards

public class CardCollection implements Writable {
    private ArrayList<Photocard> cards;
    private String name;

    // EFFECTS: constructs an empty collection of cards
    public CardCollection(String name) {
        this.cards = new ArrayList<>();
        this.name = name;
    }

    // EFFECTS: add a card to the card collection
    public void addCard(Photocard card) {
        cards.add(card);
    }

    // REQUIRES: 0 <= id < the number of cards in the collection
    // EFFECTS: remove a card from the card collection
    public void removeCard(int id) {
        cards.remove(cards.get(id));
    }

    // EFFECTS: check if this photocard is already in your collection.
    // return true if it is in your collection, false otherwise
    public boolean containCard(Photocard card) {
        return cards.contains(card);
    }

    // MODIFIES: cardsToTrade
    // EFFECTS: loop through my card collection to create a collection of card available to trade
    public CardCollection createTradeCards() {
        CardCollection cardsToTrade = new CardCollection("cardsToTrade");
        for (Photocard currentPhotocard : this.cards) {
            if (currentPhotocard.getTradeStatus() == true) {
                cardsToTrade.addCard(currentPhotocard);
            }
        }
        return cardsToTrade;
    }

    // REQUIRES: 0 <= id < the number of cards in the collection
    // EFFECTS: returns the photocard at the specified index in the collection
    public Photocard getCard(int id) {
        return cards.get(id);
    }

    // EFFECTS: returns the name of the card collection
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns the entire card collection
    public ArrayList<Photocard> getCards() {
        return this.cards;
    }
    
    // EFFECTS: get the number of cards that you have
    public int numCards() {
        return cards.size();
    }

    // EFFECTS: check if the collection is empty
    public boolean isEmpty() {
        return cards.size() == 0;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("cards", cardsToJson());
        return json;
    }

    // EFFECTS: returns things in this card collection as a JSON array
    private JSONArray cardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Photocard c : cards) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
