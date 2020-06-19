package local.dw.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.NonNull;

public class ProfileSite {
    @NonNull
    private int profileId;
    @NonNull
    private int siteId;
    @NonNull
    private FrequencyType frequencyType;
    @NonNull
    private int frequency;
    @NonNull
    private String loginString;
    @NonNull
    private boolean active;
    @NonNull
    private boolean passwordRequired;
    @NonNull
    private String password;

    public ProfileSite() {
    }

    public ProfileSite(@NonNull int profileId, @NonNull int siteId, @NonNull FrequencyType frequencyType, @NonNull int frequency,
                       @NonNull String loginString, @NonNull boolean active, @NonNull boolean passwordRequired, String password) {
        this.profileId = profileId;
        this.siteId = siteId;
        this.frequencyType = frequencyType;
        this.frequency = frequency;
        this.loginString = loginString;
        this.active = active;
        this.passwordRequired = passwordRequired;
        this.password = password;
    }

    @JsonProperty
    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    @JsonProperty
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @JsonProperty
    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(final FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    }

    @JsonProperty
    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @JsonProperty
    public String getLoginString() {
        return loginString;
    }

    public void setLoginString(final String loginString) {
        this.loginString = loginString;
    }

    @JsonProperty
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @JsonProperty
    public boolean isPasswordRequired() {
        return passwordRequired;
    }

    public void setPasswordRequired(boolean passwordRequired) {
        this.passwordRequired = passwordRequired;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ProfileSite{" +
                "profileId=" + profileId +
                ", siteId=" + siteId +
                ", frequencyType=" + frequencyType +
                ", frequency=" + frequency +
                ", loginString=" + loginString +
                ", passwordRequired=" + passwordRequired +
                ", password=" + password +
                "}";
    }
}