import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupPage {
    private JCheckBox swimmingCheckBox;
    private JCheckBox formalEventCheckBox;
    private JCheckBox businessMeetingCheckBox;
    private JCheckBox gymOrExerciseCheckBox;
    private JCheckBox visitingChineseFamilyCheckBox;
    private JTextField destinationTextField;
    private JTextField durationOfTripTextField;
    private JButton generateListButton;

    private JPanel panelMain;
    private JFrame frame;

    public SetupPage(JFrame frame){
        this.frame = frame;

        generateListButton.addActionListener(new ActionListener() {
            @Override
            //when the button is pressed, the list will be generated
            public void actionPerformed(ActionEvent e) {
                //the parameters for the new page include all the information from the checkboxes so that
                //they can be referenced later when creating the list
                frame.setContentPane(new ListGenerator(destinationTextField.getText(), swimmingCheckBox.isSelected(),
                        formalEventCheckBox.isSelected(), businessMeetingCheckBox.isSelected(),
                        gymOrExerciseCheckBox.isSelected(), visitingChineseFamilyCheckBox.isSelected(),
                        durationOfTripTextField.getText()).getMainPanel());

                frame.pack();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("setup page");
        //setting up the function in the setup page
        frame.setContentPane(new SetupPage(frame).panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
