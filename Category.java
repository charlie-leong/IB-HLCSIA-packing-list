public class Category {
    private String key;
    private String name;
    private String[] items;
    private int duration;


    public Category(String key, String name){
        this.key = key;
        this.name = this.splitItems(key)[0];
        this.duration = duration;
        this.items = this.splitItems(key);


    }
    // returns the number of items given the category name
    public int numOfItems(String name){
        String[] items = splitItems(name);
        int num = items.length;
        return num;
    }

    private String[] splitItems(String name){
        //splitting a category into an array of individual items, split by commas in between each item
        propertiesFile file = new propertiesFile();
        String[] items = ((String)(file.accessProperty(name))).split(",");
        return items;
    }

    public String getItem(int index){
        return items[index];
    }

}


