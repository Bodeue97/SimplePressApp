package pl.blukasz.articleApp.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.blukasz.articleApp.entity.ArticleEntity;
import pl.blukasz.articleApp.service.ArticleService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ArticleControllerTest {


    @Test
    void shouldAddArticle() {
        String title = "title";
        String contents = "contents";
        String paperName = "paperName";
        String authorFirstname = "authorFirstname";
        String authorLastname = "authorLastname";
        String publicationDate = "2022-02-02";
        ArticleEntity article = new ArticleEntity(title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate));
        ArticleService articleService = Mockito.mock(ArticleService.class);
        when(articleService.addArticle(title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate))).thenReturn(article);
        ArticleController articleController = new ArticleController(articleService);
        assertEquals("Article with title " + title + " added", articleController.addArticle(title, contents, paperName, authorFirstname, authorLastname, publicationDate));


    }

    @Test
    void shouldShowArticlesByPublicationDateDesc() {

        ArticleService articleService = Mockito.mock(ArticleService.class);
        String title = "title";
        String contents = "contents";
        String paperName = "paperName";
        String authorFirstname = "authorFirstname";
        String authorLastname = "authorLastname";
        String publicationDate = "2022-02-02";
        String title2 = "title2";
        String contents2 = "contents2";
        String paperName2 = "paperName2";
        String authorFirstname2 = "authorFirstname2";
        String authorLastname2 = "authorLastname2";
        String publicationDate2 = "2022-02-01";
        String title3 = "title3";
        String contents3 = "content3s";
        String paperName3 = "paperName3";
        String authorFirstname3 = "authorFirstname3";
        String authorLastname3 = "authorLastname3";
        String publicationDate3 = "2022-02-03";
        ArticleEntity article1 = new ArticleEntity(title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate));
        ArticleEntity article2 = new ArticleEntity(title2, contents2, paperName2, authorFirstname2, authorLastname2, LocalDate.parse(publicationDate2));
        ArticleEntity article3 = new ArticleEntity(title3, contents3, paperName3, authorFirstname3, authorLastname3, LocalDate.parse(publicationDate3));
        List<ArticleEntity> articles = List.of(article3, article1, article2);
        when(articleService.getAllArticlesSortedByDateDesc()).thenReturn(articles);
        ArticleController articleController = new ArticleController(articleService);
        assertEquals(articles, articleController.showArticlesByPublicationDateDesc());


    }

    @Test
    void shouldSearchByKeyword() {
        ArticleService articleService = Mockito.mock(ArticleService.class);
        String keyword = "keyword";
        ArticleController articleController = new ArticleController(articleService);
        String title = "keyword";
        String contents = "contents";
        String paperName = "paperName";
        String authorFirstname = "authorFirstname";
        String authorLastname = "authorLastname";
        String publicationDate = "2022-02-02";
        String title2 = "title2";
        String contents2 = "keyword";
        String paperName2 = "paperName2";
        String authorFirstname2 = "authorFirstname2";
        String authorLastname2 = "authorLastname2";
        String publicationDate2 = "2022-02-01";

        ArticleEntity article1 = new ArticleEntity(title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate));
        ArticleEntity article2 = new ArticleEntity(title2, contents2, paperName2, authorFirstname2, authorLastname2, LocalDate.parse(publicationDate2));
        List<ArticleEntity> articles = List.of(article1, article2);

        when(articleService.getAllContaining(keyword)).thenReturn(articles);
        assertEquals(articles, articleController.searchByKeyword(keyword));


    }

    @Test
    void shouldSearchById() {
        String title = "title";
        String contents = "contents";
        String paperName = "paperName";
        String authorFirstname = "authorFirstname";
        String authorLastname = "authorLastname";
        String publicationDate = "2022-02-02";
        ArticleEntity article1 = new ArticleEntity(title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate));
        ArticleService articleService = Mockito.mock(ArticleService.class);
        ArticleController articleController = new ArticleController(articleService);
        Long id = 1L;
        when(articleService.getArticleById(id)).thenReturn(article1);
        assertEquals(article1, articleController.searchById(id));


    }

    @Test
    void shouldUpdateArticle() {

        ArticleService articleService = Mockito.mock(ArticleService.class);
        ArticleController articleController = new ArticleController(articleService);
        String title = "title";
        String contents = "contents";
        String paperName = "paperName";
        String authorFirstname = "authorFirstname";
        String authorLastname = "authorLastname";
        String publicationDate = "2022-02-02";
        Long id = 1L;
        ArticleEntity article1 = new ArticleEntity(title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate));
        ArticleEntity article2 = new ArticleEntity(id, title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate));

        when(articleService.updateArticle(id, article1)).thenReturn(article2);
        assertEquals(article2, articleController.updateArticle(title, contents, paperName, authorFirstname, authorLastname, publicationDate, id));
        assertEquals(article2.getId(), articleController.updateArticle(title, contents, paperName, authorFirstname, authorLastname, publicationDate, id).getId());

    }

    @Test
    void shouldDeleteArticle() {
        ArticleService articleService = Mockito.mock(ArticleService.class);
        ArticleController articleController = new ArticleController(articleService);
        String title = "title";
        String contents = "contents";
        String paperName = "paperName";
        String authorFirstname = "authorFirstname";
        String authorLastname = "authorLastname";
        LocalDate publicationDate = LocalDate.parse("2022-02-02");
        Long id = 1L;

        ArticleEntity article = new ArticleEntity(Long.valueOf(id), title, contents, paperName, authorFirstname, authorLastname, publicationDate);
        when(articleService.deleteArticle(Long.valueOf(id))).thenReturn(article);
        assertEquals(article, articleController.deleteArticle(id));
        assertEquals(article.getId(), articleController.deleteArticle(id).getId());


    }
}