package models;

/**
 * Created by Guest on 1/22/18.
 */
public class Review {

    private String writtenBy;
    private int rating;
    private int idReview;
    private int idRestaurant2;
    private String content;

    public Review(String writtenBy, int rating, int idRestaurant2, String content) {
        this.writtenBy = writtenBy;
        this.rating = rating;
        this.idRestaurant2 = idRestaurant2;
        this.content = content;
    }

    // GETTER

    public String getWrittenBy() {
        return writtenBy;
    }

    public int getRating() {
        return rating;
    }

    public int getIdReview() {
        return idReview;
    }

    public int getRestaurantId() {
        return idRestaurant2;
    }

    public String getContent() {
        return content;
    }

    // SETTERS


    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
    }

    public void setRestaurantId(int idRestaurant2) {
        this.idRestaurant2 = idRestaurant2;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (rating != review.rating) return false;
        if (idRestaurant2 != review.idRestaurant2) return false;
        if (!writtenBy.equals(review.writtenBy)) return false;
        return content.equals(review.content);
    }

    @Override
    public int hashCode() {
        int result = writtenBy.hashCode();
        result = 31 * result + rating;
        result = 31 * result + idRestaurant2;
        result = 31 * result + content.hashCode();
        return result;
    }



}
