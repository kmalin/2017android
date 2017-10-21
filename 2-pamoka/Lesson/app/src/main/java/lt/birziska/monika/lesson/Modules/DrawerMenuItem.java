package lt.birziska.monika.lesson.Modules;

public class DrawerMenuItem {
    private String title;
    private String tag;

    public DrawerMenuItem(String title, String id){
        this.title = title;
        this.tag = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
