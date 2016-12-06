package edu.csumb.brogrammers.likestochill;

public class Movie {

    private String movieTitle;
    private String userId;

    public Movie(String movieTitle, String userId) {
        this.userId = userId;
        this.movieTitle = movieTitle;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieTitle='" + movieTitle + "'" +
                ", userId='" + userId + "'}";
    }
}
