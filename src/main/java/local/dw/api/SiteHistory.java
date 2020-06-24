package local.dw.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.NonNull;

import java.sql.Timestamp;
import java.time.Instant;

public class SiteHistory {
    @NonNull
    private int id;
    @NonNull
    private int siteId;
    @NonNull
    private Timestamp eventTime;
    @NonNull
    private long eventTimeEpoch;
    private String event;

    public SiteHistory() {
        super();
    }

    public SiteHistory(int siteId, final String event) {
        super();
        this.siteId = siteId;
        this.event = event;
        this.eventTimeEpoch = Instant.now().getEpochSecond();
    }

    public SiteHistory(@NonNull int id, @NonNull int siteId, @NonNull Timestamp eventTime, @NonNull long eventTimeEpoch, final String event) {
        this.id = id;
        this.siteId = siteId;
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
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
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
        return "SiteHistory{" +
                "id=" + id +
                ", siteId=" + siteId +
                ", eventTime=" + eventTime +
                ", eventTimeEpoch=" + eventTimeEpoch +
                ", event=" + event +
                "}";
    }
}
