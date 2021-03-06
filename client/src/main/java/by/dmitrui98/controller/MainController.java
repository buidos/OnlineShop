package by.dmitrui98.controller;

import by.dmitrui98.entity.Product;
import by.dmitrui98.service.dao.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Администратор on 08.04.2017.
 */
@Controller
public class MainController {

    public static final int maxPages = 14;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        int countElementsPerPage = 5;
        List<Product> products = productService.getElements(0, countElementsPerPage);
        long productCount = productService.getCount();

        int countPages = (int) productCount / countElementsPerPage;
        if (productCount % countElementsPerPage != 0) {
            countPages++;
        }

        model.addAttribute("products", products);
        model.addAttribute("countPages", countPages);

        model.addAttribute("currentPage", 1);
        model.addAttribute("countPerPage", countElementsPerPage);
        model.addAttribute("maxPages", maxPages);

        return "mainPage";
    }

    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public String productList(@RequestParam(name = "countElements",defaultValue = "10") int countElementsPerPage,
                               @RequestParam(defaultValue = "1") int page,
                               Model model) {
        int from = (page * countElementsPerPage) - countElementsPerPage;
        List<Product> products = productService.getElements(from, countElementsPerPage);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("countPerPage", countElementsPerPage);

        return "productList";
    }



}
