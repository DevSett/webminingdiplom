package automining.service;

import automining.model.*;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.*;

@Log4j
@ClientEndpoint
public class ServerServiceImpl {
    public static volatile Session session;

    private static volatile Map<String, List<String>> ids = new HashMap<>();
    private static volatile String keyHost = "1OFSFE3MHBLV0X90KBTE4Y1QP3ELWCWJ09ZIVAM32BDV5OKV9F";

    public static void connect() {
        System.out.println("123");
        WebSocketContainer container = ContainerProvider
                .getWebSocketContainer();
        try {
            ServerServiceImpl.session = container.connectToServer(ServerServiceImpl.class,
                    URI.create("ws://46.16.10.97:58453/ws/miner"));
        } catch (DeploymentException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
    }

    public static String getMoney(String key) {
        try {
            if (checkConnect()) return "Error";
            String id = UUID.randomUUID().toString();

            ServerServiceImpl.ids.put(id, new ArrayList<>());

            ServerServiceImpl.session.getAsyncRemote().sendText(new Gson().toJson(new SendCommand(
                    id,
                    ServerServiceImpl.keyHost,
                    key,
                    "",
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false
            )));

            return (String) waitToResponse(id);
        } catch (Exception ex) {
            log.error(ex);
            return "Error";
        }
    }

    private static boolean checkConnect() {
        int i = 0;
        while ((ServerServiceImpl.session == null || !ServerServiceImpl.session.isOpen()) && i < 3) {
            System.out.println("miss");
            connect();
            i++;
        }
        if (i >= 3) {
            return true;
        }
        return false;
    }

    public static ConfigGson getConfig(String key, String name) {
        try {
            if (checkConnect()) return new ConfigGson();
            String id = UUID.randomUUID().toString();

            ServerServiceImpl.ids.put(id, new ArrayList<>());

            ServerServiceImpl.session.getAsyncRemote().sendText(new Gson().toJson(new SendCommand(
                    id,
                    ServerServiceImpl.keyHost,
                    name + " " + key,
                    "",
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false,
                    false
            )));

            return new Gson().fromJson((String) waitToResponse(id), ConfigGson.class);
        } catch (Exception ex) {
            log.error(ex);
            return new ConfigGson();
        }
    }


    @OnOpen
    public void onOpen(Session session) {
    }

    @OnMessage
    public void onMessage(String message) {
        try {
            if (ServerServiceImpl.ids.size() > 1000) ServerServiceImpl.ids.clear();
            System.out.println(message);
            ReciveCommand reciveMessage = new Gson().fromJson(message, ReciveCommand.class);
            List<String> names = ids.get(reciveMessage.getId());
            names.add(reciveMessage.getData());
            ServerServiceImpl.ids.replace(reciveMessage.getId(), names);
        } catch (Exception e) {
            log.error(e);
            System.out.println("ощибка onMessage");
            System.out.println(message);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println(closeReason.toString());

    }

    @OnError
    public void onError(Session session, Throwable thr) {
    }

    private static Object waitToResponse(String id) {
        try {
            Integer sleeper = 10000;
            Integer sleepTime = sleeper / 10;

            Thread.sleep(sleepTime);
            if (ServerServiceImpl.ids.get(id).size() > 0) return ServerServiceImpl.ids.get(id).get(0);


            while (sleepTime < sleeper) {

                Thread.sleep(sleeper / 100);
                sleepTime += sleeper / 100;

                if (ServerServiceImpl.ids.get(id).size() > 0) return ServerServiceImpl.ids.get(id).get(0);
            }
        } catch (InterruptedException e) {
            log.error(e);
        }

        return ServerServiceImpl.ids.get(id).size() == 0 ? null : ServerServiceImpl.ids.get(id).get(0);
    }

    public static boolean addMoney(User byLogin, String monets) {
        if (checkConnect()) return false;

        ServerServiceImpl.session.getAsyncRemote().sendText(new Gson().toJson(new SendCommand(
                UUID.randomUUID().toString(),
                ServerServiceImpl.keyHost,
                byLogin.getKeyUser(),
                monets,
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false
        )));

        return true;

    }

    public static boolean addUser(User userWithGeneratedKeys) {
        if (checkConnect()) return false;
        ServerServiceImpl.session.getAsyncRemote().sendText(new Gson().toJson(new SendCommand(
                UUID.randomUUID().toString(),
                ServerServiceImpl.keyHost,
                userWithGeneratedKeys.getKeyUser(),
                new Gson().toJson(new RigsGson(userWithGeneratedKeys.getKeyTelegram(), "https://api.ethermine.org", userWithGeneratedKeys.getAccount(), 0)),
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false
        )));
        return true;
    }

    public static String getInfo(String keyUser) {
        try {
            if (checkConnect()) return "Error";
            String id = UUID.randomUUID().toString();

            ServerServiceImpl.ids.put(id, new ArrayList<>());

            ServerServiceImpl.session.getAsyncRemote().sendText(new Gson().toJson(new SendCommand(
                    id,
                    ServerServiceImpl.keyHost,
                    keyUser,
                    "",
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false
            )));

            return (String) waitToResponse(id);
        } catch (Exception ex) {
            log.error(ex);
            return "Error";
        }
    }

    public static boolean rebootRig(String keyUser, String name) {
        if (checkConnect()) return false;
        ServerServiceImpl.session.getAsyncRemote().sendText(new Gson().toJson(new SendCommand(
                UUID.randomUUID().toString(),
                ServerServiceImpl.keyHost,
                name + " " + keyUser,
                "",
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false
        )));
        return true;
    }

    public static boolean offRig(String keyUser, String name) {
        if (checkConnect()) return false;
        ServerServiceImpl.session.getAsyncRemote().sendText(new Gson().toJson(new SendCommand(
                UUID.randomUUID().toString(),
                ServerServiceImpl.keyHost,
                name + " " + keyUser,
                "",
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true
        )));
        return true;
    }

    public static void updateConf(ConfigGson configGson, String keyUser, String name) {
        if (checkConnect()) return ;
        ServerServiceImpl.session.getAsyncRemote().sendText(new Gson().toJson(new SendCommand(
                UUID.randomUUID().toString(),
                ServerServiceImpl.keyHost,
                name + " " + keyUser,
                new Gson().toJson(configGson),
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false
        )));
    }
}