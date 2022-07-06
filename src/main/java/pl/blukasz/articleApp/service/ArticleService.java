package pl.blukasz.articleApp.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.blukasz.articleApp.entity.ArticleEntity;
import pl.blukasz.articleApp.repository.ArticleRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ArticleService {


    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleEntity addArticle(String title, String contents, String paperName, String authorFirstName, String authorLastname, LocalDate publicationDate) {
        ArticleEntity article = new ArticleEntity(title, contents, paperName, authorFirstName, authorLastname, publicationDate);
        article.setEdited(LocalDateTime.now());
        articleRepository.save(article);
        return article;
    }

    public ArticleEntity getArticleById(Long id) throws EntityNotFoundException {
        Optional<ArticleEntity> articleById = articleRepository.findArticleById(id);
        articleById.orElseThrow(() -> new EntityNotFoundException("Article with id " + id + " not found"));
        return articleById.get();
    }

    public List<ArticleEntity> getAllArticlesSortedByDateDesc() {
        return articleRepository.findAllByOrderByPublicationDateDesc();
    }


    public List<ArticleEntity> getAllContaining(String keyword) {
        List<ArticleEntity> articlesTitleContaining = articleRepository.findByTitleContains(keyword);
        List<ArticleEntity> articlesContentsContaining = articleRepository.findByContentsContains(keyword);
        return Stream.concat(articlesTitleContaining.stream(), articlesContentsContaining.stream()).distinct()
                .collect(Collectors.toList());

    }

    @Transactional
    public ArticleEntity updateArticle(Long id, ArticleEntity updateData) throws EntityNotFoundException {

        Optional<ArticleEntity> articleToUpdate = articleRepository.findArticleById(id);
        articleToUpdate.orElseThrow(() -> new EntityNotFoundException("Article with id " + id + " not found"));
        ArticleEntity updatedArticle = articleToUpdate.get();
        if (!updateData.getTitle().equals(""))
            updatedArticle.setTitle(updateData.getTitle());
        if (!updateData.getAuthorFirstname().equals(""))
            updatedArticle.setAuthorFirstname(updateData.getAuthorFirstname());
        if (!updateData.getAuthorLastname().equals(""))
            updatedArticle.setAuthorLastname(updateData.getAuthorLastname());
        if (!updateData.getContents().equals(""))
            updatedArticle.setContents(updateData.getContents());
        if (!updateData.getPaperName().equals(""))
            updatedArticle.setPaperName(updateData.getPaperName());
        if (!updateData.getPublicationDate().equals(""))
            updatedArticle.setPublicationDate(updateData.getPublicationDate());
        updatedArticle.setEdited(LocalDateTime.now());
        articleRepository.save(updatedArticle);
        return updatedArticle;

    }

    public ArticleEntity deleteArticle(Long id) throws EntityNotFoundException {
        Optional<ArticleEntity> deleteArticle = articleRepository.findArticleById(id);
        deleteArticle.orElseThrow(() -> new EntityNotFoundException("Article with id " + id + " not found"));
        articleRepository.delete(deleteArticle.get());
        return deleteArticle.get();
    }


}
