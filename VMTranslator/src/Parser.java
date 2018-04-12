
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User_Len
 */
public class Parser {
    LinkedList arithmeticCommand;
    private String argument;
    private int argument2;
    public Parser(String address){
        //create the list with the arithmetic commands
        arithmeticCommand = new LinkedList();
        arithmeticCommand.add("add");
        arithmeticCommand.add("sub");
        arithmeticCommand.add("neg");
        arithmeticCommand.add("eq");
        arithmeticCommand.add("gt");
        arithmeticCommand.add("lt");
        arithmeticCommand.add("and");
        arithmeticCommand.add("or");
        arithmeticCommand.add("not");
        //Opens the input file and gets ready to parse it
    try{
        FileReader reader = new FileReader(address + "vm");
        BufferedReader buffer = new BufferedReader(reader);
        String line = buffer.readLine();
        while(line != null){
            if(line.contains("//")){
                line = line.substring(0,line.indexOf("/"));
            }
            switch(commandType(line.toLowerCase())){
                case "C_ARITHMETIC":
                {
                    //code
                    break;
                }
                case "C_PUSH":
                {
                    break;
                }
                case "C_POP":
                {
                    break;
                }
                default:
                {
                    break;
                }
            }
            line = buffer.readLine();
        }
    }catch(IOException e){}
    }
    //returns the type of the current vm command
    private String commandType(String command){
        if(!command.equals("")){
            String[] fragment = command.split(" ");
            if(arithmeticCommand.contains(fragment[0])){
                argument = fragment[0];
            return "C_ARITHMETIC";
            }else{
                switch(fragment[0]){
                    case "push":
                    {
                        argument = fragment[1];
                        argument2 = Integer.parseInt(fragment[2]);
                        return "C_PUSH";
                    }
                    case "pop":
                    {
                        argument = fragment[1];
                        argument2 = Integer.parseInt(fragment[2]);
                        return "C_POP";
                    }
                    case "label":
                    {
                        argument = fragment[1];
                        return "C_LABEL";
                    }
                    case "return":
                    {
                        return "C_RETURN";
                    }
                    case "function":
                    {
                        argument = fragment[1];
                        argument2 = Integer.parseInt(fragment[2]);
                        return "C_Function";
                    }
                    case "goto":
                    {
                        argument = fragment[1];
                        return "C_GOTO";
                    }
                    case "if-goto":
                    {
                        argument = fragment[1];
                        return "C_IF";
                    }
                    case "call":
                    {
                        argument = fragment[1];
                        argument2 = Integer.parseInt(fragment[2]);
                        return "C_CALL";
                    }
                }
            }
        }
    return "";
    }
    //Returns the first argument of the current command
    private String arg1(){
    return argument;
    }
    //Returns the second argument of the current command
    private int arg2(){
    return argument2;
    }
}
