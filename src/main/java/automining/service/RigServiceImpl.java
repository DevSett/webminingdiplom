package automining.service;

import automining.dao.RigDao;
import automining.model.Card;
import automining.model.Rig;
import automining.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class RigServiceImpl implements RigService {

    @Autowired
    RigDao rigDao;
    @Autowired
    CardService cardService;


    @Override
    public void save(Rig rig) {
        rigDao.save(rig);
    }

    @Override
    public Rig getById(int id) {
        return rigDao.getById(id);
    }

    @Override
    public List<Rig> getByUserId(int id) {
        return rigDao.getByUserId(id);
    }

    @Override
    public List<Rig> findAll() {
        return rigDao.findAll();
    }

    @Override
    public void update(Rig rig) {
        rigDao.update(rig);
    }

    @Override
    public void delete(int id) {
        rigDao.delete(id);
    }

    @Override
    public void updateInfo(String info, User user) {
        List<Rig> rigs = rigDao.getByUserId(user.getId());
        for (Rig rig : rigs) {
            cardService.deleteWithRig(rig.getId());
        }
        for (Rig rig : rigs) {
            rigDao.delete(rig.getId());
        }

        String[] split = info.split("\uD83D\uDD6F\uD83D\uDD6F\uD83D\uDD6F");
        for (String s : split) {
            String[] strokes = s.split("\n");
            if (strokes.length < 2) continue;
            String nameRig = strokes[0];
            String date = strokes[2].substring(strokes[2].indexOf(":")+2,strokes[2].length());
            List<String> temperature = new ArrayList<>();
            List<String> fanSpeed = new ArrayList<>();
            String totalSpeed = "";
            List<String> mhs = new ArrayList<>();
            for (String stroke : strokes) {
                if (stroke.contains("fan")) {
                    temperature.add(stroke.substring(stroke.indexOf("t=") + 2, stroke.indexOf("C")));
                    fanSpeed.add(stroke.substring(stroke.indexOf("fan=") + 4, stroke.indexOf("%")));
                    continue;
                }
                if (stroke.contains("Total Speed")) {
                    totalSpeed = stroke.substring(stroke.indexOf(":") + 2, stroke.indexOf("Mh/s") - 1);
                    continue;
                }
                if (stroke.contains("GPU")) {
                    mhs.add(stroke.substring(stroke.indexOf("GPU") + 5, stroke.indexOf("Mh/s") - 1));
                    continue;
                }
            }
            int averageTemperature = 0;
            for (String s1 : temperature) {
                averageTemperature += Integer.valueOf(s1);
            }

            averageTemperature = averageTemperature / temperature.size();
            int id;
            rigDao.save(new Rig((id = rigDao.count() + 1),
                    user.getId(),
                    nameRig,
                    Double.valueOf(totalSpeed),
                    averageTemperature,
                    mhs.size(),
                    date
            ));

            for (int i = 0; i < mhs.size(); i++) {
                Card card = new Card();
                card.setFanSpeed(Integer.parseInt(fanSpeed.get(i)));
                card.setSpeed(Double.parseDouble(mhs.get(i)));
                card.setRigId(id);
                card.setTemperature(Integer.parseInt(temperature.get(i)));
                cardService.save(card);
            }

        }

    }
}
