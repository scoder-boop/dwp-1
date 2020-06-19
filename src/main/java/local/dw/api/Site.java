package local.dw.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.NonNull;

public class Site {
    @NonNull
    private int id;
    @NonNull
    private String siteType;
    @NonNull
    private String ConnectionString;
    @NonNull
    private String description;

    public Site() {
        super();
    }

    public Site(@NonNull int id, @NonNull String siteType, @NonNull String connectionString, String description) {
        super();
        this.id = id;
        this.siteType = siteType;
        ConnectionString = connectionString;
        this.description = description;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", siteType= " + siteType +
                ", ConnectionString= " + ConnectionString +
                ", description= " + description +
                "}";
    }
}
