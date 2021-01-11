package engine;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {
    private boolean success;
    private String feedback;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int[] answer;
    public Answer() {
    }

    public Answer(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer.clone();
    }
}
