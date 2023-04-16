import java.util.Scanner;

public class SysTick implements Cortex_M0_SysTick_Interface {

    int cvr;
    int rvr;
    int csr;

    boolean tick_int;
    boolean clock_source = false;
    boolean counter_flag;
    boolean enable;
    //test

    public static void main(String []args) {

        Cortex_M0_SysTick_Interface SysTick = new SysTick();

        SysTick.tickInternal(); //
        SysTick.tickExternal(); //

        SysTick.setRVR(0);  //
        SysTick.setCVR(0);  //
        SysTick.setCSR(0);  //

        SysTick.reset();                //
        SysTick.setEnable();            //
        SysTick.setDisable();           //
        SysTick.setSourceExternal();    //
        SysTick.setSourceInternal();    //
        SysTick.setInterruptEnable();   //
        SysTick.setInterruptDisable();  //

        SysTick.getCVR();   //
        SysTick.getRVR();   //
        SysTick.getCSR();   //

        SysTick.getEnabled();   //
        SysTick.getInterrupt(); //
        SysTick.getSource();    //
        SysTick.getCountFlag(); //

        SysTick.isCountFlag();      //
        SysTick.isEnableFlag();     //
        SysTick.isInterruptFlag();  //
        SysTick.isInterrupt();      //



        int inputNum;
        Scanner myObj = new Scanner(System.in);

        System.out.println("");
        System.out.println("Type '0' to chow systick parameters: ");
        System.out.println("Type '1' to set RVR: ");
        System.out.println("Type '2' to set CVR: ");
        System.out.println("Type '3' to set CSR: ");
        System.out.println("Type '4' to set enable as true: ");
        System.out.println("Type '5' to set enable as false: ");
        System.out.println("Type '6' to set source as external: ");
        System.out.println("Type '7' to set source as internal: ");
        System.out.println("Type '8' to set interrupt as enable: ");
        System.out.println("Type '9' to set interrupt as disable: ");

        inputNum = myObj.nextInt();



        switch(inputNum){

            case 0:
                System.out.println("RVR: " + SysTick.getRVR());
                System.out.println("CVR: " + SysTick.getCVR());
                System.out.println("CSR: " + SysTick.getCSR());

                break;

            case 1:

                int newVal1;
                Scanner scanner1 = new Scanner(System.in);

                System.out.println("Enter new RVR value: ");

                newVal1 = myObj.nextInt();

                SysTick.setRVR(newVal1);

                System.out.println("New RVR val: " + SysTick.getRVR());
                break;

            case 2:

                int newVal2;
                Scanner scanner2 = new Scanner(System.in);

                System.out.println("Enter new CVR value: ");

                newVal2 = myObj.nextInt();

                SysTick.setRVR(newVal2);

                System.out.println("New CVR val: " + SysTick.getCVR());

                break;

            case 3:

                int newVal3;
                Scanner scanner3 = new Scanner(System.in);

                System.out.println("Enter new CSR value: ");

                newVal3 = myObj.nextInt();

                SysTick.setRVR(newVal3);

                System.out.println("New CSR val: " + SysTick.getCSR());

                break;

            case 4:

                SysTick.setEnable();
                System.out.println("Enable set to: " + SysTick.getEnabled());

                break;

            case 5:

                SysTick.setDisable();
                System.out.println("Enable set to: " + SysTick.getEnabled());

                break;

            case 6:

                SysTick.setSourceExternal();
                System.out.println("Source set to: " + SysTick.getSource());

                break;

            case 7:

                SysTick.setSourceInternal();
                System.out.println("Source set to: " + SysTick.getSource());

                break;

            case 8:

                SysTick.setInterruptEnable();
                System.out.println("Interrupt set to: " + SysTick.getInterrupt());

                break;

            case 9:

                SysTick.setInterruptDisable();
                System.out.println("Interrupt set to: " + SysTick.getInterrupt());

                break;

            default:
                System.out.println("Wrong number!!!");
        }

    }


    @Override
    public void tickInternal() {

        if(clock_source && enable){

            cvr--;

        }

        if(cvr == 0) {

            counter_flag = true;
            //enable = false;

        }

        if(cvr <= 0){

            cvr = rvr;

        }

//        if (clock_source && enable ) {
//
//            if(cvr == 0){
//
//                counter_flag = true;
//                cvr = rvr;
//                System.out.println(rvr);
//            }
//
//            }else {
//
//            cvr--;
//
//            if (cvr == 0) {
//
//                counter_flag = true;
//            }
//
//        }


    }
//        if(enable) {
//
//            cvr--;
//        }


