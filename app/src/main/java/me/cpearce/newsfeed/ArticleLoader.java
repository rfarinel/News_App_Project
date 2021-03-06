package me.cpearce.newsfeed;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.cpearce.newsfeed.model.Article;
import me.cpearce.newsfeed.model.Entity;
import me.cpearce.newsfeed.model.Source;

/**
 * Loads a list of articles by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class ArticleLoader extends AsyncTaskLoader<List<Article>> {

    /** Tag for log messages */
    private static final String LOG_TAG = ArticleLoader.class.getName();

    private static final int ARTICLES_MAX_SIZE = 20;

    /** Query URL */
    private String mNewsUrl;
    /** Query URL */
    private String mMLUrl;
    private String mSourceUrl;

    /**
     * Constructs a new {@link ArticleLoader}.
     *
     * @param context of the activity
     * @param newsUrl to load data from news
     * @param mlUrl to load data from google
     */
    public ArticleLoader(Context context, String newsUrl, String mlUrl, String sourceUrl) {
        super(context);
        mNewsUrl = newsUrl;
        mMLUrl = mlUrl;
        mSourceUrl = sourceUrl;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Article> loadInBackground() {
        if (mNewsUrl == null) {
            return null;
        }

        List<Source> sources = QueryUtils.fetchSourceData(mSourceUrl);


        // max possible sources that can be queried at once are 5
        // commenting this out because it returns several thousand articles all at once
//        List<List<Source>> partitions = new ArrayList<>();
//
//        for (int i = 0; i < sources.size(); i+= 5){
//            partitions.add(sources.subList(i, Math.min(i + 5, sources.size())));
//        }
//        List<Article> articles = new ArrayList<>();
//        for (List<Source> list : partitions) {
//            articles.addAll(QueryUtils.fetchArticleData(mNewsUrl, list));
//        }
        List<Article> articles = new ArrayList<>();

        articles.addAll(QueryUtils.fetchArticleData(mNewsUrl,((sources.size() < 5) ? sources : sources.subList(0,4))));


        // Perform the network request, parse the response, and extract a list of articles.

        // query entities here, currently not doing anything with entities
        // TODO: setup frontend display with entities and articles
//        List<Entity> entities = new ArrayList<>();
//        for (int i = 0; i < articles.size(); i++) {
//            Article article = articles.get(i);
//            entities.addAll(QueryUtils.fetchEntityData(mMLUrl, article.description));
//        }
        return articles;
    }
}
