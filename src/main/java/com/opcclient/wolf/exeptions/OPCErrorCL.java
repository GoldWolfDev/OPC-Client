package com.opcclient.wolf.exeptions;


import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

public class OPCErrorCL {

    private final String msgGetOPC1Text = "Host not found -> Causes of error : not entered the correct ip address/host name.";
    private final String msgGetOPC2Text = "OPC Server not found -> Causes of error :\n" +
            "1. Check DCOM / OPC Enum settings.\n" +
            "2. OPC Server is not set";
    private final String msgTitle = "Attention!!!";

    private final String msgErrorSelect = "Object not Selection";

    private final String msgErrorSQLConnect = "Could not open connection to SQL";


    private static OPCErrorCL instance = null;

    private OPCErrorCL(){}

    public static OPCErrorCL getInstance() {
        if (instance == null){
            instance = new OPCErrorCL();
        }
        return instance;
    }

    public void errorServer1(){
        MessageBox.show(new Stage(), msgGetOPC1Text, msgTitle, MessageBox.OK);
    }
    public void errorServer2(){
        MessageBox.show(new Stage(), msgGetOPC2Text, msgTitle, MessageBox.OK);
    }
    public void errorSelect(){
        MessageBox.show(new Stage(), msgErrorSelect, msgTitle, MessageBox.OK);
    }
    public void errorSQLConnect(){
        MessageBox.show(new Stage(), msgErrorSQLConnect, msgTitle, MessageBox.OK);
    }
    public void errorAny(String ex){
        MessageBox.show(new Stage(), ex, msgTitle, MessageBox.OK);
    }
}
