package com.example.mentalawareness;

import java.util.ArrayList;

public class SourcesModel {

    public static class Cite {
        public String link;

        public Cite(String link) {
            this.link = link;
        }
    }

    public ArrayList<Cite> myCite;

    private SourcesModel() {
        myCite = new ArrayList<Cite>();
        loadModel();
    }

    private void loadModel() {
        myCite.add(new Cite("https://www.helpguide.org/articles/depression/coping-with-depression.htm"));
        myCite.add(new Cite("https://www.caba.org.uk/help-and-guides/information/how-spot-depression-others"));
    }

    private static SourcesModel singleton = null;
    public static SourcesModel getModel() {
        if(singleton == null) {
            singleton = new SourcesModel();
        }
        return singleton;
    }

}
