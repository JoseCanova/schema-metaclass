package org.nanotek.meta.util;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;


//TODO: verify if possible to configure a internal codec for Mongo using Spring DatA.
public class BsonJavaClassCodec implements Codec<Class<?>> {
    @Override
    public void encode(BsonWriter writer, Class<?> value, EncoderContext encoderContext) {
    	
    	Optional
    		.ofNullable(value)
    		.ifPresentOrElse(v -> writer.writeString(v.getName()), 
    				() -> new NoSuchElementException());
    	
    }
    
    @Override
    public Class<?> decode(BsonReader reader, DecoderContext decoderContext) {
    	return Optional.of(toClass(reader.readString())).orElseThrow();
    }
    
    private Class<?> toClass(String string) {
    	try {
    		return Class.forName(string);
    	}catch(Exception ex) {}
		return null;
	}
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Class getEncoderClass() {
        return Class.class;
    }
}