    @Override
    public void tickExternal() {

        if(!clock_source && enable){

            cvr--;
            //counter_flag = true;

        }

        if(cvr == 0){

            cvr = rvr;
            counter_flag = true;
        }

        if(cvr < 0) {

            cvr = 0;

        }

//        if (!clock_source && enable ) {
//
//            if(cvr == 0){
//
//                counter_flag = true;
//                cvr = rvr;
//            }
//
//
//        }else {
//
//                cvr --;
//
//                if(cvr == 0){
//
//                    counter_flag = true;
//                }
//        }


    }




    @Override
    public void setRVR(int value) {


        if(value > 16777215){

            value = (value & 16777215);

            //rvr = value;
        }

        if(value < 0){

            value = ((1<<24) + value);

        }
        rvr = value;

//        rvr = value;
//
//        if (rvr == 0) {
//
//            setDisable();
//        }

        //rvr = value;
    }

    @Override
    public void setCVR(int value) {

        //cvr = value;

        cvr = 0;
        counter_flag = false;
    }

    @Override
    public void setCSR(int value) {

        csr = value;

//        if (csr == value) { /// try !=
//
//            counter_flag = true;
//            clock_source = true;
//            tick_int = true;
//            enable = true;
//
//        } else {
//
//            counter_flag = false;
//            clock_source = false;
//            tick_int = false;
//            enable = false;
//
//        }
    }

    @Override
    public void reset() {

        enable = false;
        counter_flag = true;

        rvr = 0;
        //cvr =0;
    }

    @Override
    public void setEnable() {

        enable = true;
        counter_flag = false;
    }

    @Override
    public void setDisable() {

        enable = false;
        counter_flag = true;
        cvr = 0;
    }

    @Override
    public void setSourceExternal() {   // false for external // true for internal

        clock_source = false;
    }

    @Override
    public void setSourceInternal() {   // false for external // true for internal

        clock_source = true;
    }

    @Override
    public void setInterruptEnable() {   // false for disable // true for enable

        tick_int = true;

    }

    @Override
    public void setInterruptDisable() {   // false for disable // true for enable

        tick_int = false;

    }

    @Override
    public int getCVR() {

        return cvr;
    }

    @Override
    public int getRVR() {

        return rvr;
    }

    @Override
    public int getCSR() {

        counter_flag = false;
        return csr;
    }

    @Override
    public boolean getEnabled() {

        if(enable) {

            counter_flag = false;
            return true;

        }else{

            counter_flag = false;
            return false;

        }

    }

    @Override
    public boolean getInterrupt() {

        if (tick_int && counter_flag){

            return true;

        }else{

            return false;

        }

        //return tick_int && counter_flag;
    }

    @Override
    public boolean getSource() {

        return clock_source;
    }

    @Override
    public boolean getCountFlag() {
        counter_flag = false;
        return false;
    }

    @Override
    public boolean isCountFlag() {

        return counter_flag;
    }

    @Override
    public boolean isEnableFlag() {

        return enable;
    }

    @Override
    public boolean isInterruptFlag() {

        return tick_int;
    }

    @Override
    public boolean isInterrupt() {

        if(tick_int){

            enable = true;
            return counter_flag;

        }else{

            return false;
        }
//        if(tick_int && !counter_flag){
//
//            return true;
//
//        }else{
//
//            return false;
//
//        }
    }

//    public static void tim() {
//
//        int timer = Integer.MAX_VALUE;
//        int check = 1;
//
//        while(true) {
//
//            System.out.println("Press '1' to start timer: ");
//
//            try {
//
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//
//
//                e.printStackTrace();
//            }
//
//            Scanner sc = new Scanner(System.in);
//            String scanned = sc.next();
//
//            int x = Integer.parseInt(scanned);
//
//            if(x == 1) {
//
//                System.out.println(timer);
//
//                if (timer == 0) {
//
//                    check = 0;
//
//                }
//
//                System.out.println("Counter_flag = " + check + ".");
//
//                timer--;
//
//                if(check == 0) {
//
//                    break;
//                }
//            }
//        }
//    }

}
