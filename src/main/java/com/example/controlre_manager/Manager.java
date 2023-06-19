package com.example.controlre_manager;

import java.io.Serializable;

public abstract class Manager implements Serializable {
    String name;
    String ID;
    float Hours;

    public Manager(String name, String ID, float hours) {
        this.name = name;
        this.ID = ID;
        Hours = hours;
    }
    abstract float calculercout();
}
class ManagerSenior extends Manager{
    public ManagerSenior(String name, String ID, float hours) {
        super(name, ID, hours);
    }
    float calculercout(){
        if(this.Hours>2000) return 2500 * 2000 +(this.Hours - 2000) * 3500;
        else if (this.Hours<2000) return this.Hours * 2000;
        else {
            return 2500 * 2000;
        }

    }
}
class ManegerJunior extends Manager{
    public ManegerJunior(String name, String ID, float hours) {
        super(name, ID, hours);
    }

    @Override
    float calculercout() {
        if(this.Hours>2500) return 2000 * 2000 +(this.Hours - 2000) * 3000;
        else if (this.Hours<2500) return this.Hours * 1500;
        else {
            return 2500 * 2000;
        }
    }
}
