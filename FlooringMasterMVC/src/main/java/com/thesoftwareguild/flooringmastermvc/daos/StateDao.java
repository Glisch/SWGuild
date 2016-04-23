/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmastermvc.daos;

import com.thesoftwareguild.flooringmastermvc.dtos.State;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class StateDao {

    private List<State> stateList = new ArrayList<>();

    public StateDao() {
        //ADD ALL STATES TO TAXLIST FROM DB
    }

    public String[] getStates() {
        String[] states = new String[stateList.size()];
        int i = 0;
        for (State x : stateList) {
            states[i] = x.getState();
            i++;
        }
        return states;
    }

    public double getTax(String stateName) {
        for (State x : stateList) {
            if (x.getState().equalsIgnoreCase(stateName)) {
                return x.getTaxRate();
            }
        }
        return -1;
    }

}
