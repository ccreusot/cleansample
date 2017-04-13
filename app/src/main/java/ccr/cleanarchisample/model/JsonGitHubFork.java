package ccr.cleanarchisample.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_JsonGitHubFork.Builder.class)
public abstract class JsonGitHubFork implements GitHubFork {

    @Override
    public String getTitle() {
        return getFullName();
    }

    @Override
    public String getUrlImage() {
        String urlImage = null;
        if (getOwner() != null) {
            urlImage = getOwner().getAvatarUrl();
        }
        return urlImage;
    }

    @JsonProperty("full_name")
    abstract String getFullName();

    @JsonProperty("owner")
    abstract JsonGitHubForkOwner getOwner();

    @AutoValue.Builder
    public abstract static class Builder {
        @JsonProperty("id")
        abstract Builder setId(String id);

        @JsonProperty("full_name")
        abstract Builder setFullName(String fullName);

        @JsonProperty("description")
        abstract Builder setDescription(String description);

        @JsonProperty("owner")
        abstract Builder setOwner(JsonGitHubForkOwner owner);

        abstract JsonGitHubFork build();
    }
}
