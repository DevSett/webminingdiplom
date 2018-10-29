package automining.security;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class UserAccess {

    private List<UserToken> tokens = new ArrayList<>();

    public List<UserToken> getTokens() {
        return tokens;
    }

    public void addSuccess(String token, int id){
        tokens.add(new UserToken(id,token));
    }
    public void removeSuccess(int id){
        tokens.forEach(user -> {
            if(user.getId() == id) tokens.remove(id);
            return;
        });
    }

    public boolean isSuccess(int id){
        for (UserToken token : tokens) {
            if (token.getId() == id) return true;
        }
        return false;
    }

    class UserToken{
        private int id;
        private String token;

        public UserToken(int id, String token) {
            this.id = id;
            this.token = token;
        }

        public int getId() {
            return id;
        }

        public String getToken() {
            return token;
        }


    }
}
