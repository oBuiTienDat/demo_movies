package com.gotasoft.movies.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dattien on 10/7/17.
 */
@Entity(nameInDb = "Detail")
public class Detail {
    @Id
    private String id;
    private String userId;
    private String nameEng;
    private String name;
    private String language;
    private String imdbRating;
    private String genre;
    private String director;
    private String actors;
    private String plot;
    private String active;
    private String categoryId;
    private String img;
    private String time;
    private String created;
    private String modified;
    private String timeRelease;
    private String tubeId;
    private String trailerId;
    private String hotMovie;
    private String poster;
    private String year;
    private String btnPlayTrailer;
    private Boolean isAdd;
    @Generated(hash = 1265833966)
    public Detail(String id, String userId, String nameEng, String name,
            String language, String imdbRating, String genre, String director,
            String actors, String plot, String active, String categoryId,
            String img, String time, String created, String modified,
            String timeRelease, String tubeId, String trailerId, String hotMovie,
            String poster, String year, String btnPlayTrailer, Boolean isAdd) {
        this.id = id;
        this.userId = userId;
        this.nameEng = nameEng;
        this.name = name;
        this.language = language;
        this.imdbRating = imdbRating;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.active = active;
        this.categoryId = categoryId;
        this.img = img;
        this.time = time;
        this.created = created;
        this.modified = modified;
        this.timeRelease = timeRelease;
        this.tubeId = tubeId;
        this.trailerId = trailerId;
        this.hotMovie = hotMovie;
        this.poster = poster;
        this.year = year;
        this.btnPlayTrailer = btnPlayTrailer;
        this.isAdd = isAdd;
    }
    @Generated(hash = 1665969126)
    public Detail() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getNameEng() {
        return this.nameEng;
    }
    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLanguage() {
        return this.language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getImdbRating() {
        return this.imdbRating;
    }
    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
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
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
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
    public String getTimeRelease() {
        return this.timeRelease;
    }
    public void setTimeRelease(String timeRelease) {
        this.timeRelease = timeRelease;
    }
    public String getTubeId() {
        return this.tubeId;
    }
    public void setTubeId(String tubeId) {
        this.tubeId = tubeId;
    }
    public String getTrailerId() {
        return this.trailerId;
    }
    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }
    public String getHotMovie() {
        return this.hotMovie;
    }
    public void setHotMovie(String hotMovie) {
        this.hotMovie = hotMovie;
    }
    public String getPoster() {
        return this.poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }
    public String getYear() {
        return this.year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getBtnPlayTrailer() {
        return this.btnPlayTrailer;
    }
    public void setBtnPlayTrailer(String btnPlayTrailer) {
        this.btnPlayTrailer = btnPlayTrailer;
    }
    public Boolean getIsAdd() {
        return this.isAdd;
    }
    public void setIsAdd(Boolean isAdd) {
        this.isAdd = isAdd;
    }
    


}
