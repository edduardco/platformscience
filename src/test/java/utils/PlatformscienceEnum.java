package utils;

public enum PlatformscienceEnum {

    cleaning("/v1/cleaning-sessions");
    private String resource;

    PlatformscienceEnum(String resource)
    {
        this.resource=resource;
    }

    public String getResource()
    {
        return resource;
    }

}
