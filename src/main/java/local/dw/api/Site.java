package local.dw.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public class Site {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String siteType;
    @NonNull
    private String ConnectionString;
    @NonNull
    private String description;
    private String siteAuthDetails;

    public Site() {
        super();
    }

    public Site(@NonNull int id, @NonNull final String name, @NonNull final String siteType,
                @NonNull final String connectionString, final String description, final String siteAuthDetails) {
        super();
        this.id = id;
        this.name = name;
        this.siteType = siteType;
        ConnectionString = connectionString;
        this.description = description;
        this.siteAuthDetails = siteAuthDetails;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @JsonProperty
    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(final String siteType) {
        this.siteType = siteType;
    }

    @JsonProperty
    public String getConnectionString() {
        return ConnectionString;
    }

    public void setConnectionString(final String connectionString) {
        ConnectionString = connectionString;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @JsonProperty
    public String getSiteAuthDetails() {
        return siteAuthDetails;
    }

    public void setSiteAuthDetails(String siteAuthDetails) {
        this.siteAuthDetails = siteAuthDetails;
    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", name=" + name +
                ", siteType=" + siteType +
                ", ConnectionString=" + ConnectionString +
                ", description=" + description +
                ", siteAuthDetails=" + siteAuthDetails +
                "}";
    }
}
