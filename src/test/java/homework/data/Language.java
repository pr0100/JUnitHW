package homework.data;

public enum Language {
    English("Welcome to Wikipedia,"),
    Español("Bienvenidos a Wikipedia,");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
