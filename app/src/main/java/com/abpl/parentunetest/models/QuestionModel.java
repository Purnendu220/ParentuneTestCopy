package com.abpl.parentunetest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public  class QuestionModel {

        @Expose
        @SerializedName("expertDesignation")
        private String expertdesignation;
        @Expose
        @SerializedName("expertAvatar")
        private String expertavatar;
        @Expose
        @SerializedName("expertName")
        private String expertname;
        @Expose
        @SerializedName("answer")
        private String answer;
        @Expose
        @SerializedName("question")
        private String question;
        @Expose
        @SerializedName("id")
        private int id;

        public String getExpertdesignation() {
            return expertdesignation;
        }

        public void setExpertdesignation(String expertdesignation) {
            this.expertdesignation = expertdesignation;
        }

        public String getExpertavatar() {
            return expertavatar;
        }

        public void setExpertavatar(String expertavatar) {
            this.expertavatar = expertavatar;
        }

        public String getExpertname() {
            return expertname;
        }

        public void setExpertname(String expertname) {
            this.expertname = expertname;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

}
