package pl.blukasz.articleApp.controller;

import org.springframework.web.bind.annotation.*;
import pl.blukasz.articleApp.entity.ArticleEntity;
import pl.blukasz.articleApp.service.ArticleService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;


@RestController
public class ArticleController {


    private final ArticleService articleService;


    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @PostMapping("/add")
    @ResponseBody
    String addArticle(
            @RequestParam String title, @RequestParam String contents,
            @RequestParam String paperName, @RequestParam String authorFirstname,
            @RequestParam String authorLastname, @RequestParam String publicationDate
    ) throws DateTimeParseException {

        articleService.addArticle(title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate));

        return "Article with title " + title + " added";

    }

    @GetMapping("/show")
    @ResponseBody
    List<ArticleEntity> showArticlesByPublicationDateDesc() {

        return articleService.getAllArticlesSortedByDateDesc();

    }


    @GetMapping("/byKeyword")
    @ResponseBody
    List<ArticleEntity> searchByKeyword(@RequestParam String keyword) {
        return articleService.getAllContaining(keyword);


    }


    @GetMapping("/byId")
    @ResponseBody
    ArticleEntity searchById(@RequestParam Long id) throws NumberFormatException {

        return articleService.getArticleById(id);


    }


    @PutMapping("/update")
    @ResponseBody
    ArticleEntity updateArticle(@RequestParam String title, @RequestParam String contents,
                                @RequestParam String paperName, @RequestParam String authorFirstname,
                                @RequestParam String authorLastname, @RequestParam String publicationDate,
                                @RequestParam Long id) throws DateTimeParseException, NumberFormatException {


        ArticleEntity updateData = new ArticleEntity(title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate));
        return articleService.updateArticle(id, updateData);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    ArticleEntity deleteArticle(@RequestParam Long id) throws NumberFormatException {
        return articleService.deleteArticle(id);

    }


}
