package automining.service;

import automining.model.Card;

import java.util.List;

public interface CardService {
    void save(Card card);

    Card getById(int id);

    List<Card> getByRigId(int id);

    List<Card> findAll();

    void update(Card card);

    void delete(int id);

    void deleteWithRig(int id);
}
