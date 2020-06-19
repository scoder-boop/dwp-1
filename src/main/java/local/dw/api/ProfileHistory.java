package local.dw.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.NonNull;

import java.sql.Timestamp;
import java.time.Instant;

public class ProfileHistory {
    @NonNull
    private int id;
    @NonNull
    private int profileId;
    @NonNull
    private Timestamp eventTime;
    @NonNull
    private long eventTimeEpoch;
    private String event;

    public ProfileHistory() {
        super();
    }

    public ProfileHistory(int profileId, final String event) {
        super();
        this.profileId = profileId;
        this.event = event;
        this.eventTimeEpoch = Instant.now().getEpochSecond();
    }

    public ProfileHistory(@NonNull int id, @NonNull int profileId, @NonNull Timestamp eventTime, @NonNull long eventTimeEpoch, final String event) {
        this.id = id;
        this.profileId = profileId;
        this.eventTime = eventTime;
        this.eventTimeEpoch = eventTimeEpoch;
        this.event = event;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    @JsonProperty
    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(final Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    @JsonProperty
    public long getEventTimeEpoch() {
        return eventTimeEpoch;
    }

    public void setEventTimeEpoch(long eventTimeEpoch) {
        this.eventTimeEpoch = eventTimeEpoch;
    }

    @JsonProperty
    public String getEvent() {
        return event;
    }

    public void setEvent(final String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "ProfileHistory{" +
                "id=" + id +
                ", profileId=" + profileId +
                ", eventTime=" + eventTime +
                ", eventTimeEpoch=" + eventTimeEpoch +
                ", event=" + event +
                "}";
    }
}
