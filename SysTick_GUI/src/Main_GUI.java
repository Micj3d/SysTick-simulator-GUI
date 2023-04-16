import javax.swing.*;
import java.awt.*;
import java.awt.color.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class Main_GUI extends JFrame {

    static SysTick sysTick = new SysTick();
    static Generator generator = new Generator();

    private JPanel windowPanel;
    private JCheckBox sourceInternalCheckBox;
    private JCheckBox interruptCheckBox;
    private JCheckBox enableCheckBox;
    private JCheckBox sourceIsExternalCheckBox;
    private JCheckBox interruptCheckBox1;
    private JCheckBox enableCheckBox1;
    private JCheckBox countFlagCheckBox;
    private JButton generatorBiegajButton;
    private JButton singleTickButton;
    private JSpinner csrSpinner;
    private JSpinner rvrSpinner;
    private JSpinner cvrSpinner;
    private JSlider slider1;
    private JCheckBox sourceExternalCheckBox;
    private JLabel cvrLabel;
    private JLabel rvrLabel;
    private JComboBox generatorComboBox;
    private JLabel csrLabel;
    private JCheckBox sourceIsInternalCheck;
    private JLabel ledLable;
    private JRadioButton diodeButton;


    public Main_GUI() {

        sourceInternalCheckBox.setSelected(true);
        sourceInternalCheckBox.setEnabled(false);


        generator.start();

        generator.addActionListener(e -> {

            if (sysTick.getEnabled() && generator.getMode() == 0){

                if(generator.getPulseCout() > 0){

                    sysTick.tickInternal();

                    generator.pulseCount--;
                }


            }else if(sysTick.getEnabled() && generator.getMode() == 1){

                sysTick.tickInternal();
            }

            cvrLabel.setText(String.valueOf(sysTick.getCVR()));

            if(sysTick.isCountFlag()){

                countFlagCheckBox.setSelected(true);

            }else{

                countFlagCheckBox.setSelected(false);
            }

        });
        JFormattedTextField cvrSpinnerTextField = ((JSpinner.DefaultEditor) cvrSpinner.getEditor()).getTextField(); //Jspinner text field disable
        cvrSpinnerTextField.setEditable(false);

        JFormattedTextField rvrSpinnerTextField = ((JSpinner.DefaultEditor) rvrSpinner.getEditor()).getTextField();
        rvrSpinnerTextField.setEditable(false);

        JFormattedTextField csrSpinnerTextField = ((JSpinner.DefaultEditor) csrSpinner.getEditor()).getTextField();
        csrSpinnerTextField.setEditable(false);

        {
            sourceIsExternalCheckBox.setEnabled(false); //disables value check boxes

            interruptCheckBox1.setEnabled(false);

            enableCheckBox1.setEnabled(false);

            sourceIsInternalCheck.setEnabled(false);
        }

        countFlagCheckBox.setEnabled(false);

        cvrLabel.setText(String.valueOf(sysTick.cvr));

        rvrLabel.setText(String.valueOf(sysTick.rvr));

        csrLabel.setText(String.valueOf(sysTick.csr));

        //enableCheckBox1.setSelected(true);
       sourceInternalCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(sourceInternalCheckBox.isSelected()){

                    sysTick.setSourceInternal();

                    sourceIsInternalCheck.setSelected(true);

                    sourceIsExternalCheckBox.setSelected(false);

                    sourceExternalCheckBox.setEnabled(true);

                    sourceInternalCheckBox.setEnabled(false);

                    sourceExternalCheckBox.setSelected(false);

                    //sourceInternalCheckBox.setSelected(false);

                    System.out.println("tick int: " + sysTick.getSource());

                }else{

                    sysTick.setSourceExternal();

                    sourceIsInternalCheck.setSelected(false);

                    System.out.println("tick int: " + sysTick.getSource());
                }
            }


        });
        generatorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(generatorComboBox.getSelectedIndex() == 0){

                    generator.setMode(PulseSource.BURST_MODE);

                    System.out.println(generator.getMode());

                }else if(generatorComboBox.getSelectedIndex() == 1){

                    generator.setMode(PulseSource.CONTINOUS_MODE);

                    System.out.println("tryp generator = " + generator.getMode());
                }
            }
        });

        singleTickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sysTick.tickExternal();

                cvrLabel.setText(String.valueOf(sysTick.getCVR()));


                if(sysTick.isCountFlag()){

                    countFlagCheckBox.setSelected(true);

                }else{

                    countFlagCheckBox.setSelected(false);
                }

                System.out.println("single tick");
            }
        });

        rvrSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                sysTick.setRVR((Integer) rvrSpinner.getValue());

                rvrLabel.setText(String.valueOf(sysTick.rvr));

                System.out.println("rvr value = " + sysTick.getRVR());

            }
        });

        sourceExternalCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(sourceExternalCheckBox.isSelected()){

                    sysTick.setSourceExternal();

                    sourceIsExternalCheckBox.setSelected(true);

                    sourceIsInternalCheck.setSelected(false);

                    sourceExternalCheckBox.setEnabled(false);

                    sourceInternalCheckBox.setEnabled(true);

                    sourceInternalCheckBox.setSelected(false);

                    System.out.println("tick ext: " + sysTick.getSource());

                }else{

                    sysTick.setSourceInternal();

                    sourceIsExternalCheckBox.setSelected(false);

                    System.out.println("tick ext: " + sysTick.getSource());
                }
            }
        });

        interruptCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(interruptCheckBox.isSelected()){

                    sysTick.setInterruptEnable();

                    interruptCheckBox1.setSelected(true);


                    System.out.println("interrupt = " + sysTick.getInterrupt());
                }else{

                    sysTick.setInterruptDisable();

                    interruptCheckBox1.setSelected(false);

                    System.out.println("interrupt = " + sysTick.getInterrupt());
                }

            }
        });
        enableCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(enableCheckBox.isSelected()){

                    sysTick.setEnable();

                    enableCheckBox1.setSelected(true);

                    System.out.println("enable = " + sysTick.getEnabled());
                }else{

                    sysTick.setDisable();

                    enableCheckBox1.setSelected(false);

                    System.out.println("enable = " + sysTick.getEnabled());
                }

            }
        });
        cvrSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                sysTick.setCVR((Integer) cvrSpinner.getValue());

                cvrLabel.setText(String.valueOf(sysTick.cvr));

                System.out.println("cvr value = " + sysTick.getCVR());
