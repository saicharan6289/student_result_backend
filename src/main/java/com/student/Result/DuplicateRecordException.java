package com.student.Result;

public class DuplicateRecordException extends Exception{
     public DuplicateRecordException(String str){
         super(str + " Already Exists please enter a new " + str);
     }

}
