

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * NAME         : Zachary Kolton
 * STUDENT#     : 7838513
 * COURSE       : COMP 2150
 * INSTRUCTOR   : MR. Boyer
 * ASSIGNMENT   : 3
 * QUESTION     : Part 1
 * 
 * REMARKS: This is a class for testing the class (and subclasses) of Value 
 */
public class TestJSON
{
    
    //-------------------------------------
    // TEST Value Creation
    //-------------------------------------
    
    @Test
    public void createValueBool()
    {
        Value vBool     = new ValueBool(true);
        
        assertEquals("Value should be true", Boolean.toString(true), vBool.toString());
    }
    
    @Test
    public void createValueString()
    {
        Value vString   = new ValueString("test");
        
        assertEquals("Value should be \"test\"", "\""+"test"+"\"", vString.toString());
    }
    
    @Test
    public void createValueInt()
    {
        Value vInt      = new ValueInt(1);
        
        assertEquals("Value should be 1", "1", vInt.toString());
    }
    
    @Test
    public void createValueDouble()
    {
        Value vDouble   = new ValueDouble(1.1);
        
        assertEquals("Value should be 1.1", "1.1",vDouble.toString());
    }
    
    //-------------------------------------
    // TEST JSONObject Creation/Add 
    //-------------------------------------   
    
    @Test
    public void JSONObjectAdd()
    {
        Value vString1 = new ValueString("TestKey");
        Value vString2 = new ValueString("TestValue");
        
        JSONObject obj = new ValueObject();
        obj.addKeyValue(vString1,vString2);
        System.out.println(obj.toString());
        
        assertEquals("Value should be \"TestValue\"","\""+"TestValue"+"\"", obj.getValue(vString1).toString());
    }
    
    @Test
    public void JSONObjectAddMultiple()
    {
        Value vString1  = new ValueString("TestKeyString");
        Value vString2  = new ValueString("TestValueString");
        
        Value vString3  = new ValueString("TestKeyInt");
        Value vInt      = new ValueInt(1);   
        
        Value vString4  = new ValueString("TestKeyBool");
        Value vBool     = new ValueBool(true);
        
        Value vString5  = new ValueString("TestKeyDub");
        Value vDouble   = new ValueDouble(1.1);
        
        JSONObject obj = new ValueObject();
        obj.addKeyValue(vString1,vString2);
        obj.addKeyValue(vString3,vInt);
        obj.addKeyValue(vString4,vBool);
        obj.addKeyValue(vString5,vDouble);
        System.out.println(obj.toString());


    }
    
    
}
