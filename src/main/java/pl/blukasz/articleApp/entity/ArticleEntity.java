package pl.blukasz.articleApp.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;
    private LocalDate publicationDate;
    private String paperName;
    private String authorFirstname;
    private String authorLastname;
    private LocalDateTime edited;

    public ArticleEntity() {
    }

    public ArticleEntity(String title, String contents, String paperName, String authorFirstname, String authorLastname, LocalDate publicationDate) {
        this.title = title;
        this.contents = contents;
        this.paperName = paperName;
        this.authorFirstname = authorFirstname;
        this.authorLastname = authorLastname;
        this.publicationDate = publicationDate;

    }

    public ArticleEntity(Long id, String title, String contents, String paperName, String authorFirstname, String authorLastname, LocalDate publicationDate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.publicationDate = publicationDate;
        this.paperName = paperName;
        this.authorFirstname = authorFirstname;
        this.authorLastname = authorLastname;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getAuthorFirstname() {
        return authorFirstname;
    }

    public void setAuthorFirstname(String authorFirstname) {
        this.authorFirstname = authorFirstname;
    }

    public String getAuthorLastname() {
        return authorLastname;
    }

    public void setAuthorLastname(String authorLastname) {
        this.authorLastname = authorLastname;
    }

    public LocalDateTime getEdited() {
        return this.edited;
    }

    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(ArticleEntity.class)){
        ArticleEntity article = (ArticleEntity) obj;
        return this.title.equals(article.title) && this.contents.equals(article.contents) && this.paperName.equals(article.paperName) && this.authorFirstname.equals(article.authorFirstname) && this.authorLastname.equals(article.authorLastname) && this.publicationDate.equals(article.publicationDate);
    }
        return false;
    }


}
