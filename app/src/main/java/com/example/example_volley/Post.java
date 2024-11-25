package com.example.example_volley;

import androidx.annotation.NonNull;

public class Post {
    int id;
    String title;
    String body;

    public Post(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @NonNull
    @Override
    public String toString() {
        return "POST "+id+"{ " +
                "\ntitle: "+title+", " +
                "\nbody: "+body+"\n}";
    }
}

