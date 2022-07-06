package pl.blukasz.articleApp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.blukasz.articleApp.entity.ArticleEntity;
import pl.blukasz.articleApp.repository.ArticleRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class ArticleServiceTest {


    @Test
    void shouldAddArticle() {

        ArticleRepository articleRepository = Mockito.mock(ArticleRepository.class);
        ArticleService articleService = new ArticleService(articleRepository);
        String title = "title";
        String contents = "contents";
        String paperName = "paperName";
        String authorFirstname = "authorFirstname";
        String authorLastname = "authorLastname";
        String publicationDate = "2022-02-02";
        ArticleEntity article = new ArticleEntity(title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate));
        when(articleRepository.save(Mockito.any(ArticleEntity.class))).then(i -> i.getArgument(0, ArticleEntity.class));

        assertEquals(article, articleService.addArticle(title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate)));

    }

    @Test
    void shouldGetArticleById() {

        ArticleRepository articleRepository = Mockito.mock(ArticleRepository.class);
        ArticleService articleService = new ArticleService(articleRepository);
        Long id = 1L;
        String title = "title";
        String contents = "contents";
        String paperName = "paperName";
        String authorFirstname = "authorFirstname";
        String authorLastname = "authorLastname";
        String publicationDate = "2022-02-02";
        ArticleEntity article = new ArticleEntity(id, title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate));
        when(articleRepository.findArticleById(id)).thenReturn(Optional.of(article));
        assertEquals(article, articleService.getArticleById(id));

    }

    @Test
    void shouldGetAllArticlesSortedByDateDesc() {

        ArticleRepository articleRepository = Mockito.mock(ArticleRepository.class);
        ArticleService articleService = new ArticleService(articleRepository);
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
        when(articleRepository.findAllByOrderByPublicationDateDesc()).thenReturn(articles);
        assertEquals(articles, articleService.getAllArticlesSortedByDateDesc());

    }

    @Test
    void shouldGetAllContaining() {

        ArticleRepository articleRepository = Mockito.mock(ArticleRepository.class);
        ArticleService articleService = new ArticleService(articleRepository);
        String keyword = "keyword";
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
        String title3 = "keyword";
        String contents3 = "keyword";
        String paperName3 = "paperName3";
        String authorFirstname3 = "authorFirstname3";
        String authorLastname3 = "authorLastname3";
        String publicationDate3 = "2022-02-09";


        ArticleEntity article1 = new ArticleEntity(title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate));
        ArticleEntity article2 = new ArticleEntity(title2, contents2, paperName2, authorFirstname2, authorLastname2, LocalDate.parse(publicationDate2));
        ArticleEntity article3 = new ArticleEntity(title3, contents3, paperName3, authorFirstname3, authorLastname3, LocalDate.parse(publicationDate3));
        List<ArticleEntity> articlesTitle = List.of(article1, article3);
        List<ArticleEntity> articlesContents = List.of(article2, article3);
        List<ArticleEntity> articlesTotal = List.of(article1, article3, article2);

        when(articleRepository.findByTitleContains(keyword)).thenReturn(articlesTitle);
        when(articleRepository.findByContentsContains(keyword)).thenReturn(articlesContents);

        assertEquals(articlesTotal, articleService.getAllContaining(keyword));


    }

    @Test
    void shouldUpdateArticle() {

        ArticleRepository articleRepository = Mockito.mock(ArticleRepository.class);
        ArticleService articleService = new ArticleService(articleRepository);
        Long id = 1L;
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
        ArticleEntity updateData = new ArticleEntity(title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate));
        ArticleEntity findreturns = new ArticleEntity(id, title2, contents2, paperName2, authorFirstname2, authorLastname2, LocalDate.parse(publicationDate2));
        ArticleEntity updatedArticle = new ArticleEntity(id, title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate));
        when(articleRepository.findArticleById(id)).thenReturn(Optional.of(findreturns));
        when(articleRepository.save(Mockito.any(ArticleEntity.class))).then(i -> i.getArgument(0, ArticleEntity.class));
        assertEquals(updatedArticle, articleService.updateArticle(id, updateData));
        assertEquals(updatedArticle.getId(), articleService.updateArticle(id, updateData).getId());


    }

    @Test
    void shouldDeleteArticle() {

        ArticleRepository articleRepository = Mockito.mock(ArticleRepository.class);
        ArticleService articleService = new ArticleService(articleRepository);
        Long id = 1L;
        String title = "title";
        String contents = "contents";
        String paperName = "paperName";
        String authorFirstname = "authorFirstname";
        String authorLastname = "authorLastname";
        String publicationDate = "2022-02-02";
        ArticleEntity articleToDelete = new ArticleEntity(id, title, contents, paperName, authorFirstname, authorLastname, LocalDate.parse(publicationDate));
        when(articleRepository.findArticleById(id)).thenReturn(Optional.of(articleToDelete));
        assertEquals(articleToDelete, articleService.deleteArticle(id));


    }
}