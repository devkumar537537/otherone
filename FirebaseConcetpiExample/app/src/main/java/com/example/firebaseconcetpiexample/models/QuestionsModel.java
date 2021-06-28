package com.example.firebaseconcetpiexample.models;

public class QuestionsModel {
    private String firstOption;
    private String secondOption;
    private String thirdOption;
    private String fourthOption;
    private String question;
    private String questionNumber;
    private String rightresponse;
    private String topic;

    public QuestionsModel(String firstOption, String secondOption, String thirdOption, String fourthOption, String question, String questionNumber, String rightresponse, String topic) {
        this.firstOption = firstOption;
        this.secondOption = secondOption;
        this.thirdOption = thirdOption;
        this.fourthOption = fourthOption;
        this.question = question;
        this.questionNumber = questionNumber;
        this.rightresponse = rightresponse;
        this.topic = topic;
    }

    public QuestionsModel() {
    }

    public String getFirstOption() {
        return firstOption;
    }

    public void setFirstOption(String firstOption) {
        this.firstOption = firstOption;
    }

    public String getSecondOption() {
        return secondOption;
    }

    public void setSecondOption(String secondOption) {
        this.secondOption = secondOption;
    }

    public String getThirdOption() {
        return thirdOption;
    }

    public void setThirdOption(String thirdOption) {
        this.thirdOption = thirdOption;
    }

    public String getFourthOption() {
        return fourthOption;
    }

    public void setFourthOption(String fourthOption) {
        this.fourthOption = fourthOption;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getRightresponse() {
        return rightresponse;
    }

    public void setRightresponse(String rightresponse) {
        this.rightresponse = rightresponse;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
