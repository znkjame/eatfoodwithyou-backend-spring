package th.ac.ku.eatfoodwithyouspringbackend.service.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties()
public class StorageProperties {
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
