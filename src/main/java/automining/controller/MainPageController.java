package automining.controller;

import automining.model.ConfigGson;
import automining.model.Rig;
import automining.model.User;
import automining.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {

    @Autowired
    private Environment environment;

    @Autowired
    ServletContext servletContext;

    @Autowired
    public UserService userService;

    @Autowired
    public RigService rigService;

    @Autowired
    public CardService cardService;



    @GetMapping("/rig/{id}")
    public String rigs(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable("id") String id) {

        Cookie cookie = userService.checkCookie(request.getCookies());

        if (!checkRig(cookie, id)) return "redirect:/";
        User user = userService.getByLogin(cookie.getValue());

        rigService.updateInfo(ServerServiceImpl.getInfo(user.getKeyUser()), user);

        model.addAttribute("cards", cardService.getByRigId(Integer.parseInt(id)));
        model.addAttribute("name", rigService.getById(Integer.parseInt(id)).getName());
        model.addAttribute("conf", ServerServiceImpl.getConfig(user.getKeyUser(), rigService.getById(Integer.parseInt(id)).getName()));

        return "rig";
    }

    private boolean checkRig(Cookie cookie, String id) {
        if (cookie == null) {
            return false;
        } else {
            for (Rig rig : rigService.getByUserId(userService.getByLogin(cookie.getValue()).getId())) {
                if (rig.getId() == Integer.valueOf(id)) return true;
            }
        }

        return false;
    }

    @GetMapping("/rig/{id}/reboot")
    public String rigsReboot(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable("id") String id) {

        Cookie cookie = userService.checkCookie(request.getCookies());
        if (!checkRig(cookie, id)) return "redirect:/";

        User user = userService.getByLogin(cookie.getValue());
        ServerServiceImpl.rebootRig(user.getKeyUser(), rigService.getById(Integer.parseInt(id)).getName());

        model.addAttribute("alert", environment.getProperty("validation.rig.reboot"));
        model.addAttribute("cards", cardService.getByRigId(Integer.parseInt(id)));
        model.addAttribute("name", rigService.getById(Integer.parseInt(id)).getName());
        model.addAttribute("conf", ServerServiceImpl.getConfig(user.getKeyUser(), rigService.getById(Integer.parseInt(id)).getName()));

        return "rig";
    }

    @GetMapping("/rig/{id}/off")
    public String rigsOff(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable("id") String id) {

        Cookie cookie = userService.checkCookie(request.getCookies());
        if (!checkRig(cookie, id)) return "redirect:/";

        User user = userService.getByLogin(cookie.getValue());

        ServerServiceImpl.offRig(user.getKeyUser(), rigService.getById(Integer.parseInt(id)).getName());
        model.addAttribute("alert", environment.getProperty("validation.rig.off"));
        model.addAttribute("cards", cardService.getByRigId(Integer.parseInt(id)));
        model.addAttribute("name", rigService.getById(Integer.parseInt(id)).getName());
        model.addAttribute("conf", ServerServiceImpl.getConfig(user.getKeyUser(), rigService.getById(Integer.parseInt(id)).getName()));

        return "rig";
    }

    @GetMapping("/rebootAll")
    public String rigsRebootAll(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = userService.checkCookie(request.getCookies());
        if (cookie == null) {
            return "redirect:/";
        }

        User user = userService.getByLogin(cookie.getValue());
        List<Rig> rigList = rigService.getByUserId(user.getId());

        for (Rig rig : rigList) {
            ServerServiceImpl.rebootRig(user.getKeyUser(), rig.getName());
        }

        model.addAttribute("alert", environment.getProperty("validation.rig.rebootAll"));
        model.addAttribute("rigs", rigService.getByUserId(user.getId()));
        model.addAttribute("money", ServerServiceImpl.getMoney(user.getKeyUser()));
        model.addAttribute("keyUser", user.getKeyUser());
        model.addAttribute("keyTelegram", user.getKeyTelegram());

        return "control-panel";
    }

    @GetMapping("/offAll")
    public String rigsOffAll(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = userService.checkCookie(request.getCookies());
        if (cookie == null) {
            return "redirect:/";
        }

        User user = userService.getByLogin(cookie.getValue());
        List<Rig> rigList = rigService.getByUserId(user.getId());

        for (Rig rig : rigList) {
            ServerServiceImpl.offRig(user.getKeyUser(), rig.getName());
        }

        model.addAttribute("alert", environment.getProperty("validation.rig.offAll"));
        model.addAttribute("rigs", rigService.getByUserId(user.getId()));
        model.addAttribute("money", ServerServiceImpl.getMoney(user.getKeyUser()));
        model.addAttribute("keyUser", user.getKeyUser());
        model.addAttribute("keyTelegram", user.getKeyTelegram());

        return "control-panel";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

        Cookie cookie = userService.checkCookie(request.getCookies());
        if (cookie != null) {
            User user = userService.getByLogin(cookie.getValue());
            if (user.getRight() == 666) {
                return "admin-panel";
            }
            rigService.updateInfo(ServerServiceImpl.getInfo(user.getKeyUser()), user);

            model.addAttribute("rigs", rigService.getByUserId(user.getId()));
            model.addAttribute("money", ServerServiceImpl.getMoney(user.getKeyUser()));
            model.addAttribute("keyUser", user.getKeyUser());
            model.addAttribute("keyTelegram", user.getKeyTelegram());

            return "control-panel";
        }
        return "index";
    }

    @GetMapping("/exit")
    public String exit(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = userService.checkCookie(request.getCookies());
        if (cookie != null) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, BindingResult validation, Model model, HttpServletRequest request, HttpServletResponse response) {

        user.setLogin(user.getLogin().toLowerCase());

        if (userService.validateUser(user)) {
            Cookie cookie = new Cookie("username", user.getLogin());
            cookie.setMaxAge(60 * 60 * 24); // 24 hours for expiry
            response.addCookie(cookie);
            return "redirect:/";
        } else {
            validation.addError(new ObjectError("user", environment.getProperty("validation.login.error")));
            model.addAttribute("bindingResult", validation);
            model.addAttribute("user", user);
            return "index";
        }
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user, BindingResult validation, Model model,
                          HttpServletRequest request, HttpServletResponse response) {

        Cookie cookie = userService.checkCookie(request.getCookies());
        user.setLogin(user.getLogin().toLowerCase());

        if (userService.validateCreateUser(user)) {
            validation.addError(new ObjectError("user", environment.getProperty("validation.login.create.success")));
            model.addAttribute("bindingResult", validation);
            model.addAttribute("userAddUser", user);
        } else {
            validation.addError(new ObjectError("user", environment.getProperty("validation.login.create.error")));
            model.addAttribute("bindingResult", validation);
            model.addAttribute("userAddUser", user);
        }
        if (cookie != null) {
            return "admin-panel";
        } else return "index";

    }


    @PostMapping("/delUser")
    public String delUser(@ModelAttribute("user") User user, BindingResult validation, Model model,
                          HttpServletRequest request, HttpServletResponse response) {

        user.setLogin(user.getLogin().toLowerCase());

        String login = user.getLogin();
        if (userService.validateDeleteUser(login)) {
            validation.addError(new ObjectError("user", environment.getProperty("validation.login.delete.success")));
            model.addAttribute("bindingResultDelUser", validation);
            model.addAttribute("loginDelUser", login);
        } else {
            validation.addError(new ObjectError("user", environment.getProperty("validation.login.delete.error")));
            model.addAttribute("bindingResultDelUser", validation);
            model.addAttribute("loginDelUser", login);
        }

        return "admin-panel";

    }

    @PostMapping("/rig/{id}/updateConf")
    public String updateConf(@ModelAttribute("ConfigGson") ConfigGson configGson, @PathVariable("id") String id, BindingResult validation, Model model,
                             HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = userService.checkCookie(request.getCookies());
        if (!checkRig(cookie, id)) return "redirect:/";

        User user = userService.getByLogin(cookie.getValue());

        ServerServiceImpl.updateConf(configGson, user.getKeyUser(), rigService.getById(Integer.parseInt(id)).getName());
        model.addAttribute("alert", environment.getProperty("validation.rig.conf"));
        model.addAttribute("cards", cardService.getByRigId(Integer.parseInt(id)));
        model.addAttribute("name", rigService.getById(Integer.parseInt(id)).getName());
        model.addAttribute("conf", ServerServiceImpl.getConfig(user.getKeyUser(), rigService.getById(Integer.parseInt(id)).getName()));

        return "rig";
    }

    @PostMapping("/addMonets")
    public String addMonets(@ModelAttribute("user") User user, BindingResult validation, Model model,
                            HttpServletRequest request, HttpServletResponse response) {
        user.setLogin(user.getLogin().toLowerCase());

        String login = user.getLogin();
        String monets = user.getPassword();
        if (userService.getByLogin(login).getLogin() == null) {
            validation.addError(new ObjectError("user", environment.getProperty("validation.login.monets.error.login")));
            model.addAttribute("bindingResultAddMonets", validation);
            model.addAttribute("loginMonets", login);
            return "admin-panel";
        }

        if (!monets.matches("^-?[0-9]\\d*(\\.\\d+)?$")) {
            validation.addError(new ObjectError("user", environment.getProperty("validation.login.monets.error.match")));
            model.addAttribute("bindingResultAddMonets", validation);
            model.addAttribute("loginMonets", login);
            return "admin-panel";
        }


        if (!ServerServiceImpl.addMoney(userService.getByLogin(login), monets)) {
            validation.addError(new ObjectError("user", environment.getProperty("validation.server.error")));
            model.addAttribute("bindingResultAddMonets", validation);
            model.addAttribute("loginMonets", login);
            return "admin-panel";
        }

        validation.addError(new ObjectError("user", environment.getProperty("validation.login.monets.success")));
        model.addAttribute("bindingResultAddMonets", validation);
        model.addAttribute("loginMonets", login);

        return "admin-panel";

    }
}
