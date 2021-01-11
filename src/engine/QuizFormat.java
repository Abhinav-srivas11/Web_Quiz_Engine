package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

//@Entity
//@ResponseStatus(HttpStatus.NOT_FOUND)
//@Table(name = "quizzes")
//public class QuizFormat {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    //Id and GeneratedValue annotations tell that the id column is the primary key for this entity
//    private long id;
//    @NotBlank
//    @Column(unique = true)
//    private String title;
//    @NotBlank
//    private String text;
//    @NotEmpty(message = "options may not be empty")
//    @Size(min = 2, message = "There must be atleast 2 options")
//    private String[] options;
//
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private int[] answer;
//
//    public QuizFormat() {
//    }
//
//    public QuizFormat (long id, String title, String text, String[] options, int[] answer) {
//        this.id = id;
//        this.title = title;
//        this.text = text;
//        this.options = options;
//        this.answer = answer;
//    }
//    @Column (name = "title")
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//    @Column (name = "quiz_text")
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//    @Column (name = "quiz_options")
//    public String[] getOptions() {
//        return options;
//    }
//
//    public void setOptions(String[] options) {
//        this.options = options;
//    }
////    @Column (name = "quiz_id")
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//    @Column (name = "answers")
//    public int[] getAnswer() {
//        return answer;
//    }
//
//    public void setAnswer(int[] answer) {
//        this.answer = answer;
//    }
//}


@Entity
public class QuizFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Text is mandatory")
    private String text;

    @NotNull(message = "Options shouldn't be null")
    @Size(min = 2, message = "Minimal size should be 2")
    @ElementCollection
    private List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int[] answer = new int[0];

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    protected QuizFormat() { }

    public QuizFormat(String title, String text, List<String> options, int[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer.clone();
    }

    public Long getId() {
        return id;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    //    @JsonIgnore
    public int[] getAnswer() {
        return answer;
    }

    //    @JsonProperty
    public void setAnswer(int[] answer) {
        this.answer = answer.clone();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}