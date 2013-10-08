
package se.slide.sgu.db;

import android.content.Context;

import se.slide.sgu.model.Content;
import se.slide.sgu.model.Section;

import java.sql.SQLException;
import java.util.List;

public class DatabaseManager {
    static private DatabaseManager instance;
    private DatabaseHelper helper;

    static public void init(Context ctx) {
        if (instance == null) {
            instance = new DatabaseManager(ctx.getApplicationContext());
        }
    }

    static public DatabaseManager getInstance() {
        return instance;
    }

    private DatabaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }

    public List<Content> getPremiumContents() {
        List<Content> listOfContent = null;
        try {
            listOfContent = getHelper().getContentDao().query(getHelper().getContentDao().queryBuilder().where().like("title", "%SGU Premium%").prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfContent;
    }
    
    public List<Content> getAdFreeContents() {
        List<Content> listOfContent = null;
        try {
            listOfContent = getHelper().getContentDao().query(getHelper().getContentDao().queryBuilder().where().like("title", "%The Skeptics Guide%").prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfContent;
    }
    
    public List<Content> getAllContents() {
        List<Content> listOfContent = null;
        try {
            listOfContent = getHelper().getContentDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfContent;
    }

    public void addContent(Content content) {
        try {
            getHelper().getContentDao().createOrUpdate(content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addContent(List<Content> listOfContent) {
        try {
            for (Content content : listOfContent) {
                getHelper().getContentDao().createOrUpdate(content);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Section
     */
    
    public List<Section> getSection(String mp3) {
        List<Section> listOfSection = null;
        try {
            listOfSection = getHelper().getSectionDao().query(getHelper().getSectionDao().queryBuilder().where().like("mp3", mp3).prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfSection;
    }
    
    public void addSection(List<Section> listOfSection) {
        try {
            for (Section section : listOfSection) {
                getHelper().getSectionDao().createOrUpdate(section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