//
//                sysTick.setRVR((Integer) rvrSpinner.getValue());
//
//                rvrLabel.setText(String.valueOf(sysTick.rvr));
//
//                System.out.println("rvr value = " + sysTick.getRVR());

            }
        });

        csrSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                sysTick.setCSR((Integer) csrSpinner.getValue());

                csrLabel.setText(String.valueOf(sysTick.csr));

                System.out.println("csr value = " + sysTick.getCSR());

            }
        });
        generatorBiegajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(generatorBiegajButton.getText() == "On"){

                    generatorBiegajButton.setText("Off");

                    ledLable.setForeground(Color.GREEN);

                    generator.startGeneration();

                    if(generator.getMode() == 0){


                        generator.setPulseCount(slider1.getValue() +1);

                    }else if(generator.getMode() == 1){


                    }

                    System.out.println("generator = " + generator.on);

                }else if(generatorBiegajButton.getText() == "Off"){

                    generatorBiegajButton.setText("On");

                    ledLable.setForeground(Color.RED);

                    generator.stopGeneration();

                    System.out.println("generator = " + generator.on);
                }
            }
        });
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                generator.setPulseCount(slider1.getValue());

                System.out.println("Pulse count = " + generator.getPulseCout());
            }
        });
    }

    public static void main(String[] args) {

        //JFrame window = new JFrame();//creating instance of JFrame
        //window.setDefaultCloseOperation(JFrame.c);

        //window.add();//adding button in JFrame

        //window.setSize(400,500);//400 width and 500 height
        //window.setLayout(null);//using no layout managers
        //window.setVisible(true);//making the frame visible

//        Main_GUI.setLayout(null);
//        frame.setContentPane(mainPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//        frame.setSize(1500,700);

        Main_GUI h = new Main_GUI();
        h.setContentPane(h.windowPanel);
        h.setTitle("Systick GUI");
        h.setSize(600, 600);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


}
