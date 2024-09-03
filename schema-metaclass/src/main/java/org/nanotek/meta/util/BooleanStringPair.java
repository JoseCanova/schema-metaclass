package org.nanotek.meta.util;

public record BooleanStringPair(Boolean left , String  right)
implements DistinctMemberPair<Boolean ,String > {

    public static BooleanStringPair from(Boolean left , String right){
        return new BooleanStringPair(left , right);
    }

}
