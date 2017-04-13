package ccr.cleanarchisample.list.viewmodel;

public class GitHubForkItemViewModel {
    private final String id;
    private final String title;
    private final String urlImage;

    public GitHubForkItemViewModel(String id, String title, String urlImage) {
        this.id = id;
        this.title = title;
        this.urlImage = urlImage;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
