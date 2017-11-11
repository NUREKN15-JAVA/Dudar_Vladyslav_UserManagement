package ua.nure.kn_15_6.dudar.view;

import ua.nure.kn_15_6.dudar.User;
import ua.nure.kn_15_6.dudar.util.Messages;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private List<User> users = null;
    private final String[] COLUMN_NAMES = {Messages.getString("UserTableModel.id"),
            Messages.getString("UserTableModel.first_name"),
            Messages.getString("UserTableModel.last_name")};
    private final Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};

    public UserTableModel(List<User> users) {
        this.users = users;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch(columnIndex){
            case 0:
                return user.getId();
            case 1:
                return user.getFirstName();
            case 2:
                return user.getLastName();
        }
        return null;
    }

    public Class getColumnClass(int colNum) {
        return COLUMN_CLASSES[colNum];
    }

    public String getColumnName(int colNum) {
        return COLUMN_NAMES[colNum];
    }
}
