import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ListGenerator {
    private JLabel destinationLabel;
    private JLabel weatherConditionLabel;
    private JLabel temperatureLabel;
    private JLabel preLeavingLabel;
    private JPanel preLeavingPanel;
    private JPanel planePanel;
    private JLabel planeLabel;
    private JLabel airportLabel;
    private JPanel airportPanel;
    private JLabel periodlabel;
    private JPanel periodPanel;
    private JLabel art;
    private JPanel artPanel;
    private JLabel deviceLabel;
    private JPanel devicePanel;
    private JLabel cameraLabel;
    private JPanel cameraPanel;
    private JPanel mainPanel;
    private JPanel makeupPanel;
    private JLabel basicsLabel;
    private JPanel basicsPanel;
    private JPanel creamsPanel;
    private JPanel toiletriesPanel;
    private JLabel toiletriesLabel;
    private JLabel creamsLabel;
    private JLabel makeupLabel;
    private JLabel clothesLabel;
    private JPanel generalClothesPanel;
    private JPanel conditionalClothesPanel;
    private JPanel temperatureClothesPanel;
    private JPanel Panel;
    private JScrollPane ScrollPane;

    /**
     *
     * @param destination - the trip destination, used to check the weather for the clothing section
     * @param swimming - whether the user intends to go swimming on the trip
     * @param formal - whether the user will be attending a formal event during the trip
     * @param business - whether the trip will include some sort of business meeting
     * @param gym - whether the user will be doing exercise or going to a gym during the trip
     * @param family - whether the user is planning on visiting family during the trip
     * @param duration - the duration of the trip, used to calculate the amount of clothes required on the trip
     */
    public ListGenerator(String destination, boolean swimming, boolean formal,
                         boolean business, boolean gym, boolean family, String duration) {
        //retrieves and saves the weather and weather category in the destination
        WeatherRetriever weather = new WeatherRetriever();
        String tempcat = weather.getTempCategory(destination);

        destinationLabel.setText(destination);
        weatherConditionLabel.setText(weather.getWeatherCondition(destination));
        temperatureLabel.setText(" the current temperature in "+ destination +" is " + weather.getTemperature(destination));

        int d = Integer.parseInt(duration);

        Category weatherClothes = new Category(tempcat, tempcat);
        Category basicClothes = new Category("avoid_indecency", "avoid_indecency");
        addToTextFile("clothing");
        generalClothesPanel.setLayout(new GridLayout(weatherClothes.numOfItems(tempcat)
                +basicClothes.numOfItems("avoid_indecency"), 1));
        temperatureClothesPanel.setLayout(new GridLayout(weatherClothes.numOfItems(tempcat), 1));
        for (int i = 0; i < basicClothes.numOfItems("avoid_indecency"); i++) {
            String item = basicClothes.getItem(i);
            Item c = new Item(item, d);
            c.setQuantity(item, d);
            String quantity = Integer.toString(c.getQuantity());
            JCheckBox box = new JCheckBox();
            String yes = c.getName()+ " " + quantity;
            addToTextFile(yes);

            box.setText(yes);
            generalClothesPanel.add(box);
        }
        for (int i = 0; i < weatherClothes.numOfItems(tempcat); i++) {
            String item = weatherClothes.getItem(i);
            Item c = new Item(item, d);
            c.setQuantity(item, d);
            String quantity = Integer.toString(c.getQuantity());
            JCheckBox box = new JCheckBox();
            String yes = c.getName()+ " " + quantity;
            addToTextFile(yes);

            box.setText(yes);
            generalClothesPanel.add(box);
        }

        //checking how many initial rows should be added to the panel
        //based on which checkboxes have been selected in the setup page
        int rows = 0;
        if (swimming) {
            rows += 1;
        }
        if (formal) {
            rows += 1;
        }
        if (business) {
            rows += 1;
        }
        if (gym) {
            rows += 1;
        }
        if (family) {
            rows += 1;
        }

        //conditionalClothesPanel holds all the clothes that may be determined by checkboxes
        //
        conditionalClothesPanel.setLayout(new GridLayout(rows, 1));

        //listing the items for each category that has been checked
        if (swimming) {
            //creating a new category and label for swimming
            //this process is repeated for all the conditional clothing categories
            Category swimCategory = new Category("swim", "swim");
            JLabel swimLabel = new JLabel();
            swimLabel.setText("swimming");
            conditionalClothesPanel.add(swimLabel);
            createBoxes("swim", "swimming gear", conditionalClothesPanel);
            //making space for the new boxes
            conditionalClothesPanel.setLayout(new GridLayout(rows += swimCategory.numOfItems("swim"), 1));
        }
        if (formal) {
            Category formalCategory = new Category("event", "event");
            JLabel formalLabel = new JLabel();
            formalLabel.setText("formal");
            conditionalClothesPanel.add(formalLabel);
            createBoxes("event", "förmal", conditionalClothesPanel);
            conditionalClothesPanel.setLayout(new GridLayout(rows += formalCategory.numOfItems("event"), 1));
        }
        if (business) {
            Category businessCat = new Category("business", "business");
            JLabel businessLabel = new JLabel();
            businessLabel.setText("business");
            conditionalClothesPanel.add(businessLabel);
            createBoxes("business", "business", conditionalClothesPanel);
            conditionalClothesPanel.setLayout(new GridLayout(rows += businessCat.numOfItems("business"), 1));
        }
        if (gym) {
            Category gymCat = new Category("exercise", "exercise");

            JLabel gymLabel = new JLabel();
            gymLabel.setText("exercise");
            conditionalClothesPanel.add(gymLabel);
            createBoxes("exercise", "gym rat", conditionalClothesPanel);
            conditionalClothesPanel.setLayout(new GridLayout(rows += gymCat.numOfItems("exercise"), 1));
        }
        if (family) {
            addToTextFile("\nvisiting family");
            Category familyCat = new Category("visiting_family", "visiting_family");
            JLabel familyLabel = new JLabel();
            familyLabel.setText("visiting_family");
            conditionalClothesPanel.add(familyLabel);
            createBoxes("visiting_family", "visiting chinese family", conditionalClothesPanel);
            conditionalClothesPanel.setLayout(new GridLayout(rows += familyCat.numOfItems("visiting_family"), 1));
        }

        // setting the unconditional categories
        createBoxes("toiletries", "\ntoiletries", toiletriesPanel);
        createBoxes("creams", "creams", creamsPanel);
        createBoxes("basics", "basics", basicsPanel);
        createBoxes("makeup", "makeup", makeupPanel);
        createBoxes("camera", "\ncamera", cameraPanel);
        createBoxes("device_chargers", "device_chargers", devicePanel);
        createBoxes("art", "art", artPanel);
        createBoxes("period", "MENSTRUATION", periodPanel);

        createBoxes("pre_leaving_room", "\nthings to do before leaving room", preLeavingPanel);
        createBoxes("airport", "airport", airportPanel);
        createBoxes("plane", "plane", planePanel);

    }

    public JPanel getMainPanel() {
        return Panel;
    }

    public static StringBuilder sb = new StringBuilder();

    /**
     *
     * @param contents, the text that will be added to the final packing list file
     */
    public void addToTextFile(String contents){
        propertiesFile fileAccess = new propertiesFile();
        //the try/ catch attempts to create and save the "packing list" csv file
        try (FileWriter writer = new FileWriter(fileAccess.getDirectory("packing list"), true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            //creates a new line in the "packing list" file and adds the string in the variable "contents"
            bw.write("\n"+sb + contents);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    /**
     *
     * @param key, provides the key to access each category
     * @param label, what the category will be named on the saved packing list, includes spacing
     * @param panel, the name of each category's panel so that check boxes can be added to them
     */
    public void createBoxes(String key, String label, JPanel panel){
        Category cat = new Category(key, key);
        //adding the category title to the final packing document
        addToTextFile("\n" + label);
        panel.setLayout(new GridLayout(cat.numOfItems(key), 1));
        // iterating through each category and adding each item to the packing document
        // as well as giving each one a separate checkbox under the category
        for (int i = 0; i < cat.numOfItems(key); i++) {
            JCheckBox box = new JCheckBox();
            box.setText(cat.getItem(i));
            panel.add(box);
            addToTextFile(cat.getItem(i));
        }
    }

}
