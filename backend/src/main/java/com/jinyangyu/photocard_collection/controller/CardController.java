package com.jinyangyu.photocard_collection.controller;

import com.jinyangyu.photocard_collection.model.Photocard;
import com.jinyangyu.photocard_collection.service.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardService service;
    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping
    public List<Photocard> list() {
        return service.getAll();
    }

    @GetMapping("/trade")
    public List<Photocard> tradeables() {
        return service.getTradeables();
    }

    @PostMapping
    public void add(@RequestBody Photocard card) {
        service.add(card);
    }

    @DeleteMapping("/{idx}")
    public void delete(@PathVariable int idx) {
        service.remove(idx);
    }
}
