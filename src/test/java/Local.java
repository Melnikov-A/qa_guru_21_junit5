public enum Local {
    EN("English"),
    IT("Italiano");
    private final String displayName;

    Local(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
