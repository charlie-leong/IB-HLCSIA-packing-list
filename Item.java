public class Item {
    private String name;
    private int quantity;

    public Item(String n, int d) {
        this.setName(n);
        this.setQuantity(n, d);
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param name, the full item name including the quantity specifications
     * @param duration, the trip's duration, used to calculate the final quantity
     */
    public void setQuantity(String name, int duration) {
        //checking if the quantity is 1 or 2
        //ie. divisible by duration and therefore has no base number
        if (name.contains("duration")) {
            if (name.contains("duration/2")) {
                this.quantity = 2;
            }else{
                this.quantity = 1;
            }
        } else {
            int base = 0;
            int addition = 0;
            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) == '(') {
                    //the number directly after an open bracket would be the base quantity
                    //converts the character after the bracket (the quantity base) to an integer to be divided
                    base = duration/(Integer.parseInt(String.valueOf(name.charAt(i + 1))));
                }
                //accessing and saving an additions or subtractions to the base quantity
                if (name.charAt(i) == '+') {
                    //the number after + should be added to the base quantity
                    addition = Integer.parseInt(String.valueOf(name.charAt(i + 1)));
                } else if (name.charAt(i) == '-') {
                    //the number after - should be saved as a negative number to be subtracted from the base quantity
                    addition = 0 - Integer.parseInt(String.valueOf(name.charAt(i + 1)));
                }
            }
            this.quantity = base + addition;
        }
        String nobracketname = "";
        //removing the quantity specifications from the end of the name
        for (int i = 0; i < name.length() && name.charAt(i) != '('; i++) {
            nobracketname += name.charAt(i);
        }//saving the new name so that the quantity specifications won't be displayed on the file or list
        this.setName(nobracketname);
    }

}
