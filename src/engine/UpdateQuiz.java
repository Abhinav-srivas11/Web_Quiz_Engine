package engine;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UpdateQuiz {

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

    public UpdateQuiz() { }

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

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
}
