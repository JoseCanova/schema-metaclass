package org.nanotek.meta.util;

import java.util.stream.*;


public record SnakeCaseFluentConverter(){
    public SnakeCaseFluentConverter(){}

    public static String from (String snakeCase){
        return snakeCase.chars()
        .mapToObj(c -> String.valueOf( (char)c))
        .map(s -> 
        
            BooleanStringPair.from(
                s.equals("_")?false:true,
                s
            )
        
        ).reduce((a,b)-> computeRedux(a,b)).get().right();
        
    }

    private static BooleanStringPair computeRedux(BooleanStringPair c , BooleanStringPair b){

        System.out.print("\t" + c.left() + "\t" + c.right());
        System.out.println("\t" + b.left() + "\t" + b.right());
        return BooleanStringPair.from(
            
                b.left() , 
                c.left() == false && b.left() == true?
                c.right().concat(b.left()==true?b.right().toUpperCase():""):
                c.left() == true && b.left()==false ?  c.right(): c.right().concat(b.right())

        
        );
    }

    public static void main(String args[]){
        System.out.println( SnakeCaseFluentConverter.from("_snake_case_fluent_converter"));
    }
}