package cpa.subos.xml;

import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import cpa.subos.datastore.WriteException;
import cpa.subos.datastore.Writer;
import cpa.subos.io.BufferIOBase;
import cpa.subos.io.IO;
import cpa.subos.io.IOBase;
import cpa.subos.io.OutputStreamIOBase;

public class XmlWriter implements Writer{

	public BufferIOBase write(Object o) throws WriteException{
		BufferIOBase ret = IO.buffer();
		write(o,ret);
		return ret;
	}
	
	public <T extends IOBase<?>> T write(Object in, T out) throws WriteException{
		try {
			JAXBContext context = JAXBContext.newInstance( in.getClass() );
			Marshaller m = context.createMarshaller();
			m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
			m.marshal( in, out.writer() );
			return out;
		} catch (JAXBException | IOException e) {
			throw new WriteException(e);
		}
	}
	
	public OutputStreamIOBase write(Object o, OutputStream s) throws WriteException{
		return write(o, IO.stream(s));
	}
	
}
