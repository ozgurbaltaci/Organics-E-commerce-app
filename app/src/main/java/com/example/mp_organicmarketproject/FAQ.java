package com.example.mp_organicmarketproject;

public class FAQ {
    private String question;
    private String answer;
    private boolean isExpanded;

    public FAQ() {
    }

    public FAQ(String question, String answer) {
        this.question = question;
        this.answer = answer;
        isExpanded = false;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getAnswer() {
        return answer;
    }
}
