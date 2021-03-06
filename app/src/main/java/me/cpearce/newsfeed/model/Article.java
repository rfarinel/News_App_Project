package me.cpearce.newsfeed.model;

/**
 * Created by Christopher on 10/19/2017.
 * model for Articles
 */

public class Article {
    public final String sourceId;
    public final String sourceName;
    public final String author;
    public final String title;
    public final String description;
    public final String url;
    public final String urlToImage;
    public final String publishedAt;

    /**
     * Constructs a new {@link Article} object.
     *
     * @param sourceId   article source The identifier id
     * @param sourceName article source name
     * @param author      article author
     * @param title       article title
     * @param description article description
     * @param url         is the article url
     * @param urlToImage  is the image url
     * @param publishedAt is the date and time the article was published
     */
    public Article(String sourceId, String sourceName, String author, String title, String description, String url, String urlToImage,
                   String publishedAt) {
        this.sourceId = sourceId;
        this.sourceName = sourceName;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }
}
