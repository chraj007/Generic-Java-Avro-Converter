package org.spring.avro.converter.service.impl;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.spring.avro.converter.service.base.AvroService;
import org.springframework.stereotype.Service;

/**
 * The Class AvroServiceImpl.
 * 
 * @author Rajesh Chejerla
 */
@Service
public class AvroServiceImpl implements AvroService{

	@Override
	public DataFileWriter<? extends Serializable> getDataFileWriter(
			Class<? extends Serializable> theClass, String fileName)
			throws IOException {
		File file = new File(fileName);
		Schema schema = ReflectData.get().getSchema(theClass);
		DatumWriter<Serializable> datumWriter = new ReflectDatumWriter<Serializable>((Class<Serializable>) theClass);
		DataFileWriter<Serializable> out = new DataFileWriter<Serializable>(datumWriter)
			      .create(schema, file);
		
		return out;
	}

	@Override
	public void closeWriter(
			DataFileWriter<? extends Serializable> dataFileWriter)
			throws IOException {
		if(dataFileWriter != null){
			dataFileWriter.flush();
			dataFileWriter.close();
		}
	}

	@Override
	public void writeToAvro(DataFileWriter dataFileWriter, Object object,
			Class<? extends Serializable> theClass) throws IOException {
		synchronized (dataFileWriter) {
			dataFileWriter.append(theClass.cast(object));
			dataFileWriter.flush();
		}
	}

	@Override
	public DataFileReader<? extends Serializable> getDataFileReader(String fileName, Class<? extends Serializable> theClass) throws IOException {
		DatumReader<Serializable> userDatumReader = new ReflectDatumReader<Serializable>((Class<Serializable>) theClass);
		DataFileReader<Serializable> dataFileReader = new DataFileReader<Serializable>(new File(fileName), userDatumReader);
		return dataFileReader;
	}

	@Override
	public void closeDataFileReader(
			DataFileReader<? extends Serializable> dataFileReader) throws IOException {
		if(dataFileReader != null){
			dataFileReader.close();
		}
	}
}
