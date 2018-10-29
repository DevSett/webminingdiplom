package automining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;
import sun.misc.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/images")


public class ImageController {
    @Autowired
    ServletContext servletContext;

    @ResponseBody
    @GetMapping("/line")
    public Resource getImageAsResourceLine() {
        return new ServletContextResource(servletContext, "/WEB-INF/images/line.png");
    }

    @ResponseBody
    @GetMapping("/main")
    public Resource getImageAsResourceMain() {
        return new ServletContextResource(servletContext, "/WEB-INF/images/main.png");
    }

    @ResponseBody
    @GetMapping("/info")
    public Resource getImageAsResourceInfo() {
        return new ServletContextResource(servletContext, "/WEB-INF/images/info.png");
    }

    @ResponseBody
    @GetMapping("/scroll")
    public Resource getImageAsResourceScroll() {
        return new ServletContextResource(servletContext, "/WEB-INF/images/scroll.png");
    }

    @ResponseBody
    @GetMapping("/avatar")
    public Resource getImageAsResourceAvatar() {
        return new ServletContextResource(servletContext, "/WEB-INF/images/avatar.png");
    }

    @ResponseBody
    @GetMapping("/alert-ico")
    public Resource getImageAsResourceAlertIco() {
        return new ServletContextResource(servletContext, "/WEB-INF/images/alert-ico.png");
    }

    @ResponseBody
    @GetMapping("/sort-table")
    public Resource getImageAsResourceSortTable() {
        return new ServletContextResource(servletContext, "/WEB-INF/images/sort-table.png");
    }



    @ResponseBody
    @GetMapping("/vkr")
    public void getImageAsResourceVkr(HttpServletResponse response) {
      try {
          org.apache.commons.io.IOUtils.copy(new ServletContextResource(servletContext, "/WEB-INF/files/vkr.docx").getInputStream(), response.getOutputStream());
          response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
