package local.dw.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.NonNull;

public class ProfileInterest {
    @NonNull
    private int id;
    @NonNull
    private int profileId;
    @NonNull
    private String topic;
    @NonNull
    private boolean active;
    public ProfileInterest() {
    }

    public ProfileInterest(@NonNull int id, @NonNull int profileId, @NonNull final String topic, @NonNull boolean active) {
        super();
        this.id = id;
        this.profileId = profileId;
        this.topic = topic;
        this.active = active;
    }

    @JsonProperty
    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @JsonProperty
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ProfileSite{" +
                "id=" + id +
                ", profileId=" + profileId +
                ", topic=" + topic +
                ", active=" + active +
                "}";
    }
}
