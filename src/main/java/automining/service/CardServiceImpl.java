package automining.service;

import automining.dao.CardDao;
import automining.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardDao cardDao;

    @Override
    public void save(Card card) {
        card.setId(cardDao.count() + 1);
        cardDao.save(card);
    }

    @Override
    public Card getById(int id) {
        return cardDao.getById(id);
    }

    @Override
    public List<Card> getByRigId(int id) {
        return cardDao.getByRigId(id);
    }

    @Override
    public List<Card> findAll() {
        return cardDao.findAll();
    }

    @Override
    public void update(Card card) {
        cardDao.update(card);
    }

    @Override
    public void delete(int id) {
        cardDao.delete(id);
    }

    @Override
    public void deleteWithRig(int id) {
        List<Card> cards = getByRigId(id);
        for (Card card : cards) {
            delete(card.getId());
        }
    }
}
