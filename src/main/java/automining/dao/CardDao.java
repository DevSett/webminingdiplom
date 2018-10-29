package automining.dao;

import automining.model.Card;

import java.util.List;

public interface CardDao {

    void save(Card card);

    Card getById(int id);

    List<Card> getByRigId(int id);

    List<Card> findAll();



    void update(Card card);

    void delete(int id);

    int count();
}
