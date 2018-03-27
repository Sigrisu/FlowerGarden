package com.flowergarden.dao.impl.json;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.dao.BouquetDaoIO;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONTokener;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;
import org.springframework.stereotype.Repository;

import javax.xml.bind.*;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.List;


@Repository("bouquetDaoJson")
public class BouquetDaoJsonImpl implements BouquetDaoIO {
    @Override
    public List<Bouquet> getAll() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Bouquets.class);
        Configuration config = new Configuration();
        MappedNamespaceConvention con = new MappedNamespaceConvention(config);
        InputStream inputStream = new FileInputStream("src/bouquets.json");
        Reader reader = new InputStreamReader(inputStream);
        XMLStreamReader xmlStreamReader = getXmlStreamReader(reader, con);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Bouquets bouquets = (Bouquets) unmarshaller.unmarshal(xmlStreamReader);
        inputStream.close();
        return bouquets.getBouquetsToList();
    }

    @Override
    public int addAll(List<Bouquet> bouquets) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Bouquets.class);

        Configuration config = new Configuration();
        MappedNamespaceConvention con = new MappedNamespaceConvention(config);
        OutputStream outputStream = new FileOutputStream("src/bouquets.json");
        Writer writer = new OutputStreamWriter(outputStream);
        XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);
        Marshaller marshaller = jc.createMarshaller();
        Bouquets b = new Bouquets();
        b.setBouquetsFlomList(bouquets);
        marshaller.marshal(b, xmlStreamWriter);
        outputStream.close();
        return b.getBouquets().size();
    }

    protected MappedXMLStreamReader getXmlStreamReader(Reader reader, MappedNamespaceConvention convention) throws JAXBException {
        MappedXMLStreamReader badger;
        char[] buffer = new char[100];
        StringBuffer buf = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            int wasRead = 0;
            do {
                wasRead = bufferedReader.read(buffer, 0, 100);
                if (wasRead > 0)
                    buf.append(buffer, 0, wasRead);
            } while (wasRead > -1);
            badger = new MappedXMLStreamReader(new JSONObject(new JSONTokener(buf.toString())), convention);
        } catch (Exception e) {
            throw new JAXBException(e);
        }
        return badger;
    }
}
