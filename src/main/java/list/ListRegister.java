package list;

import java.util.ArrayList;

public class ListRegister {
    private static ListRegister register;
    private ArrayList<List> vereinList;

    private ListRegister() {
        //List of all the Items
        vereinList = CsvManager.loadData();
    }

    public static ListRegister getRegister() {
        if (register == null) {
            register = new ListRegister();
        }
        return register;
    }

    public ArrayList<List> getList() {
        return vereinList;
    }

    public static void setRegister(ListRegister register) {
        ListRegister.register = register;
    }

    public void setList(ArrayList<List> lists) {
        this.vereinList = lists;
    }

    public void loadRegister() {
        this.vereinList = CsvManager.loadData();
    }
}

