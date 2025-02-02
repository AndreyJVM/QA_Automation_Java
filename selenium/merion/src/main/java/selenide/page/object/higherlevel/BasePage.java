package selenide.page.object.higherlevel;

public abstract class BasePage {
    private HeaderElement header;

    public BasePage() {
        this.header = new HeaderElement();
    }

    public HeaderElement getHeader() {
        return this.header;
    }

}