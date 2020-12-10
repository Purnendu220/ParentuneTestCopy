package com.abpl.parentunetest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public  class BlogModel {
        @Expose
        @SerializedName("title")
        private String title;
        @Expose
        @SerializedName("ageGroup")
        private String agegroup;
        @Expose
        @SerializedName("userAvatar")
        private String useravatar;
        @Expose
        @SerializedName("userName")
        private String username;
        @Expose
        @SerializedName("image")
        private String image;
        @Expose
        @SerializedName("topic")
        private String topic;
        @Expose
        @SerializedName("id")
        private int id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAgegroup() {
            return agegroup;
        }

        public void setAgegroup(String agegroup) {
            this.agegroup = agegroup;
        }

        public String getUseravatar() {
            return useravatar;
        }

        public void setUseravatar(String useravatar) {
            this.useravatar = useravatar;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

}
