package ccr.cleanarchisample.detail.viewmodel;

public class GitHubForkDetailViewModel {
    private final String title;
    private final String imageUrl;
    private final String description;

    public GitHubForkDetailViewModel(String title, String imageUrl, String description) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }
}
