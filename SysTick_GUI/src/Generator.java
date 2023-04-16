import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Generator extends Thread implements PulseSource {
    ActionListener listener;

    boolean alive;
    boolean on = false;
    byte mode = 0;
    int pulseDelay;
    int pulseCount;



    public void addActionListener(ActionListener l)
    {
        listener= AWTEventMulticaster.add(listener,l);

    }
    @Override
    public void removeActionListener(ActionListener pl) {
        AWTEventMulticaster.remove(listener,pl);
    }
    public void run()
    {
        alive = true;
        while (alive)
        {
            if (on)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(listener==null)
                {
                    System.out.println("Tick");
                }
                else {
                    listener.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"tick"));
                }

            } else
            {
                try
                {
                    Thread.sleep(1);
                } catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void killThread()
    {
        alive=false;
    }
    public void startG()
    {
        on=true;
    }
    public void stopG()
    {
        on=false;
    }
    public boolean checkOn()
    {
        return on;
    }
    public static void main(String[] args) {
        Generator gen=new Generator();
        gen.start();
        gen.addActionListener(e->{
            System.out.println("inny tick");
        });
        JFrame okno = new JFrame();
        JButton b=new JButton();
        b.setText("Button");
        b.addActionListener(e->{
            if(gen.checkOn())
            {
                gen.stopG();
            }else
            {
                gen.startG();
            }
        });
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.add(b);
        okno.setSize(400,500);
        okno.setVisible(true);

    }





    @Override
    public void startGeneration() {

        on = true;
    }

    @Override
    public void setMode(byte mode) {

        this.mode = mode;
    }

    @Override
    public byte getMode() {

        return mode;
    }

    @Override
    public void stopGeneration() {

        on = false;
    }

    @Override
    public void setPulseDelay(int ms) {

        this.pulseDelay = ms;
    }

    @Override
    public int getPulseDelay() {

        return getPulseDelay();
    }

    @Override
    public void setPulseCount(int burst) {

        this.pulseCount = burst;
    }

    public int getPulseCout() {

        return pulseCount;
    }
}
