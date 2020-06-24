package local.dw.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.NonNull;

public class Site {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String siteType;
    @NonNull
    private String connectionString;
    @NonNull
    private String description;
    private String siteAuthDetails;
    private String acceptXpath;
    private boolean useable;

    public Site() {
        super();
    }

    public Site(@NonNull int id, @NonNull final String name, @NonNull final String siteType,
                @NonNull final String connectionString, final String description, final String siteAuthDetails,
                final String acceptXpath, boolean useable) {
        super();
        this.id = id;
        this.name = name;
        this.siteType = siteType;
        this.connectionString = connectionString;
        this.description = description;
        this.siteAuthDetails = siteAuthDetails;
        this.acceptXpath = acceptXpath;
        this.useable = useable;
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
        return connectionString;
    }

    public void setConnectionString(final String connectionString) {
        this.connectionString = connectionString;
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

    public String getAcceptXpath() {
        return acceptXpath;
    }

    public void setAcceptXpath(String acceptXpath) {
        this.acceptXpath = acceptXpath;
    }

    public boolean isUseable() {
        return useable;
    }

    public void setUseable(boolean useable) {
        this.useable = useable;
    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", name=" + name +
                ", siteType=" + siteType +
                ", ConnectionString=" + connectionString +
                ", description=" + description +
                ", siteAuthDetails=" + siteAuthDetails +
                ", acceptXpath=" + acceptXpath +
                ", useable=" + useable +
                "}";
    }
}
