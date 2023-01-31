package day0118;

import model.UserDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class HashMap {
    ArrayList<String> keySet = new ArrayList<>();
    ArrayList<UserDTO> valueList = new ArrayList<>();

    public UserDTO get(String key) {
        if (keySet.contains(key)) {
            int index = keySet.indexOf(key);
            return valueList.get(index);
        }

        return null;
    }

    public ArrayList<String> keySet() {
        return keySet;
    }

    public void put(String key, UserDTO userDTO) {
        if (!keySet.contains(key)) {
            keySet.add(key);
            valueList.add(userDTO);
        }
    }

    public void remove(String key) {
        int index = keySet.indexOf(key);
        if (index != -1) {
            keySet.remove(index);
            valueList.remove(index);
        }
    }
}
