package utils;

public enum PlatformscienceEnum {

    cleaning("/v1/cleaning-sessions");
    private String basePath;

    PlatformscienceEnum(String resource)
    {
        this.basePath=resource;
    }

    public String getBasePath()
    {
        return basePath;
    }

}
