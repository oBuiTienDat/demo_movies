package com.gotasoft.movies.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dattien on 9/17/17.
 */
@Entity(nameInDb = "Product")
public class Product {
    @Id
    private String id;
    private String name;
    private String userId;
    private String namengE;
    private String language;
    private String genre;
    private String director;
    private String actors;
    private String plot;
    private String active;
    private String categoryId;
    private String img;
    private String created;
    private String modified;
    private String count;
    private String tubeId;
    private String description;
    @Generated(hash = 672604618)
    public Product(String id, String name, String userId, String namengE,
            String language, String genre, String director, String actors,
            String plot, String active, String categoryId, String img,
            String created, String modified, String count, String tubeId,
            String description) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.namengE = namengE;
        this.language = language;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.active = active;
        this.categoryId = categoryId;
        this.img = img;
        this.created = created;
        this.modified = modified;
        this.count = count;
        this.tubeId = tubeId;
        this.description = description;
    }
    @Generated(hash = 1890278724)
    public Product() {
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
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getNamengE() {
        return this.namengE;
    }
    public void setNamengE(String namengE) {
        this.namengE = namengE;
    }
    public String getLanguage() {
        return this.language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getGenre() {
        return this.genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getDirector() {
        return this.director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getActors() {
        return this.actors;
    }
    public void setActors(String actors) {
        this.actors = actors;
    }
    public String getPlot() {
        return this.plot;
    }
    public void setPlot(String plot) {
        this.plot = plot;
    }
    public String getActive() {
        return this.active;
    }
    public void setActive(String active) {
        this.active = active;
    }
    public String getCategoryId() {
        return this.categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getCreated() {
        return this.created;
    }
    public void setCreated(String created) {
        this.created = created;
    }
    public String getModified() {
        return this.modified;
    }
    public void setModified(String modified) {
        this.modified = modified;
    }
    public String getCount() {
        return this.count;
    }
    public void setCount(String count) {
        this.count = count;
    }
    public String getTubeId() {
        return this.tubeId;
    }
    public void setTubeId(String tubeId) {
        this.tubeId = tubeId;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}
