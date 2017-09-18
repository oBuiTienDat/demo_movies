package com.gotasoft.movies.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dattien on 9/17/17.
 */
@Entity(nameInDb = "Category")
public class Category {
    @Id
    private String id;
    private String name;
    private String isNew;
    private String thumb;
    private String description;
    private String lastMovie;
    @Generated(hash = 1134341762)
    public Category(String id, String name, String isNew, String thumb,
            String description, String lastMovie) {
        this.id = id;
        this.name = name;
        this.isNew = isNew;
        this.thumb = thumb;
        this.description = description;
        this.lastMovie = lastMovie;
    }
    @Generated(hash = 1150634039)
    public Category() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIsNew() {
        return this.isNew;
    }
    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }
    public String getThumb() {
        return this.thumb;
    }
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLastMovie() {
        return this.lastMovie;
    }
    public void setLastMovie(String lastMovie) {
        this.lastMovie = lastMovie;
    }

   
}
