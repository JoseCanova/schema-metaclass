package org.nanotek.meta.util;

public record BoolCharPair(BooleanPair left , CharPair right) 
implements DistinctMemberPair<BooleanPair,CharPair> {
    public static  BoolCharPair from (BooleanPair left , CharPair right){
        return new BoolCharPair(left , right);
    }
}
