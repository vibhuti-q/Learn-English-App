package english.englishgrammar.app.Models;

public class ConversationModel {

    private String id, name;

    public ConversationModel() {
    }

    public ConversationModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
