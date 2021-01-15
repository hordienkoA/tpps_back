package ua.cn.stu.tpps.buyfly.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Controller for home page
 */
@Controller
public class HomePageController {

    public static final Logger logger = LogManager.getLogger("BuyFly");

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(@RequestParam(value = "locale", required = false) String localeParam,
                          HttpServletRequest request, HttpServletResponse response,
                          Model model) {

        if (localeParam != null) {
            LocaleContextHolder.setLocale(new Locale(localeParam));
        }

        // obtain locale from LocaleContextHolder
        Locale currentLocale = LocaleContextHolder.getLocale();
        model.addAttribute("locale", currentLocale);
        logger.info("locale changed");

        RequestContextUtils.getLocaleResolver(request).setLocale(request, response, currentLocale);

        // add parametrized message from controller
        String welcome = messageSource.getMessage("homepage.welcome.greeting", new Object[]{"John Doe"}, currentLocale);
        model.addAttribute("message", welcome);

        return "home";
    }
}