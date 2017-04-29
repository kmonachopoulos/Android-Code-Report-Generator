/*
  ------------------------------------------------------------------------------------------------------------------------------------------
  Project       : Code Report Generator
  File          : SQLCodes.java
  Description   : This file holds the SQLite functions.
  Author        : Konstantinos Monachopoulos
  ------------------------------------------------------------------------------------------------------------------------------------------
*/

package com.sphy.commander.codereportgenerator;

public class SQLCodes {

    //private variables
    int _id;
    String _date;
    String _Fcode;
    String _Zcode;

    // Empty constructor
    public SQLCodes(){

    }
    // constructor
    public SQLCodes(int id, String _date, String _Fcode,  String _Zcode){
        this._id = id;
        this._date = _date;
        this._Fcode = _Fcode;
        this._Zcode = _Zcode;
    }

    // constructor
    public SQLCodes(String _date, String _Fcode,  String _Zcode){
        this._date = _date;
        this._Fcode = _Fcode;
        this._Zcode = _Zcode;
    }

    // getting id
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting date
    public String getDate(){
        return this._date;
    }

    // setting date
    public void setDate(String _date){
        this._date = _date;
    }

    // getting code_1
    public String getFcode(){
        return this._Fcode;
    }

    // setting code_1
    public void setFcode(String _Fcode){
        this._Fcode = _Fcode;
    }

    // getting code_2
    public String getZcode(){
        return this._Zcode;
    }

    // setting code_2
    public void setZcode(String _Zcode){
        this._Zcode = _Zcode;
    }


}
