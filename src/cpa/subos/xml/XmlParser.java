package cpa.subos.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import cpa.subos.datastore.ParseException;
import cpa.subos.datastore.Parser;
import cpa.subos.io.IOBase;

public class XmlParser implements Parser{

	@SuppressWarnings("unchecked")
	public <T> T parse(Class<T> c, InputStream s) throws ParseException{
		try {
			JAXBContext context = JAXBContext.newInstance( c );
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(s);
		} catch (JAXBException e) {
			throw new ParseException(e);
		}
	}
	
	public <T> T parse(Class<T> c, IOBase<?> b) throws ParseException{
		try {
			return parse(c,b.reader());
		} catch (IOException e) {
			throw new ParseException(e);
		}
	}
	
}
