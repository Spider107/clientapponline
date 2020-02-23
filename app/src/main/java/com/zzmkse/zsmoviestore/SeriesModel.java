package com.itonemm.clientapponline;

public class SeriesModel  {
    public String seriesName;
    public String seriesImage;
    public String seroesVideo;
    public String seriesCagegory;

    public SeriesModel(String seriesName, String seriesImage, String seroesVideo, String seriesCagegory) {
        this.seriesName = seriesName;
        this.seriesImage = seriesImage;
        this.seroesVideo = seroesVideo;
        this.seriesCagegory = seriesCagegory;
    }

    public SeriesModel() {

    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesImage() {
        return seriesImage;
    }

    public void setSeriesImage(String seriesImage) {
        this.seriesImage = seriesImage;
    }

    public String getSeroesVideo() {
        return seroesVideo;
    }

    public void setSeroesVideo(String seroesVideo) {
        this.seroesVideo = seroesVideo;
    }

    public String getSeriesCagegory() {
        return seriesCagegory;
    }

    public void setSeriesCagegory(String seriesCagegory) {
        this.seriesCagegory = seriesCagegory;
    }
}
