package org.nanotek.meta.util;

public record SnakeCaseConverter(){
    public SnakeCaseConverter(){}

    public static String from (String camelCase){
        Holder<Boolean> caseHolder = new Holder<>(false);
        StringBuilder sb = new StringBuilder();
        camelCase
        .chars()
        .forEach(c -> {
            var append = true;
            char c1 = Character.class.cast(c);
            if (c1 == '_'){
                append = false;
                caseHolder.put(true);
            }
            if (caseHolder.get() == true && append ==true){
                c1 = Character.toUpperCase(c1);
                caseHolder.put(false);
            }
            if (append == true){
                sb.append(c1);
            }
        });
        return sb.toString();
    }
}