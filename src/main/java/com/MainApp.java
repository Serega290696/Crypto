package com;

import com.console_interface.Console;
import com.data_base.HibernateUtil;
import com.data_base.entities.User;

/**
 * Created by Serega on 19.07.2015.
 */
public class MainApp {


    static final User curUser = null;
    private static AppInterface appInterface = new Console(curUser);
//    private static final Logger logger = Logger.getLogger("");

    public static void main(String[] args) {


        appInterface.launch();

        HibernateUtil.shutdown();
//
//        String str = "12345678 2345678 2345678 2345678";
//        ArrayList<byte[]> list = new ArrayList<byte[]>();
//        for (int i = 0; i < str.length(); i++) {
//            if(i % 8 == 0)
//                list.add(new byte[8]);
//            list.get(list.size()-1)[i % 8] = (byte) str.charAt(i);
//        }
//        for(int i = 0; i < 8; i++) {
//            if(list.get(list.size() - 1)[8-1-i] == 0)
//                    list.get(list.size() - 1)[8-1-i] = (byte)'c';
//        }
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i < list.size(); i ++) {
//            sb.append(myMethod(new String(list.get(i))));
//        }
//        for(int i = 0; i < list.size(); i ++) {
//            for(int j = 0; j < 8; j++)
//                System.out.print((char) list.get(i)[j]);
//        }
//        System.out.println(new String(list.get(0)));
//Character.
    }


}
