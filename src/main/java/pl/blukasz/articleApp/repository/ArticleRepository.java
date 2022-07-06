package pl.blukasz.articleApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.blukasz.articleApp.entity.ArticleEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    List<ArticleEntity> findAllByOrderByPublicationDateDesc();

    Optional<ArticleEntity> findArticleById(Long id);

    List<ArticleEntity> findByTitleContains(String keyword);

    List<ArticleEntity> findByContentsContains(String keyword);
}
