package ccr.cleanarchisample.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_JsonGitHubForkOwner.Builder.class)
public abstract class JsonGitHubForkOwner {

    @JsonProperty("avatar_url")
    abstract String getAvatarUrl();

    @AutoValue.Builder
    public abstract static class Builder {
        @JsonProperty("avatar_url")
        abstract Builder setAvatarUrl(String avatarUrl);

        abstract JsonGitHubForkOwner build();
    }
}
